package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.client.UserServiceClient
import com.denisfominykh.messengerapi.repository.ChatRepository
import com.denisfominykh.messengerapi.repository.MessageRepository
import com.denisfominykh.messengerapi.result.OperationResult
import com.denisfominykh.messengerapi.service.MessageService
import com.denisfominykh.messengerapi.user.UserData
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class MessengerServiceImpl(
    private val clientService: UserServiceClient,
    private val repository: MessageRepository,
    private val chatRepository: ChatRepository,
): MessageService {
    override fun attemptSendMessage(token: String, message: String, chatId: Long): OperationResult {
        val authUser = clientService.requestUserDataByToken(token)

        return newMessage(message, authUser, chatId)
    }

    override fun attemptDeleteMessage(token: String, chatId: Long, messageId: Long): OperationResult {
        val authUser = clientService.requestUserDataByToken(token)

        return deleteMessage(authUser, chatId, messageId)
    }

    private fun newMessage(message: String, user: UserData, chatId: Long): OperationResult {
        // TODO:
        return OperationResult(OperationResult.OperationStatus.SUCCESS, Instant.now(), null)
    }

    private fun deleteMessage(user: UserData, chatId: Long, messageId: Long): OperationResult {
        // TODO:
    }

}