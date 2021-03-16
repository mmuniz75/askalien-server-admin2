package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.View
import edu.muniz.askalien.admin.repository.ViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class ViewService {

    @Autowired
    lateinit var repo: ViewRepository


    fun getViewFromYear(year: Short?): Flux<View> {
        return repo.updateView()
                .thenMany(repo.findByYearOrderByMonthAsc(year))
    }

    fun getYears(): List<Short>? {
        val years: MutableList<Short> = ArrayList()
        val currentYear = Calendar.getInstance()[Calendar.YEAR]
        for (i in 2012..currentYear) {
            years.add(i.toShort())
        }
        return years
    }

    fun updateView(): Mono<Int> {
        return repo.updateView()
    }

    fun getViewAllYears(): Flux<View> {
        return repo.updateView()
                .thenMany(repo.countAll())
    }
}