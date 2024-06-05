package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.client.UserServiceClient
import com.denisfominykh.messengerapi.message.Message
import com.denisfominykh.messengerapi.result.OperationData
import com.denisfominykh.messengerapi.result.OperationResult
import com.denisfominykh.messengerapi.service.MessageService
import com.denisfominykh.messengerapi.service.MessageStorageService
import com.denisfominykh.messengerapi.user.UserData
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random

@Service
class MessengerServiceImpl(
    private val clientService: UserServiceClient,
    private val messageStorageService: MessageStorageService,
    private val mongoTemplate: MongoTemplate,
) : MessageService {
    override fun attemptSendMessage(
        token: String,
        chatId: Long,
    ): OperationResult {
        val authUser = clientService.requestUserDataByToken(token)

        return newMessage(authUser, chatId)
    }

    override fun attemptDeleteMessage(
        token: String,
        chatId: Long,
        messageId: String,
    ): OperationResult {
        val authUser = clientService.requestUserDataByToken(token)

        return deleteMessage(authUser, chatId, messageId)
    }

    override fun readFromChat(
        token: String,
        chatId: Long,
        number: Int,
    ): List<Message> {
        val authUser = clientService.requestUserDataByToken(token)

        return messageStorageService.getNumberFromChat(chatId, number)
    }

    private fun newMessage(
        user: UserData,
        chatId: Long,
    ): OperationResult {
        val randomText = getRandomString(100)

        val country = randomCountry()

        val saved =
            messageStorageService.save(
                Message(senderId = user.id, timestamp = Instant.now(), text = randomText, chatId = 123, country = country),
            )

        return OperationResult(
            OperationResult.OperationStatus.SUCCESS,
            Instant.now(),
            OperationData.create(
                requireNotNull(saved.id),
                saved.chatId,
                saved.senderId,
            ),
        )
    }

    private fun deleteMessage(
        user: UserData,
        chatId: Long,
        messageId: String,
    ): OperationResult {
        messageStorageService.delete(messageId)

        return OperationResult(
            OperationResult.OperationStatus.SUCCESS,
            Instant.now(),
            OperationData.delete(
                messageId,
                chatId,
                user.id,
            ),
        )
    }

    private fun randomCountry(): String {
        return if (Random.nextLong(1, 3) == 1L) {
            "RUSSIA"
        } else {
            "CHINA"
        }
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('А'..'Я') + ('а'..'я') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
