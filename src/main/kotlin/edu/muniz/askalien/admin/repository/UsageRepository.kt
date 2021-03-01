package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Usage
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface  UsageRepository : ReactiveCrudRepository<Usage, Integer>{

    fun findByYearOrderByMonthAsc(year: Short): Flux<Usage>

}