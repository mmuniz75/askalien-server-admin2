package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Country
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class CountryRepositoryTests {

    @Autowired
    lateinit var repo: CountryRepository

    @Test
    fun testCountryQuestions() {
        val countries: List<Country> = repo.getCountryQuestions().collectList().block()!!
        assertTrue(countries.size >= 108)
        val country = countries[0]
        assertEquals(country.country, "UNITED STATES")
        assertTrue(country.countQuestions!! > 30000)
    }

    @Test
    fun saveCountry() {
        val COUNTRY = "Country"
        val IP = "1.2.3.4.5"
        var country = Country(ip=IP, country = COUNTRY)
        country = repo.save(country).block()!!
        val id: Int = country.id!!

        try {
            country = repo.findById(id).block()!!
            Assert.assertEquals(country.ip, IP)
            Assert.assertEquals(country.country, COUNTRY)

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            repo.deleteById(id).block()
        }
    }
}