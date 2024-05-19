package com.denisfominykh.messengerapi.client

import com.denisfominykh.messengerapi.client.internal.ExchangeResponse
import com.denisfominykh.messengerapi.user.UserData
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class UserServiceClient(
    webClientBuilder: WebClient.Builder
){
    // TODO: PROPERTIES
    private val webClient = webClientBuilder.baseUrl("http://158.160.3.23:8080").build()

    fun getSomeData(endpoint: String): Mono<String> {
        return webClient.get()
            .uri(endpoint)
            .retrieve()
            .bodyToMono(String::class.java)
    }

    fun <T> getSomeDataWithBody(endpoint: String, body: String, resultClass: Class<T>): Mono<T> {
        return webClient.post()
            .uri(endpoint)
            .body(Mono.just(body), String::class.java)
            .retrieve()
            .bodyToMono(resultClass)
    }
    // ---

    fun requestUserDataByToken(token: String): UserData {
        val result = getSomeDataWithBody("/user/exchange", token, ExchangeResponse::class.java).block(DEFAULT_TIMEOUT)!!

        // TODO: RETRIES ETC

        return UserData(result.id!!, result.region!!)
    }

    companion object {
        val DEFAULT_TIMEOUT = Duration.parse("PT10S")!!
    }
}
