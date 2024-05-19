package com.denisfominykh.messengerapi.controller.body

data class SendBody (
    val token: String,
    val message: String,
    val chatId: Long
)

data class DeleteBody(
    val userId: Long,
    val chatId: Long,
    val messageId: Long
)