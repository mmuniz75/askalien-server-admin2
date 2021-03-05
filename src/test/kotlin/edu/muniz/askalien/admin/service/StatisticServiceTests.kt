package edu.muniz.askalien.admin.service

import edu.muniz.askalien.admin.services.StatisticService
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class StatisticServiceTests {

    @Autowired
    lateinit var service: StatisticService

    @Test
    fun testAccessStatistic() {
        val dto = service.getAccessStatistic().block()
        assertTrue(dto?.totalQuestion!! > 90000)
        assertTrue(dto?.totalFrequentUsers!! >= 1400)
        assertTrue(dto?.totalUsers!! >= 4800)
        assertTrue(dto?.totalAnswers!! >= 1200)
        assertTrue(dto?.totalCountries!! >= 100)
    }

}