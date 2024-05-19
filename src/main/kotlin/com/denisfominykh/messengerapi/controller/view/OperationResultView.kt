package com.denisfominykh.messengerapi.controller.view

import com.denisfominykh.messengerapi.result.OperationData
import com.denisfominykh.messengerapi.result.OperationResult
import java.time.Instant

data class OperationResultView (
    val status: OperationResult.OperationStatus,
    val timestamp: Instant,
    val data: OperationData?,
)