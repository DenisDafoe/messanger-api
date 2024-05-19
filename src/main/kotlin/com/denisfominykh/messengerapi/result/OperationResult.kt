package com.denisfominykh.messengerapi.result

import java.time.Instant

data class OperationResult(
    val status: OperationStatus,
    val timestamp: Instant,
    val data: OperationData?,
) {
    enum class OperationStatus{
        SUCCESS,
        FAILED,
        FORBIDDEN,
    }
}