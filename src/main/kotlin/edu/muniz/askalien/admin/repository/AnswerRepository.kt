package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.domain.AnswerAggregate
import edu.muniz.askalien.admin.domain.AnswerSummary
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AnswerRepository : ReactiveCrudRepository<Answer, Int> {

    @Query("""select answer.id, answer.subject, answer.content, answer.url, video.number as numberFromVideo,video.creationDate as datefromvideo 
                    from Answer answer INNER JOIN video ON answer.videoNumber = video.id where answer.id=:id
                 """)
    fun findAnswerById(id: Int): Mono<AnswerAggregate>

    //override fun findById(id: Int): Mono<Answer>

    @Query("select * from Answer answer order by id desc ")
    fun findAllSummary(): Flux<Answer>

    @Query("select * from Answer answer order by id asc ")
    fun findAllSummaryAsc(): Flux<Answer>

    @Query("select * from Answer answer where id>=?1 and id<=?2 order by id asc ")
    fun findAllSummaryBloc(from: Int, to: Int): Flux<Answer>

    @Query("SELECT answer.id, answer.subject, count(question.id) as clicks  "
            + "FROM question inner join answer ON question.answer_id = answer.id "
            + "GROUP BY answer.id, answer.subject "
            + "ORDER BY 3 desc")
    fun findTopAnswers(): Flux<AnswerAggregate>

    @Query("SELECT answer.id, answer.subject, count(question.id) as clicks "
            + "FROM question inner join answer ON question.answer_id = answer.id "
            + "WHERE question.feedback is not null "
            + "GROUP BY answer.id, answer.subject "
            + "ORDER BY 3 desc")
    fun findTopAnswersJustFeedBack(): Flux<AnswerAggregate>

    @Query("select * from Answer answer INNER JOIN video ON answer.videoNumber = video.id where answer.url=:url")
    fun findByUrl(url: String): Mono<AnswerAggregate>

    @Query("select id,subject from Answer where id=:id")
    fun findSummaryById(id: Int): Mono<AnswerSummary>

}