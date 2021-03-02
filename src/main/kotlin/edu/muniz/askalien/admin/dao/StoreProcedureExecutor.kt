package edu.muniz.askalien.admin.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.await
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class StoreProcedureExecutor {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    suspend fun executeProc(procedureName : String ) {
         databaseClient.sql("exec $procedureName()").await()
         println("store procedure $procedureName executed")
    }

//    suspend fun buildExecuteProc(procedureName : String )  {
//        return databaseClient.sql("exec ${procedureName}()").await()
//    }

}