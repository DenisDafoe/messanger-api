package com.denisfominykh.messengerapi.message

import java.time.Instant

data class Message (
    val id: Long? = null,
    val text: String,
    val senderId: Long,
    val timestamp: Instant,
    val chatId: Long,
) {
    fun withId(id: Long) = copy(
        id = id
    )
}