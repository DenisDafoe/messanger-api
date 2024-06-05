package com.denisfominykh.messengerapi.service

import com.denisfominykh.messengerapi.message.Message
import com.denisfominykh.messengerapi.result.OperationResult

interface MessageService {
    fun attemptSendMessage(token: String, chatId: Long): OperationResult

    fun attemptDeleteMessage(token: String, chatId: Long, messageId: String): OperationResult

    fun readFromChat(token: String, chatId: Long, number: Int): List<Message>
}