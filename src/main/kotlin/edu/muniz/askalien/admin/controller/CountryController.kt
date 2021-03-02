package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Country
import edu.muniz.askalien.admin.services.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/admin")
class CountryController {

    @Autowired
    lateinit var service: CountryService

    @GetMapping("/countries")
    fun getCountries(): Flux<Country> {
        return service.getCountryQuestions()
    }

    @GetMapping("/countriesCode")
    fun getCountryQuestionsByCode(): Mono<Map<String, String>> {
        return service.getCountryQuestionsByCode()
    }
}