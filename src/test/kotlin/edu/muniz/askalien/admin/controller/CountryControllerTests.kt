package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.services.CountryService
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
class CountryControllerTests {

    @Autowired
    lateinit var service : CountryService

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun getCountries() {
        val URL = "/admin/countries"
        val countries = service.getCountryQuestions().collectList().block()!!
        val count = countries.size
        val last = count - 1

        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(count!!)
                .jsonPath("$[0].country").isEqualTo(countries[0].country)
                .jsonPath("$[0].countQuestions").isEqualTo(countries[0].countQuestions!!)
                .jsonPath("$[$last].country").isEqualTo(countries[last].country)
                .jsonPath("$[$last].countQuestions").isEqualTo(countries[last].countQuestions!!)

    }
}