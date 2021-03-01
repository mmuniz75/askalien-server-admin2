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

    private val mapCountries: MutableMap<String, String> = HashMap()

    constructor(){

        for (locale in Locale.getAvailableLocales()) {
            if (locale.country != null && !locale.country.equals("")) {
                mapCountries[locale.displayCountry.toUpperCase()] = locale.country.toLowerCase()
            }
        }

        mapCountries["NEPAL"] = "ne"
        mapCountries["BARBADOS"] = "bb"
        mapCountries["GEORGIA"] = "ka"
        mapCountries["TRINIDAD AND TOBAGO"] = "tt"
        mapCountries["KAZAKHSTAN"] = "kk"
        mapCountries["CAPE VERDE"] = "cv"
        mapCountries["MADAGASCAR"] = "mg"
        mapCountries["GUAM"] = "gu"
        mapCountries["NIGERIA"] = "ng"
        mapCountries["GUYANA"] = "gy"
        mapCountries["SYRIAN ARAB REPUBLIC"] = "sy"
        mapCountries["KYRGYZSTAN"] = "kg"
        mapCountries["BOTSWANA"] = "bw"
        mapCountries["AZERBAIJAN"] = "az"
        mapCountries["SURINAME"] = "sr"
        mapCountries["ARMENIA"] = "hy"
        mapCountries["BELIZE"] = "bz"
        mapCountries["BANGLADESH"] = "bd"
        mapCountries["TANZANIA"] = "tz"
        mapCountries["MONGOLIA"] = "mn"
        mapCountries["IRAN"] = "ir"
        mapCountries["ZIMBABWE"] = "zw"
        mapCountries["CAMBODIA"] = "kh"
        mapCountries["SRI LANKA"] = "lk"
        mapCountries["MOZAMBIQUE"] = "mz"
        mapCountries["GEORGIA"] = "ge"
        mapCountries["KAZAKHSTAN"] = "kz"
        mapCountries["NEPAL"] = "np"
        mapCountries["ARMENIA"] = "am"
        mapCountries["KENYA"] = "ke"
        //mapCountries.put("REPUBLIC OF LITHUANIA","lt");
        mapCountries["CAYMAN ISLANDS"] = "ky"
    }

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