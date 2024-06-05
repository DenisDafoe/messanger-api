package com.denisfominykh.messengerapi.controller.view

import com.denisfominykh.messengerapi.message.Message
import java.time.Instant

data class MessagesView (
    val id: String,
    val text: String,
    val senderId: Long,
    val timestamp: Instant,
    val chatId: Long,
) {
    companion object {
        fun fromCore(message: Message): MessagesView {
            return MessagesView(
                requireNotNull(message.id),
                message.text,
                message.senderId,
                message.timestamp,
                message.chatId
            )
        }
    }
}