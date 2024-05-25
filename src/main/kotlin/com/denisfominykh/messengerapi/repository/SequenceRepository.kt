package com.denisfominykh.messengerapi.repository

import com.denisfominykh.messengerapi.model.SequenceModel
import org.springframework.data.mongodb.repository.MongoRepository

interface SequenceRepository: MongoRepository<SequenceModel, String>