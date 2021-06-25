package edu.muniz.askalien.admin.services

import edu.muniz.askalien.admin.domain.Country
import edu.muniz.askalien.admin.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.Locale

import java.util.function.Function
import kotlin.collections.HashMap

@Service
class CountryService {

    @Autowired
    lateinit var mapCountries: MutableMap<String, String>

    fun getCountryCode(country: String): String? {
        var code = mapCountries[country]
        if (code == null) {
            println("mapCountries.put(\"$country\",\"\");")
            code = country
        }
        return code
    }

    @Autowired
    lateinit var countryRepo: CountryRepository

    fun getCountryQuestions(): Flux<Country> {
        return countryRepo.getCountryQuestions()
    }

    fun getCountryQuestionsByCode(): Mono<Map<String, String>> {
        return countryRepo.getCountryQuestions()
                .collectMap(Function<Country, String> { country: Country -> getCountryCode(country.country) },
                        Function<Country, String> { country: Country -> country.countQuestions.toString() })
    }
}