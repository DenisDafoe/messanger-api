package com.denisfominykh.messengerapi.controller

import com.denisfominykh.messengerapi.controller.body.DeleteBody
import com.denisfominykh.messengerapi.controller.body.SendMessageBody
import com.denisfominykh.messengerapi.controller.view.OperationResultView
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

    @PostMapping("send")
    fun sendMessage(@RequestBody body: SendMessageBody): OperationResultView {
        return OperationResultView.fromCore(messageService.attemptSendMessage(body.token, body.chatId))
    }

    @PostMapping("delete")
    fun deleteMessage(@RequestBody body: DeleteBody): OperationResultView {
        return OperationResultView.fromCore(messageService.attemptDeleteMessage(body.token, body.chatId, body.messageId))
    }
}