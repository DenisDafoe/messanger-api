package com.denisfominykh.messengerapi.serviceimpl

import com.denisfominykh.messengerapi.client.UserServiceClient
import com.denisfominykh.messengerapi.service.UserService
import org.springframework.stereotype.Service

@Service
class UserLoginServiceImpl(
    private val clientService: UserServiceClient,
): UserService {
    override fun login(userName: String, pass: String): String {
        return clientService.requestUserToken(userName, pass)
    }
}