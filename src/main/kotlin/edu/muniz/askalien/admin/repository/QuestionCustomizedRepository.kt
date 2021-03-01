package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Question
import reactor.core.publisher.Flux

interface QuestionCustomizedRepository {

    fun findAll(filter: QuestionFilter): Flux<Question>

}