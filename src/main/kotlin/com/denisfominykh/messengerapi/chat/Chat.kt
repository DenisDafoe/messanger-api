package com.denisfominykh.messengerapi.chat

data class Chat(
    val id: Long,
    val name: String,
    val participants: List<Long>,
)