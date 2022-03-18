package edu.muniz.askalien.admin.services


import edu.muniz.askalien.admin.*
import edu.muniz.askalien.admin.domain.Country
import edu.muniz.askalien.admin.native.NativeLanguageFeature
import edu.muniz.askalien.admin.repository.CountryRepository
import org.graalvm.nativeimage.ImageSingletons
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import java.util.function.Function


@Service
class CountryService {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val mapCountries: MutableMap<String, String> = HashMap()

    fun getAvailableLocales(): Array<Locale> {
        if (Objects.nonNull(System.getenv("NATIVE"))) {
            if (ImageSingletons.contains(NativeLanguageFeature.SupportedLocales::class.java)) {
                val lookup: NativeLanguageFeature.SupportedLocales =
                    ImageSingletons.lookup(NativeLanguageFeature.SupportedLocales::class.java)
                return lookup.locales
            }
        }

        return Locale.getAvailableLocales()
    }

    constructor(){
        Locale.setDefault(Locale.ENGLISH)

        for (locale in getAvailableLocales()) {
            if (locale.country != null && !locale.country.equals("")) {
                println(locale.displayCountry.uppercase(Locale.getDefault()))
                mapCountries[locale.displayCountry.uppercase(Locale.getDefault())] = locale.country.lowercase(Locale.getDefault())
            }
        }

        mapCountries.put("MACEDONIA","mk");
        mapCountries.put("HONG KONG","hk");
        mapCountries.put("TRINIDAD AND TOBAGO","tt");
        mapCountries.put("REPUBLIC OF LITHUANIA","lt");
        mapCountries.put("REPUBLIC OF KOREA","kr");
        mapCountries.put("SYRIAN ARAB REPUBLIC","sy");
        mapCountries.put("CZECH REPUBLIC","cz");
        mapCountries.put("BOSNIA AND HERZEGOVINA","ba");
        mapCountries.put("EUROPEAN UNION","eu");
        mapCountries.put("ANONYMOUS PROXY","a1");
        mapCountries.put("ASIA/PACIFIC REGION","ap");
        mapCountries.put("YUGOSLAVIA","yu");

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