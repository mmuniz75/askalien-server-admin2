package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.dao.StoreProcedureExecutor
import edu.muniz.askalien.admin.domain.Usage
import edu.muniz.askalien.admin.repository.UsageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class UsageService {

    @Autowired
    lateinit var repo: UsageRepository

    @Autowired
    lateinit var dao:StoreProcedureExecutor

    fun getUsageFromYear(year: Short): Flux<Usage> {
        return  repo.updateUsage()
                .thenMany(repo.findByYearOrderByMonthAsc(year))
    }

    fun getUsageAllYears(): Flux<Usage> {
        return  repo.updateUsage()
                .thenMany(repo.countAll())
    }

    fun getYears(): List<Short> {
        val years: MutableList<Short> = ArrayList()
        val currentYear = Calendar.getInstance()[Calendar.YEAR]
        for (i in 2012..currentYear) {
            years.add(i.toShort())
        }
        return years
    }

    fun updateUsage(): Mono<Int> {
        return dao.executeProc("update_usage")
    }
}



