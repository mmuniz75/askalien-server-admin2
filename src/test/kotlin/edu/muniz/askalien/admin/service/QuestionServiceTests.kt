package edu.muniz.askalien.admin.service

import edu.muniz.askalien.admin.repository.QuestionFilter
import edu.muniz.askalien.admin.repository.QuestionRepository
import edu.muniz.askalien.admin.services.QuestionService
import junit.framework.Assert.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class QuestionServiceTests {

    @Autowired
    lateinit var service: QuestionService

    @Autowired
    lateinit var questionRepo: QuestionRepository

    @Test
    fun testGetAllQuestions() {
        val questions = service.getQuestions(QuestionFilter(justThisMonth = true)).collectList().block()
        assertTrue(questions?.size!! >= 325)
        val question = questions[0]
        assertNotNull(question.creationDate)
    }


}