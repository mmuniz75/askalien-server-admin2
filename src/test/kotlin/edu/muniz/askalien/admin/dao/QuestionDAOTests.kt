package edu.muniz.askalien.admin.dao

import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.repository.QuestionFilter
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime


@RunWith(SpringRunner::class)
@SpringBootTest
class QuestionDAOTests {

    @Autowired
    lateinit var dao: QuestionDAO

    @Test
    fun getQuestionsWithFeedBack() {
        val filter = QuestionFilter(justFeedback = true)
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        for (question in questions) assertNotNull(question.feedback)
    }

    @Test
    fun getQuestionsThisMonth() {
        val filter = QuestionFilter(justThisMonth = true)
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        val checkedQuestions = questions.filter{it.creationDate!!.month == LocalDate.now().month }

        Assert.assertTrue(questions.size == checkedQuestions.size)
    }

    @Test
    fun getQuestionsByIP() {
        val IP = "79.176.94.126"
        val filter = QuestionFilter(ipFilter = IP)
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        for (question in questions) Assert.assertEquals(IP, question.ip)
    }

    @Test
    fun getQuestionsFiltered() {
        val QUESTION = "what should we eat to have a healthy body"
        val filter = QuestionFilter(question = QUESTION)

        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        for (question in questions) Assert.assertEquals(QUESTION, question.text)
    }

    @Test
    fun getQuestionsByAnswer() {
        val filter = QuestionFilter(answerId = 198)
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size >= 378)
    }

    @Test
    fun getQuestionsByDates() {
        val filter = QuestionFilter(startDate = LocalDate.of(2012, 4, 1), endDate = LocalDate.of(2012, 4, 30))
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size == 46)
    }

    @Test
    fun getQuestionsEndDate() {
        val filter = QuestionFilter(endDate = LocalDate.of(2012, 3, 31))
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size == 201)
    }

    @Test
    fun getQuestionsStartDate() {
        val filter = QuestionFilter(startDate = LocalDate.of(2017, 10, 1))
        val questions: List<Question> = dao.findAll(filter).collectList().block()!!
        Assert.assertTrue(questions.size >= 2000)
    }

    private fun checkDate(date1: LocalDateTime): Boolean {
        val now = LocalDateTime.now()
        return date1.isAfter(now) || date1.compareTo(now) == 0
    }

}