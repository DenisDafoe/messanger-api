package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.message.Message
import com.denisfominykh.messengerapi.model.MessageModel
import com.denisfominykh.messengerapi.repository.MessageRepository
import com.denisfominykh.messengerapi.repository.SequenceRepository
import com.denisfominykh.messengerapi.service.MessageStorageService
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class MessageStorageServiceImpl(
    private val messageRepository: MessageRepository,
    private val sequenceRepository: SequenceRepository,
    private val mongoTemplate: MongoTemplate,
) : MessageStorageService {
    override fun save(message: Message): Message {
        if (message.id == null) {
            return messageRepository.save(MessageModel.fromCore(message)).intoCore()
        }

        return messageRepository.save(MessageModel.fromCore(message)).intoCore()
    }

    override fun getAllFromChat(chatId: Long): List<Message> {
        return messageRepository.findAllByChatId(chatId).map { it.intoCore() }
    }

    override fun getNumberFromChat(
        chatId: Long,
        number: Int,
    ): List<Message> {
        return findLastNMessages(chatId, number).map { it.intoCore() }
    }

    override fun delete(messageId: String) {
        messageRepository.deleteById(messageId)
    }

    private fun findLastNMessages(
        chatId: Long,
        number: Int,
    ): List<MessageModel> {
        val query = Query(Criteria.where("chatId").`is`(chatId)).with(Sort.by(Sort.Direction.ASC, "timestamp")).limit(number)
        return mongoTemplate.find(query, MessageModel::class.java)
    }
}
