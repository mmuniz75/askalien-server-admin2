package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.domain.QuestionAggregate
import edu.muniz.askalien.admin.repository.QuestionFilter
import edu.muniz.askalien.admin.services.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/admin")
class QuestionController {

    @Autowired
    lateinit var service: QuestionService

    @PostMapping("/questions")
    fun getQuestions(@RequestBody filter: QuestionFilter): Flux<Question> {
        return service.getQuestions(filter)
    }

    @GetMapping("/questions")
    fun getQuestions(): Flux<Question> {
        val filter = QuestionFilter()
        filter.justThisMonth = true
        return service.getQuestions(filter)
    }

    @GetMapping("/question/{id}")
    fun getQuestion(@PathVariable id: Int): Mono<QuestionAggregate> {
        return service.getQuestion(id)
    }

    @GetMapping("/questions/count")
    fun getCountQuestions(): Mono<Long> {
        return service.getCountQuestions()
    }

    @GetMapping("/questions/{id}")
    fun getQuestionByAnswerId(@PathVariable id: Int): Flux<Question> {
        return service.getQuestionsByAnwerId(id)
    }
}