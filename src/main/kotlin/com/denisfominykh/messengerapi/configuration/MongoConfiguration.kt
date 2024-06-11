package com.denisfominykh.messengerapi.configuration

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ReadPreference
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.transaction.support.TransactionTemplate
import java.util.concurrent.TimeUnit

@Configuration
@EnableMongoRepositories(basePackages = ["com.denisfominykh.messengerapi.repository"])
class MongoConfiguration : AbstractMongoClientConfiguration() {
    override fun mongoClient(): MongoClient {
        val mongoClientSettings =
            MongoClientSettings.builder()
                .applyToConnectionPoolSettings {
                }
                .applyToSocketSettings {
                    it.connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                }
                .writeConcern(
                    WriteConcern(1)
                        .withJournal(false)
                        .withWTimeout(5, TimeUnit.SECONDS),
                ).credential(
                    MongoCredential.createScramSha256Credential(
                        "dionis",
                        "admin",
                        "I_h@t3_scr1pt_k1dd13s".toCharArray(),
                    ),
                )
                .readPreference(ReadPreference.secondary())
                .applyConnectionString(
                    ConnectionString(
                        "mongodb://dionis:I_h%40t3_scr1pt_k1dd13s@" +
                            "rc1a-1ygi1svtru3v8isl.mdb.yandexcloud.net:27018," +
                            "rc1b-m2t30uxwf7crilxu.mdb.yandexcloud.net:27018," +
                            "rc1b-qvq903kx3s5stmaa.mdb.yandexcloud.net:27018/" +
                            "userdata?authSource=admin",
                    ),
                )
        return MongoClients.create(mongoClientSettings.build())
    }

    override fun getDatabaseName(): String {
        return "userdata"
    }

    @Bean
    fun mongoTransactionManager(databaseFactory: MongoDatabaseFactory): MongoTransactionManager {
        return MongoTransactionManager(databaseFactory)
    }

    override fun autoIndexCreation(): Boolean {
        return true
    }

    @Bean
    fun mongoTransactionTemplate(transactionManager: MongoTransactionManager): TransactionTemplate {
        return TransactionTemplate(transactionManager)
    }
}
