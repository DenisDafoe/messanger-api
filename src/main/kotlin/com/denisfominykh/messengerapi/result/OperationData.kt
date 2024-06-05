package com.denisfominykh.messengerapi.result

data class OperationData(
    val operationType: OperationType,
    val affectedMessageId: String?,
    val affectedChatId: Long?,
    val requesterId: Long?
) {
    enum class OperationType {
        DESTROY,
        CREATE
    }

    companion object {
        fun create(affectedMessageId: String, affectedChatId: Long, sender: Long) = OperationData(
            OperationType.CREATE,
            affectedMessageId,
            affectedChatId,
            sender
        )

        fun delete(affectedMessageId: String, affectedChatId: Long, sender: Long) = OperationData(
            OperationType.DESTROY,
            affectedMessageId,
            affectedChatId,
            sender
        )
    }
}