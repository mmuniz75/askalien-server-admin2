package edu.muniz.askalien.admin.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class StatisticsControllerTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

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
                .jsonPath("$[0].monthName").isEqualTo("January")
                .jsonPath("$[0].oldUsers").isEqualTo(20)
                .jsonPath("$[11].year").isEqualTo(2016)
                .jsonPath("$[11].month").isEqualTo(12)
                .jsonPath("$[11].numberUsers").isEqualTo(72)
                .jsonPath("$[11].newUsers").isEqualTo(60)
                .jsonPath("$[11].monthName").isEqualTo("December")
                .jsonPath("$[11].oldUsers").isEqualTo(12)

    }

}