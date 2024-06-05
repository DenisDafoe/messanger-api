package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.message.Message
import com.denisfominykh.messengerapi.model.MessageModel
import com.denisfominykh.messengerapi.model.SequenceModel
import com.denisfominykh.messengerapi.repository.MessageRepository
import com.denisfominykh.messengerapi.repository.SequenceRepository
import com.denisfominykh.messengerapi.service.MessageStorageService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class MessageStorageServiceImpl(
    private val messageRepository: MessageRepository,
    private val sequenceRepository: SequenceRepository,
    private val mongoTemplate: MongoTemplate,
): MessageStorageService {
    override fun save(message: Message): Message {
        if (message.id == null) {
            return messageRepository.save(MessageModel.fromCore(message)).intoCore()
        }

        return messageRepository.save(MessageModel.fromCore(message)).intoCore()
    }

    override fun getAllFromChat(chatId: Long): List<Message> {
        return messageRepository.findAllByChatId(chatId).map { it.intoCore() }
    }

    override fun getNumberFromChat(chatId: Long, number: Int): List<Message> {
        return findLastNMessages(chatId, number).map { it.intoCore() }
    }

    override fun delete(messageId: String) {
        messageRepository.deleteById(messageId)
    }

    private fun findLastNMessages(chatId: Long, number: Int): List<MessageModel> {

        val pageable = PageRequest.of(0, number, Sort.by(Sort.Direction.DESC, "timestamp"))
        return messageRepository.findAllByChatIdOrderByTimestampDesc(chatId, pageable)
    }
}