package com.denisfominykh.messengerapi.service

import com.denisfominykh.messengerapi.result.OperationResult

interface MessageService {
    fun attemptSendMessage(token: String, chatId: Long): OperationResult

    fun attemptDeleteMessage(token: String, chatId: Long, messageId: Long): OperationResult
}