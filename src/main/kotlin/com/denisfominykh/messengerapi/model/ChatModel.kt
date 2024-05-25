package com.denisfominykh.messengerapi.model

import com.denisfominykh.messengerapi.chat.Chat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("chat")
class ChatModel (
    @Id
    val id: Long,
    val name: String,
) {
    fun intoCore() = Chat(
        id,
        name,
    )

    companion object {
        fun fromCore(core: Chat) = ChatModel(
            core.id,
            core.name,
        )
    }
}