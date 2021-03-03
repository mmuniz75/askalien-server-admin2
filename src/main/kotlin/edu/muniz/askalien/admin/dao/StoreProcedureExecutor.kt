package edu.muniz.askalien.admin.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.FetchSpec
import org.springframework.r2dbc.core.await
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Service
class StoreProcedureExecutor {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    fun executeProc(procedureName : String ): Mono<MutableMap<String, Any>> {
         return databaseClient.sql("select $procedureName()").fetch().first().toMono()

    }

}