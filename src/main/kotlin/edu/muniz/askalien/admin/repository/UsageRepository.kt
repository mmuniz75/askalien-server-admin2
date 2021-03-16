package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Usage
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface  UsageRepository : ReactiveCrudRepository<Usage, Integer>{

    fun findByYearOrderByMonthAsc(year: Short): Flux<Usage>

    @Query("""select year, sum(newusers) as newusers, sum(numberusers) as numberusers
                    from usage 
                    group by year 
                    order by 1""")
    fun countAll(): Flux<Usage>

    @Query("select update_usage()")
    fun updateUsage() : Mono<Int>

}