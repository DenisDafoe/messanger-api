package com.denisfominykh.messengerapi.message

import java.time.Instant

data class Message (
    val id: Long,
    val senderId: Long,
    val timestamp: Instant,
    val chatId: Long,
)