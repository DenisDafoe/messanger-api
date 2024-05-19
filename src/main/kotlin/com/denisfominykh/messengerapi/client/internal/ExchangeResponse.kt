package com.denisfominykh.messengerapi.client.internal

import com.denisfominykh.messengerapi.region.Region

data class ExchangeResponse (
    val exchangeStatus: ExchangeStatus,
    val id: Long? = null,
    val region: Region? = null,
) {
    enum class ExchangeStatus {
        SUCCESSFUL,
        NOT_FOUND,
        EXPIRED,
    }
}