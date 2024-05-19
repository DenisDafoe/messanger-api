package com.denisfominykh.messengerapi.repository

import com.denisfominykh.messengerapi.model.ChatModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : MongoRepository<ChatModel, Long>