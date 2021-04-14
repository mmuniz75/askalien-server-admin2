package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.dao.StoreProcedureExecutor
import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.repository.QuestionRepository
import edu.muniz.askalien.admin.repository.UsageRepository
import edu.muniz.askalien.admin.repository.ViewRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class StatisticsControllerTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var repo: UsageRepository

    @Autowired
    lateinit var repoView: ViewRepository

    @Autowired
    lateinit var questionRepo: QuestionRepository

    @Autowired
    lateinit var dao: StoreProcedureExecutor

    @Test
    fun getStatistics() {
        val URL = "/admin/statistics"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalQuestion").isNotEmpty
                .jsonPath("$.totalFrequentUsers").isNotEmpty
                .jsonPath("$.totalUsers").isNotEmpty
                .jsonPath("$.totalAnswers").isNotEmpty
                .jsonPath("$.totalCountries").isNotEmpty

    }

    @Test
    fun getUsage() {
        val URL = "/admin/usage/2016"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(12)
                .jsonPath("$[0].year").isEqualTo(2016)
                .jsonPath("$[0].month").isEqualTo(1)
                .jsonPath("$[0].numberUsers").isEqualTo(69)
                .jsonPath("$[0].newUsers").isEqualTo(49)
                .jsonPath("$[0].monthName").isEqualTo("Jan")
                .jsonPath("$[0].oldUsers").isEqualTo(20)
                .jsonPath("$[11].year").isEqualTo(2016)
                .jsonPath("$[11].month").isEqualTo(12)
                .jsonPath("$[11].numberUsers").isEqualTo(72)
                .jsonPath("$[11].newUsers").isEqualTo(60)
                .jsonPath("$[11].monthName").isEqualTo("Dec")
                .jsonPath("$[11].oldUsers").isEqualTo(12)

    }

    @Test
    fun getViews() {
        val URL = "/admin/view/2016"

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(12)
                .jsonPath("$[0].year").isEqualTo(2016)
                .jsonPath("$[0].month").isEqualTo(1)
                .jsonPath("$[0].number").isEqualTo(1104)
                .jsonPath("$[0].monthName").isEqualTo("Jan")
                .jsonPath("$[11].year").isEqualTo(2016)
                .jsonPath("$[11].month").isEqualTo(12)
                .jsonPath("$[11].number").isEqualTo(992)
                .jsonPath("$[11].monthName").isEqualTo("Dec")

    }

    @Test
    fun testUpdateUsage() {
        val now = LocalDate.now();
        val year = now.year
        val month = now.month.value - 1

        var questionId : Integer? = null
        try{

            val statitcs = repo.findByYearOrderByMonthAsc(year.toShort()).collectList().block()
            val numberUsers = statitcs?.get(month)?.numberUsers!!
            val newUsers = statitcs?.get(month)?.newUsers!!

            var question = Question(text = "some question", ip = "1.2.3.4.5")
            question = questionRepo.save(question).block()!!
            questionId = question.id

            val URL = "/admin/usage/${year}"
            webTestClient.get()
                    .uri(URL)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$[$month].numberUsers").isEqualTo(numberUsers!! + 1)
                    .jsonPath("$[$month].newUsers").isEqualTo(newUsers + 1)

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex

        } finally {
            if (questionId!! > 0) {
                questionRepo.deleteById(questionId!!).block()
                dao.executeProc("update_usage").block()
            }
        }

    }

    @Test
    fun testUpdateView() {
        val now = LocalDate.now();
        val year = now.year
        val month = now.month.value - 1

        var questionId : Integer? = null
        try{

            val statitcs = repoView.findByYearOrderByMonthAsc(year.toShort()).collectList().block()
            val number = statitcs?.get(month)?.number!!

            var question = Question(text = "some question", ip = "1.2.3.4.5")
            question = questionRepo.save(question).block()!!
            questionId = question.id

            val URL = "/admin/view/${year}"
            webTestClient.get()
                    .uri(URL)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$[$month].number").isEqualTo(number!! + 1)

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex

        } finally {
            if (questionId!! > 0) {
                questionRepo.deleteById(questionId!!).block()
                dao.executeProc("update_view").block()
            }
        }

    }

}