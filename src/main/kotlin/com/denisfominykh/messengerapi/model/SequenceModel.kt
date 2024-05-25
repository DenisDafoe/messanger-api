package com.denisfominykh.messengerapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("sequence")
class SequenceModel (
    @Id
    val name: String,
    val idNumber: Long
)