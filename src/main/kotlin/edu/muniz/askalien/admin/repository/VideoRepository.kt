package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Video
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface VideoRepository : ReactiveCrudRepository<Video, Int>{

    fun findByNumber(number: Int): Mono<Video>
    fun findAllByOrderByNumberDesc(): Flux<Video>
}