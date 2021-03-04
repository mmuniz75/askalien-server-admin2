package edu.muniz.askalien.admin.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class StoreProcedureExecutor {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    fun executeProc(procedureName: String): Mono<Int> {
         return databaseClient.sql("select $procedureName()").fetch().rowsUpdated()
    }

}