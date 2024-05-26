package com.denisfominykh.messengerapi.controller

import com.denisfominykh.messengerapi.controller.body.LoginBody
import com.denisfominykh.messengerapi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
){
    @PostMapping("login")
    fun login(
        @RequestBody loginBody: LoginBody,
    ): String {
        return userService.login(loginBody.userName, loginBody.hashedPassword)
    }
}