package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Video
import edu.muniz.askalien.admin.repository.VideoRepository
import edu.muniz.askalien.admin.services.VideoService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class VideoControllerTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var service : VideoService

    @Autowired
    lateinit var repo: VideoRepository

    @Test
    fun getVideos() {
        val URL = "/admin/videos"
        val videos = service.getList().collectList().block()
        val count = videos?.size
        val last = count!! - 1

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(count)
                .jsonPath("$[0].id").isEqualTo(videos[0].id!!)
                .jsonPath("$[0].creationDate").isEqualTo(videos[0].creationDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .jsonPath("$[$last].id").isEqualTo(videos[last].id!!)
                .jsonPath("$[$last].creationDate").isEqualTo(videos[last].creationDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

    }

    @Test
    fun getVideo() {
        val URL = "/admin/video/100"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.number").isEqualTo(100)
                .jsonPath("$.creationDate").isEqualTo("2013-04-22")
    }

    @Test
    fun saveVideo() {
        val NUMBER = -1
        val CREATION_DATE = LocalDate.of(2100, 7, 15)

        var video = Video(number = NUMBER, creationDate = CREATION_DATE)

        var id: Int? = null
        try {
            val URL = "/admin2/video"

            webTestClient.post()
                    .uri(URL)
                    .body(Mono.just(video), Video::class.java)
                    .exchange()
                    .expectStatus().isOk()

            video = service.getVideofromNumber(NUMBER).block()!!

            id = video.id

            video.apply {
                assertEquals(number, NUMBER)
                assertTrue(creationDate?.compareTo(CREATION_DATE) == 0)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) repo.deleteById(id).block()
        }
    }

    @Test
    fun updateVideo() {
        val NUMBER = -1
        val CREATION_DATE = LocalDate.of(2100, 7, 15)
        val NUMBER_UPDATED = -10
        val CREATION_DATE_UPDATED = LocalDate.of(2110, 8, 12)

        var video = Video(number = NUMBER, creationDate = CREATION_DATE)
        video = repo.save(video).block()!!
        val id = video.id

        try {
            video = repo.findById(id!!).block()!!

            video.apply {
                number = NUMBER_UPDATED
                creationDate = CREATION_DATE_UPDATED
            }

            webTestClient.post()
                    .uri("/admin2/video")
                    .body(Mono.just(video), Video::class.java)
                    .exchange()
                    .expectStatus().isOk()

            video = repo.findById(id!!).block()!!

            video.apply {
                assertEquals(number, NUMBER_UPDATED)
                assertTrue(creationDate?.compareTo(CREATION_DATE_UPDATED) == 0)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) repo.deleteById(id).block()
        }
    }


}