package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Video
import edu.muniz.askalien.admin.services.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class VideoController {

    @Autowired
    lateinit var service: VideoService

    @GetMapping("/admin/videos")
    fun getVideos(): Flux<Video> {
        return service.getList()
    }

    @GetMapping("/admin/video/{id}")
    fun getAnswerDetail(@PathVariable id: Int): Mono<Video> {
        return service.getVideofromNumber(id)
    }

    @PostMapping("/admin2/video")
    fun addAnswer(@RequestBody video: Video): Mono<Video> {
        return service.save(video)
    }
}