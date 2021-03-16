package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.View
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ViewRepository : ReactiveCrudRepository<View, Integer>{

    fun findByYearOrderByMonthAsc(year: Short?): Flux<View>

    @Query("select update_view()")
    fun updateView() : Mono<Int>

    @Query("""select year, sum(number) as number  
                   from view_question  
                   group by year 
                   order by 1""")
    fun countAll(): Flux<View>;

}