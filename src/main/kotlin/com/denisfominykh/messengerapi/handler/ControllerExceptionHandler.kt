package com.denisfominykh.messengerapi.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(java.lang.Exception::class)
    fun handleCommonException(
        exception: Exception,
        webRequest: ServletWebRequest,
    ) = ResponseEntity(
        ApiError(exception::class.simpleName.toString(), exception.message ?: "some-message"),
        HttpStatus.BAD_REQUEST,
    )
}
