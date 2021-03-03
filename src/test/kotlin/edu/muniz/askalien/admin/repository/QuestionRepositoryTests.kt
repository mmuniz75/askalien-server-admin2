package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Question
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class QuestionRepositoryTests {

    @Autowired
    lateinit var repo: QuestionRepository

    @Test
    fun getQuestionById() {
        val CONTENT = "<font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333330154419px;\">These planets are here in your own galaxy, a solar system 70 light years away. They are colonies of humanoids, very similar to this one.&nbsp;</span></font><div><font face=\"Arial, Ver"
        val SUBJECT = "In an earlier video you mentioned there are two other earth like planets getting ready to go thru stages in their development. Where are these planets located? How far along are they in their development?"
        val CREATION_DATE = LocalDateTime.of(2017, 8, 15,21,36,31,291000000)

        val question = repo.findQuestionById(83755).block()

        question?.apply {
            Assert.assertTrue(creationDate?.isEqual(CREATION_DATE)!!)
            Assert.assertEquals("81.193.48.239", ip)
            Assert.assertEquals("PORTUGAL", country)
            Assert.assertEquals("Jesus Vieira", creator)
            Assert.assertEquals("jesusvieira2000@gmail.com", email)
            Assert.assertEquals("planets", text)
            Assert.assertTrue(answer?.content?.contains(CONTENT)!!)
            Assert.assertEquals(SUBJECT, answer?.subject)
        }

    }

    @Test
    fun getQuestionsWithFeedBack() {
        val filter = QuestionFilter(justFeedback = true)
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        for (question in questions) assertNotNull(question.feedback)
    }

    @Test
    fun getQuestionsThisMonth() {
        val filter = QuestionFilter(justThisMonth = true)
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        val checkedQuestions = questions.filter{checkDate(it.creationDate!!) }

        Assert.assertTrue(questions.size == checkedQuestions.size)
    }

    @Test
    fun getQuestionsByIP() {
        val IP = "79.176.94.126"
        val filter = QuestionFilter(ipFilter = IP)
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        for (question in questions) Assert.assertEquals(IP, question.ip)
    }

    @Test
    fun getQuestionsFiltered() {
        val QUESTION = "what should we eat to have a healthy body"
        val filter = QuestionFilter(question = QUESTION)

        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        for (question in questions) Assert.assertEquals(QUESTION, question.text)
    }

    @Test
    fun getQuestionsByAnswer() {
        val filter = QuestionFilter(answerId = 198)
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size >= 378)
    }

    @Test
    fun getQuestionsByDates() {
        val filter = QuestionFilter(startDate = LocalDate.of(2012, 4, 1), endDate = LocalDate.of(2012, 4, 30))
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size == 46)
    }

    @Test
    fun getQuestionsEndDate() {
        val filter = QuestionFilter(endDate = LocalDate.of(2012, 3, 31))
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size == 201)
    }

    @Test
    fun getQuestionsStartDate() {
        val filter = QuestionFilter(startDate = LocalDate.of(2017, 10, 1))
        val questions: List<Question> = repo.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size >= 2000)
    }

    private fun checkDate(date1: LocalDateTime): Boolean {
        val now = LocalDateTime.now()
        return date1.isAfter(now) || date1.compareTo(now) == 0
    }

    @Test
    fun getFrequenceUsers() {
        val questions: List<Long> = repo.findFrequentUsers().collectList().block()!!
        Assert.assertTrue(questions.size >= 1400)
    }

    @Test
    fun getCountUsers() {
        val count: Number = repo.findCountUsers().block()!!
        Assert.assertTrue(count.toLong() >= 4800)
    }

    @Test
    fun getCountCountries() {
        val count: Number = repo.findCountCountries().block()!!
        Assert.assertTrue(count.toLong() >= 100)
    }

}