package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.repository.QuestionFilter
import edu.muniz.askalien.admin.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class QuestionService {

    @Autowired
    lateinit var  questionRepo: QuestionRepository

    fun getQuestions(filter: QuestionFilter): Flux<Question> {
        return questionRepo.findAll(filter)
    }

    fun getQuestion(id: Int): Mono<Question> {
        return questionRepo.findQuestionById(id)
    }

    fun getCountQuestions(): Mono<Long> {
        return questionRepo.count()
    }

    fun getFrequentUsers(): Mono<Long> {
        return questionRepo.findFrequentUsers()
                .count()
    }

    fun getCountUsers(): Mono<Long> {
        return questionRepo.findCountUsers()
    }

    fun getCountCountries(): Mono<Long> {
        return questionRepo.findCountCountries()
    }

    fun getQuestionsByAnwerId(id: Int): Flux<Question> {
        return questionRepo.findByAnswerId(id)
    }
}