package com.denisfominykh.messengerapi.model

import com.denisfominykh.messengerapi.message.Message
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("message")
class MessageModel (
    @Id
    val id: Long,
    val text: String,
    val senderId: Long,
    val timestamp: Instant,
    val chatId: Long,
) {
    fun intoCore() = Message(
        id,
        text,
        senderId,
        timestamp,
        chatId
    )

    companion object {
         fun fromCore(message: Message): MessageModel {
             return MessageModel(
                 requireNotNull(message.id),
                 message.text,
                 message.senderId,
                 message.timestamp,
                 message.chatId
             )
         }
    }
}