package com.denisfominykh.messengerapi.controller.body

data class LoginBody (
    val userName: String,
    val hashedPassword: String,
)