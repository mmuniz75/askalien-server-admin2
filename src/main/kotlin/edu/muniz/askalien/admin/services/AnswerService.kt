package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.domain.AnswerAggregate
import edu.muniz.askalien.admin.domain.AnswerSummary
import edu.muniz.askalien.admin.exception.NotFoundException
import edu.muniz.askalien.admin.repository.AnswerRepository
import edu.muniz.askalien.admin.repository.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AnswerService {

    @Autowired
    lateinit var repo: AnswerRepository

    @Autowired
    lateinit var videoRepository: VideoRepository

    fun getAnswers(): Flux<Answer> {
        return repo.findAllSummary()
    }

    fun getListAnswers(): Flux<Answer> {
        return repo.findAllSummaryAsc()
    }

    fun getListAnswersBloc(from: Int, to: Int): Flux<Answer> {
        return repo.findAllSummaryBloc(from, to)
    }

    fun getAnswerSummary(id: Int): Mono<AnswerSummary> {
        return repo.findSummaryById(id)
    }

    fun getAnswer(id: Int): Mono<AnswerAggregate> {
        return repo.findAnswerById(id)
    }

    fun save(answer: Answer): Mono<Answer> {
        return saveOrUpdate(answer, true)
    }

    fun update(answer: Answer): Mono<Answer> {
        return saveOrUpdate(answer, false)
    }

    private fun saveOrUpdate(answer: Answer, save: Boolean): Mono<Answer> {
       return  videoRepository.findByNumber(answer.videoNumber!!)
               .switchIfEmpty(Mono.error(NotFoundException("Video not found")))
               .map{
                     answer.videoNumber = it.id
                     answer
                }
               .flatMap{repo.save(it)}
    }

    fun getTopAnswers(feedBack: Boolean): Flux<AnswerAggregate> {
        return if (feedBack) repo.findTopAnswersJustFeedBack() else repo.findTopAnswers()
    }

    fun getCountAnswers(): Mono<Long> {
        return repo.count()
    }

    fun removeAnswer(id: Int) : Mono<Void> {
        return repo.deleteById(id)
    }
}