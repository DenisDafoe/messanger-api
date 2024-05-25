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

    companion object {
        fun create(affectedMessageId: Long, affectedChatId: Long, sender: Long) = OperationData(
            OperationType.CREATE,
            affectedMessageId,
            affectedChatId,
            sender
        )

        fun delete(affectedMessageId: Long, affectedChatId: Long, sender: Long) = OperationData(
            OperationType.DESTROY,
            affectedMessageId,
            affectedChatId,
            sender
        )
    }
}