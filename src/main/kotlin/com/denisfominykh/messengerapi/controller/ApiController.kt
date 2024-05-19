package com.denisfominykh.messengerapi.controller

import com.denisfominykh.messengerapi.controller.body.DeleteBody
import com.denisfominykh.messengerapi.controller.body.SendBody
import com.denisfominykh.messengerapi.result.OperationResult
import com.denisfominykh.messengerapi.service.MessageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class ApiController(
    private val messageService: MessageService,
){
//    @PostMapping("send")
//    fun sendMessage(userToken: String): OperationResultView {
//        return messageService.attemptSendMessage(userToken, "ABC", 123L)
//    }

    @PostMapping("send")
    fun sendMessage(@RequestBody body: SendBody): OperationResult {
        return messageService.attemptSendMessage(body.token, body.message, body.chatId)
    }

    @PostMapping("delete")
    fun deleteMessage(@RequestBody body: DeleteBody): OperationResult {
        return messageService.attemptDeleteMessage(body.userId, body.chatId, body.messageId)
    }
}