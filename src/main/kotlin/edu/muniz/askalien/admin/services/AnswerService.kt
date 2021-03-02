package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.repository.AnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AnswerService {

    @Autowired
    lateinit var repo: AnswerRepository

    @Autowired
    lateinit var videoService: VideoService

    fun getAnswers(): Flux<Answer> {
        return repo.findAllSummary()
    }

    fun getListAnswers(): Flux<Answer> {
        return repo.findAllSummaryAsc()
    }

    fun getListAnswersBloc(from: Int, to: Int): Flux<Answer> {
        return repo.findAllSummaryBloc(from, to)
    }

    fun getAnswerSummary(id: Int): Mono<Answer> {
        return repo.findById(id)
    }

    fun getAnswer(id: Int): Mono<Answer> {
        return repo.findAnswerById(id)
    }

    fun save(answer: Answer): Mono<Answer> {
        return saveOrUpdate(answer, true)
    }

    fun update(answer: Answer): Mono<Answer> {
        return saveOrUpdate(answer, false)
    }

    private fun saveOrUpdate(answer: Answer, save: Boolean): Mono<Answer> {
       return  videoService.getVideofromNumber(answer.videoNumber!!)
               .switchIfEmpty(throw IllegalStateException("Video ${answer.videoNumber} does not exists"))
               .then(repo.save(answer))
    }

    fun getTopAnswers(feedBack: Boolean): Flux<Answer> {
        return if (feedBack) repo.findTopAnswersJustFeedBack() else repo.findTopAnswers()
    }

    fun getCountAnswers(): Mono<Long> {
        return repo.count()
    }

    fun removeAnswer(id: Int) : Mono<Void> {
        return repo.deleteById(id)
    }
}