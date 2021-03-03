package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Question
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface QuestionRepository : ReactiveCrudRepository<Question, Integer>,QuestionCustomizedRepository{

    @Query("select * from Question question inner JOIN answer on question.answer_id = answer.id where question.id=:id")
    fun findQuestionById(id: Int): Mono<Question>

    fun findByAnswerIdAndFeedbackIsNotNull(id: Int): Flux<Question>

    fun findByAnswerIdAndFeedbackIsNull(id: Int): Flux<Question>

    @Query("select count(ip) "
            + "from Question "
            + "where ip not in ('','x.x.x.x') "
            + "group by ip "
            + "having count(ip) > 10")
    fun findFrequentUsers(): Flux<Long>

    @Query("SELECT count(DISTINCT question.ip) FROM Question question")
    fun findCountUsers(): Mono<Long>

    @Query("SELECT count(DISTINCT question.country) FROM Question question")
    fun findCountCountries(): Mono<Long>

    fun findByAnswerId(id: Int): Flux<Question>
}