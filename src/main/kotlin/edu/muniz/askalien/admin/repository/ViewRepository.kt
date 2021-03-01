package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.View
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ViewRepository : ReactiveCrudRepository<View, Integer>{

    fun findByYearOrderByMonthAsc(year: Short?): Flux<View>

}