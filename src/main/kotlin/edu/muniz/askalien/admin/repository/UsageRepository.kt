package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Usage
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface  UsageRepository : ReactiveCrudRepository<Usage, Integer>{

    fun findByYearOrderByMonthAsc(year: Short): Flux<Usage>

    @Query("select update_usage()")
    fun updateUsage() : Mono<Int>

}