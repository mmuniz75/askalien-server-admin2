package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Country
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CountryRepository : ReactiveCrudRepository<Country, Int>{

    @Query("SELECT question.country ,count(question.country) as countQuestions "
            + "FROM Question question "
            + "WHERE question.country "
            + "NOT IN ('','undefined','Unknown Country') "
            + "GROUP BY question.country "
            + "ORDER BY 2 desc")
    fun getCountryQuestions(): Flux<Country>
}