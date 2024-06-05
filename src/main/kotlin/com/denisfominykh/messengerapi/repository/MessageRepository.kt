package com.denisfominykh.messengerapi.repository

import com.denisfominykh.messengerapi.model.MessageModel
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : MongoRepository<MessageModel, String> {
    fun findAllByChatId(chatId: Long): List<MessageModel>

    fun findAllByChatIdOrderByTimestampDesc(
        chatId: Long,
        pageable: Pageable,
    ): List<MessageModel>
}
