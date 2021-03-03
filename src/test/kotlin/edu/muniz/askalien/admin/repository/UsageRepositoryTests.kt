package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.dao.StoreProcedureExecutor
import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.domain.Usage
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest
class UsageRepositoryTests {

    @Autowired
    lateinit var repo: UsageRepository

    @Autowired
    lateinit var questionRepo: QuestionRepository

    @Autowired
    lateinit var dao: StoreProcedureExecutor

    @Test
    fun testCountryQuestions() {
        val usages: List<Usage> = repo.findByYearOrderByMonthAsc(2015.toShort()).collectList().block()!!
        assertTrue(usages.size == 12)
        val usage = usages[0]

        usage.apply {
            assertTrue(month!!.toInt() == 1)
            assertTrue(year!!.toInt() == 2015)
            assertTrue(newUsers == 100)
            assertTrue(numberUsers == 133)
        }

    }

    @Test
    fun testUpdateUsage() {
        val date = LocalDate.now()
        val month = date.monthValue
        val year = date.year

        var usages = repo.findByYearOrderByMonthAsc(year.toShort()).collectList().block()
        var usage = usages!![month - 1]
        val countUsers = usage.numberUsers
        val countNewUsers = usage.newUsers

        var questionId: Integer? = Integer(-1)
        try {
            var question = Question(text = "some question", ip = "1.2.3.4.5")

            question = questionRepo.save(question).block()!!
            questionId = question.id

            dao.executeProc("update_usage").block()
            usages = repo.findByYearOrderByMonthAsc(year.toShort()).collectList().block()
            usage = usages!![month - 1]

            usage.apply {
                assertTrue(numberUsers!! == (countUsers!! + 1))
                assertTrue(newUsers!! == (countNewUsers!! + 1))
            }

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

}