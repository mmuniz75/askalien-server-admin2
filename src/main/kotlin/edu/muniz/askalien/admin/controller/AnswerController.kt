package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.domain.AnswerAggregate
import edu.muniz.askalien.admin.domain.AnswerSummary
import edu.muniz.askalien.admin.services.AnswerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class AnswerController {

    @Autowired
    lateinit var service: AnswerService

    @GetMapping("/admin/answers")
    fun getAnswers(): Flux<Answer> {
        return service.getAnswers()
    }

    @GetMapping("/answers")
    fun getListAnswers(): Flux<Answer> {
        return service.getListAnswers()
    }

    @GetMapping("/answers/{from}/{to}")
    fun getListAnswersBloc(@PathVariable from: Int, @PathVariable to: Int): Flux<Answer> {
        return service.getListAnswersBloc(from, to)
    }

    @GetMapping("/admin/summary-answer/{id}")
    fun getAnswer(@PathVariable id: Int): Mono<AnswerSummary> {
        return service.getAnswerSummary(id)
    }

    @GetMapping("/admin/answer/{id}")
    fun getAnswerDetail(@PathVariable id: Int): Mono<AnswerAggregate> {
        return service.getAnswer(id)
    }

    @GetMapping("/answer/{id}")
    fun getAnswerDetail2(@PathVariable id: Int): Mono<AnswerAggregate> {
        return service.getAnswer(id)
    }

    @PutMapping("/admin2/answer")
    fun updateAnswer(@RequestBody answer: Answer): Mono<Answer> {
        return service.update(answer)
    }

    @PostMapping("/admin2/answer")
    fun addAnswer(@RequestBody answer: Answer): Mono<Answer> {
        return service.save(answer)
    }

    @GetMapping("/admin/topanswers")
    fun getTopAnswers(@RequestParam feedback: Boolean): Flux<AnswerAggregate> {
        return service.getTopAnswers(feedback)
    }
}