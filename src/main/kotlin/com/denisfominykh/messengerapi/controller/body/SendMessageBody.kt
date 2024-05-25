package com.denisfominykh.messengerapi.controller.body

data class SendMessageBody (
    val token: String,
    val chatId: Long
)

data class DeleteBody(
    val token: String,
    val chatId: Long,
    val messageId: Long
)