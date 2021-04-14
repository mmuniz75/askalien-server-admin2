package edu.muniz.askalien.admin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import edu.muniz.askalien.admin.config.OAuth2SecurityConfig
import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.repository.AnswerRepository
import edu.muniz.askalien.admin.services.AnswerService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import reactor.core.publisher.Mono
import java.util.function.Consumer


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class AnswerControllerTests {

    @Autowired
    lateinit var repo: AnswerRepository

    @Autowired
    lateinit var service: AnswerService

    @Autowired
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var webTestClient: WebTestClient


    @Test
    fun getAnswers() {
        val URL = "/admin/answers"

        val answers = service.getAnswers().collectList().block()!!
        val count = answers?.size
        val last = count?.minus(1)

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(count!!)
                .jsonPath("$[0].question").isEqualTo(answers!![0].subject!!)
                .jsonPath("$[$last].question").isEqualTo(answers!![last!!].subject!!)
    }

    @Test
    fun getTopAnswersNoFeedBack() {
        getTopAnswers(false)
    }

    @Test
    fun getTopAnswersFeedBack() {
        getTopAnswers(true)
    }

    private fun getTopAnswers(feedback: Boolean) {
        val URL = "/admin/topanswers?feedback=$feedback"
        val answers = service.getTopAnswers(feedback).collectList().block()
        val count = answers?.size
        val last = count?.minus(1)

          webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(count!!)
                .jsonPath("$[0].id").isEqualTo(answers!![0].id!!.toInt())
                .jsonPath("$[0].question").isEqualTo(answers!![0].subject!!)
                .jsonPath("$[0].clicks").isEqualTo(answers!![0].clicks!!.toInt())
                .jsonPath("$[$last].id").isEqualTo(answers!![last!!].id!!.toInt())
                .jsonPath("$[$last].question").isEqualTo(answers!![last!!].subject!!)
                .jsonPath("$[$last].clicks").isEqualTo(answers!![last!!].clicks!!.toInt())

    }


    @Test
    fun getAnswer() {
        val SUBJECT = "Where did the humans on Earth orginate from?"
        val CONTENT = "<font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333330154419px;\">All the different ethnic groups found today on Earth came from different places of the universe.</span></font>"
        val URL = "/admin/answer/2"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.question").isEqualTo(SUBJECT)
                .jsonPath("$.content").exists()
                .jsonPath("$.video.creationDate").isEqualTo("2010-09-10")
                .jsonPath("$.video.number").isEqualTo(1)
    }


    @Test
    fun getAnswerSummary() {
        val SUBJECT = "Where did the humans on Earth orginate from?"
        val URL = "/admin/summary-answer/2"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.question").isEqualTo(SUBJECT)

    }


    @Test
    fun testUpdateAnswer() {
        var id: Int? = null
        try {
            val SUBJECT = "sample question"
            val CONTENT = "we dont have answer for that"
            val URL = "www.youyube.com.br"
            var answer = Answer(subject = SUBJECT, content = CONTENT, url = URL, videoNumber = 1)

            val SUBJECT_UDATED = "other question"
            val CONTENT_UDATED = "for this question we have answer"
            val URL_UDATED = "www.google.com.br"
            val VIDEO_UPDATED = 2

            answer = repo.save(answer).block()!!
            id = answer.id

            answer = repo.findById(id!!).block()!!

            answer.apply {
                content = CONTENT_UDATED
                subject = SUBJECT_UDATED
                url = URL_UDATED
                videoNumber = VIDEO_UPDATED
            }

            val URL2 = "/admin2/answer"

            webTestClient.put()
                    .uri(URL2)
                    .body(Mono.just(answer), Answer::class.java)
                    .exchange()
                    .expectStatus().isOk()


            answer = repo.findById(id).block()!!

            answer.apply {
                assertEquals(SUBJECT_UDATED, answer.subject)
                assertEquals(CONTENT_UDATED, answer.content)
                assertEquals(URL_UDATED, answer.url)
                assertTrue(VIDEO_UPDATED === answer.videoNumber)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) service.removeAnswer(id)
        }
    }

    @Test
    fun testAddAnswer() {
        val URL2 = "/admin2/answer"
        var id: Int? = null

        try {
            val SUBJECT = "sample question"
            val CONTENT = "we dont have answer for that"
            val URL = "www.www.www.www"
            val VIDEO = 1

            var answer = Answer(subject = SUBJECT, content = CONTENT, url = URL, videoNumber = VIDEO)
            webTestClient.post()
                    .uri(URL2)
                    .body(Mono.just(answer), Answer::class.java)
                    .exchange()
                    .expectStatus().isOk()

            val answer2 = repo.findByUrl(URL).block()!!
            id = answer2.id

            answer2.apply {
                assertEquals(SUBJECT, subject)
                assertEquals(CONTENT, content)
                assertEquals(URL, url)
                assertTrue(VIDEO === videoNumber)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) service.removeAnswer(id).block()
        }
    }


}