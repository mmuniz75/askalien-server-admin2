package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.Video
import edu.muniz.askalien.admin.repository.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class VideoService {

    @Autowired
    lateinit var videoRepo: VideoRepository

    fun getVideofromNumber(number: Int): Mono<Video> {
        return videoRepo.findByNumber(number)
    }

    fun getList(): Flux<Video> {
        return videoRepo.findAllByOrderByNumberDesc()
    }

    fun save(video: Video): Mono<Video> {
        return videoRepo.save(video)
    }
}