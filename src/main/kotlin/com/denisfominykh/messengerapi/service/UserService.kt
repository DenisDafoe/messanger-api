package com.denisfominykh.messengerapi.service

interface UserService {
    fun login(userName: String, pass: String): String
}