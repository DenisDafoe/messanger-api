package com.denisfominykh.messengerapi.client

import com.denisfominykh.messengerapi.client.internal.ExchangeResponse
import com.denisfominykh.messengerapi.client.internal.LoginResponse
import com.denisfominykh.messengerapi.user.UserData
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class UserServiceClient(
    webClientBuilder: WebClient.Builder
){
    private val webClient = webClientBuilder.baseUrl("http://84.201.138.104:2123").build()
//    private val webClient = webClientBuilder.baseUrl("http://localhost:2123").build()

    fun getSomeData(endpoint: String): Mono<String> {
        return webClient.get()
            .uri(endpoint)
            .retrieve()
            .bodyToMono(String::class.java)
    }

    fun <T> getSomeDataWithBody(endpoint: String, body: String, resultClass: Class<T>): Mono<T> {
        return webClient.post()
            .uri(endpoint)
            .header("Content-Type", "application/json")
            .body(Mono.just(body), String::class.java)
            .retrieve()
            .bodyToMono(resultClass)
    }
    // ---

    fun requestUserToken(userName: String, pass: String, ): String {
        val result = getSomeDataWithBody("/user/login", lazyLoginBodyStringification(userName, pass), LoginResponse::class.java)
            .block(DEFAULT_TIMEOUT)!!

        return result.userToken.token
    }

    fun requestUserDataByToken(token: String): UserData {
        val result = getSomeDataWithBody("/user/exchange", token, ExchangeResponse::class.java).block(DEFAULT_TIMEOUT)!!

        return UserData(result.id!!, result.region!!)
    }

    private fun lazyLoginBodyStringification(userName: String, password: String) =
        "{\"userName\":\"$userName\",\"hashedPassword\":\"$password\"}"

    companion object {
        val DEFAULT_TIMEOUT = Duration.parse("PT10S")!!
    }
}
