package com.denisfominykh.messengerapi.client.internal

import java.time.Instant

data class LoginResponse(
    val status: ExchangeResponse.ExchangeStatus,
    val userToken: TokenResponse,
)

data class TokenResponse(
    val token: String,
    val willExpire: Instant,
)
