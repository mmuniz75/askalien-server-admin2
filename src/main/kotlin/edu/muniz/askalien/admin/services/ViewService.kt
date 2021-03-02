package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.dao.StoreProcedureExecutor
import edu.muniz.askalien.admin.domain.View
import edu.muniz.askalien.admin.repository.ViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class ViewService {

    @Autowired
    lateinit var repo: ViewRepository

    @Autowired
    lateinit var dao: StoreProcedureExecutor

    suspend fun getViewFromYear(year: Short?): Flux<View> {
        dao.executeProc("update_view")
        return repo.findByYearOrderByMonthAsc(year)
    }

    fun getYears(): List<Short>? {
        val years: MutableList<Short> = ArrayList()
        val currentYear = Calendar.getInstance()[Calendar.YEAR]
        for (i in 2012..currentYear) {
            years.add(i.toShort())
        }
        return years
    }

    suspend fun updateView() {
        dao.executeProc("update_view")
    }
}