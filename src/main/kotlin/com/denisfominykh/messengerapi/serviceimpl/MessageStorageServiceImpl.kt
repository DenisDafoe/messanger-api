package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.message.Message
import com.denisfominykh.messengerapi.model.MessageModel
import com.denisfominykh.messengerapi.model.SequenceModel
import com.denisfominykh.messengerapi.repository.MessageRepository
import com.denisfominykh.messengerapi.repository.SequenceRepository
import com.denisfominykh.messengerapi.service.MessageStorageService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class MessageStorageServiceImpl(
    private val messageRepository: MessageRepository,
    private val sequenceRepository: SequenceRepository,
): MessageStorageService {
    override fun save(message: Message): Message {
        if (message.id == null) {
            val currentId = sequenceRepository.findById("message").getOrNull()?.idNumber ?: initSequence()
            updateSequence(currentId)
            return messageRepository.save(MessageModel.fromCore(message.withId(currentId))).intoCore()
        }

        return messageRepository.save(MessageModel.fromCore(message)).intoCore()
    }

    override fun getAllFromChat(chatId: Long): List<Message> {
        return messageRepository.findAllByChatId(chatId).map { it.intoCore() }
    }

    override fun getNumberFromChat(chatId: Long, number: Int): List<Message> {
        return findLastNMessages(chatId, number).map { it.intoCore() }
    }

    override fun delete(messageId: Long) {
        messageRepository.deleteById(messageId)
    }

    private fun findLastNMessages(chatId: Long, number: Int): List<MessageModel> {
        val pageable = PageRequest.of(0, number, Sort.by(Sort.Direction.DESC, "timestamp"))
        return messageRepository.findAllByChatIdOrderByTimestampDesc(chatId, pageable)
    }

    private fun updateSequence(lastId: Long) {
        sequenceRepository.save(SequenceModel("message", lastId+1))
    }

    private fun initSequence(): Long {
        return sequenceRepository.save(SequenceModel("message", 1)).idNumber
    }
}