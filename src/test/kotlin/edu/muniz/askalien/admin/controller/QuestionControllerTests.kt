package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.dao.QuestionDAO
import edu.muniz.askalien.admin.repository.QuestionFilter
import edu.muniz.askalien.admin.repository.QuestionRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class QuestionControllerTests {

    @Autowired
    lateinit var repo : QuestionRepository

    @Autowired
    lateinit var dao : QuestionDAO

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun testGetQuestions() {
        val URL = "/admin/questions"
        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isNotEmpty
                .jsonPath("$[0].ip").isNotEmpty
                .jsonPath("$[0].country").isNotEmpty
                .jsonPath("$[0].creationDate").isNotEmpty
                .jsonPath("$[0].text").isNotEmpty

    }

    @Test
    fun testGetQuestion() {
        val CONTENT = "<font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333330154419px;\">These planets are here in your own galaxy, a solar system 70 light years away. They are colonies of humanoids, very similar to this one.&nbsp;</span></font><div><font face=\"Arial, Ver"
        val SUBJECT = "In an earlier video you mentioned there are two other earth like planets getting ready to go thru stages in their development. Where are these planets located? How far along are they in their development?"
        val URL = "/admin/question/83755"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.ip").isEqualTo("81.193.48.239")
                .jsonPath("$.country").isEqualTo("PORTUGAL")
                .jsonPath("$.creationDate").isEqualTo("08/15/2017 09:36")
                .jsonPath("$.text").isEqualTo("planets")
                .jsonPath("$.email").isEqualTo("jesusvieira2000@gmail.com")
                .jsonPath("$.feedback").isEqualTo("Can I be transfered?")
                .jsonPath("$.creator").isEqualTo("Jesus Vieira")
                .jsonPath("$.answer.question").isEqualTo(SUBJECT)
                .jsonPath("$.answer.content").isNotEmpty
    }


    @Test
    fun getQuestionsWithFeedBack() {
        val URL = "/admin/questions"
        val filter = QuestionFilter(justFeedback = true)

        webTestClient.post()
                .uri(URL)
                .body(Mono.just(filter), QuestionFilter::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].feedback").isNotEmpty
                .jsonPath("$[1].feedback").isNotEmpty
                .jsonPath("$[2].feedback").isNotEmpty
                .jsonPath("$[3].feedback").isNotEmpty

    }

    @Test
    fun getQuestionsByIP() {
        val IP = "79.176.94.126"
        val filter = QuestionFilter(ipFilter = IP)
        val URL = "/admin/questions"

        webTestClient.post()
                .uri(URL)
                .body(Mono.just(filter), QuestionFilter::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].ip").isEqualTo(IP)
                .jsonPath("$[1].ip").isEqualTo(IP)

    }


    @Test
    fun getQuestionsFiltered() {
        val QUESTION = "what should we eat to have a healthy body"
        val filter = QuestionFilter(question = QUESTION)
        val URL = "/admin/questions"

        webTestClient.post()
                .uri(URL)
                .body(Mono.just(filter), QuestionFilter::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].text").isEqualTo(QUESTION)
                .jsonPath("$[1].text").isEqualTo(QUESTION)
                .jsonPath("$[2].text").isEqualTo(QUESTION)
    }


    @Test
    fun getQuestionsByAnswer() {
        val filter = QuestionFilter(answerId = 198)
        val questions = dao.findAll(filter).collectList().block()!!

        val count = questions.size
        val last = count - 1
        val URL = "/admin/questions"

        webTestClient.post()
                .uri(URL)
                .body(Mono.just(filter), QuestionFilter::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(count!!)
                .jsonPath("$[0].text").isEqualTo(questions[0].text!!)
                .jsonPath("$[$last].text").isEqualTo(questions[last].text!!)
    }


    @Test
    fun getQuestionsByDates() {
        val filter = QuestionFilter(startDate = LocalDate.of(2012, 4, 1), endDate = LocalDate.of(2012, 4, 30))
        val URL = "/admin/questions"

        webTestClient.post()
                .uri(URL)
                .body(Mono.just(filter), QuestionFilter::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(46)

    }

}