package com.denisfominykh.messengerapi.model

import com.denisfominykh.messengerapi.message.Message
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Sharded
import java.time.Instant

@Document("message")
@Sharded(shardKey = [ "country" ])
class MessageModel (
    @Id
    val id: String?,
    val text: String,
    val senderId: Long,
    @Indexed
    val timestamp: Instant,
    val chatId: Long,
    val country: String
) {
    fun intoCore() = Message(
        id,
        text,
        senderId,
        timestamp,
        country,
        chatId
    )

    companion object {
         fun fromCore(message: Message): MessageModel {
             return MessageModel(
                 message.id,
                 message.text,
                 message.senderId,
                 message.timestamp,
                 message.chatId,
                 message.country
             )
         }
    }
}