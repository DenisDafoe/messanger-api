package com.denisfominykh.messengerapi.service

import com.denisfominykh.messengerapi.message.Message

interface MessageStorageService {
    fun save(message: Message): Message

    fun getAllFromChat(chatId: Long): List<Message>

    fun delete(messageId: Long)
}