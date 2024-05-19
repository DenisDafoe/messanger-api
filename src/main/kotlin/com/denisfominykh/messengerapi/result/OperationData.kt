package com.denisfominykh.messengerapi.result

data class OperationData(
    val operationType: OperationType,
    val affectedMessageId: Long?,
    val affectedChatId: Long?,
    val requesterId: Long?
) {
    enum class OperationType {
        DESTROY,
        CREATE
    }
}