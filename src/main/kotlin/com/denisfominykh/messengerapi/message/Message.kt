package com.denisfominykh.messengerapi.message

import java.time.Instant

data class Message (
    val id: String? = null,
    val text: String,
    val senderId: Long,
    val timestamp: Instant,
    val country: String,
    val chatId: Long,
)
