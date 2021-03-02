package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.dto.StatisticDTO
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class StatisticService {

    @Autowired
    lateinit var answerService: AnswerService

    @Autowired
    lateinit var questionService: QuestionService

    fun getAccessStatistic_old(): Mono<StatisticDTO> {
        var dto = StatisticDTO()

        return questionService.getCountQuestions()
            .doOnNext { dto.totalQuestion = it }
            .then(questionService.getFrequentUsers())
            .doOnNext { dto.totalFrequentUsers = it }
            .then(questionService.getCountUsers())
            .doOnNext { dto.totalUsers = it }
            .then(questionService.getCountCountries())
            .doOnNext { dto.totalCountries = it }
            .then(answerService.getCountAnswers())
            .doOnNext { dto.totalAnswers = it }
            .then(Mono.just(dto))
    }

    fun getAccessStatistic(): Mono<StatisticDTO> {

        return questionService.getCountQuestions()
               .map { StatisticDTO(totalQuestion = it) }
               .flatMap { Mono.zip(Mono.just(it), questionService.getFrequentUsers()) }
               .map {
                       val dto = it.t1
                       dto.totalFrequentUsers = it.t2
                       dto
               }
               .flatMap { Mono.zip(Mono.just(it), questionService.getCountUsers()) }
                .map {
                    val dto = it.t1
                    dto.totalUsers = it.t2
                    dto
                }
                .flatMap { Mono.zip(Mono.just(it), questionService.getCountCountries()) }
                .map {
                    val dto = it.t1
                    dto.totalCountries = it.t2
                    dto
                }
                .flatMap { Mono.zip(Mono.just(it), answerService.getCountAnswers()) }
                .map {
                    val dto = it.t1
                    dto.totalAnswers = it.t2
                    dto
                }
        }
 }



