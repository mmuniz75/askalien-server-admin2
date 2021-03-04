package edu.muniz.askalien.admin.service

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.repository.AnswerRepository
import edu.muniz.askalien.admin.repository.QuestionRepository
import edu.muniz.askalien.admin.services.AnswerService
import junit.framework.Assert.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class AnswerServiceTests {

    @Autowired
    lateinit var service: AnswerService

    @Autowired
    lateinit var repo: AnswerRepository

    @Autowired
    lateinit var questionRepo: QuestionRepository

    @Test
    fun testGetAllAnswers() {
        val anwers = service.getAnswers().collectList().block()!!
        Assert.assertTrue(anwers.size >= 1209)
        val answer = anwers[0]
        assertNotNull(answer.subject)
    }

    @Test
    fun testGetAnswerFullById() {
        val answer = service.getAnswer(24).block()!!
        val CONTENT = "<font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333330154419px;\">Yes, in the Community Galactica there are about 30 galactic races of humanoids with this approximate size.</span></font><div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.333"
        val SUBJECT = "There is a video showing the autopsy of a very small humanoid just over a foot tall. The proportions of his body was like that of an earthlings.  Was this video fake or do these beings exist?"
        val URL = "https://www.youtube.com/watch?v=rsAXYOc2aPM"

        answer.apply {
            assertEquals(SUBJECT, subject)
            assertTrue(answer.content!!.contains(CONTENT))
            assertEquals(URL, url)
            assertTrue(answer.video.number == 3)
        }

    }

    @Test
    fun testGetAnswerById() {
        val answer = service.getAnswerSummary(24).block()!!
        val SUBJECT = "There is a video showing the autopsy of a very small humanoid just over a foot tall. The proportions of his body was like that of an earthlings.  Was this video fake or do these beings exist?"
        assertEquals(SUBJECT, answer.subject)
    }

    @Test
    fun testAddAnswer() {
        var id: Int? = null

        try {
           val SUBJECT = "struts velociry jsp jsf"
           val CONTENT = "oracle msqsl sybase sqlserver"
           var answer = Answer(content = CONTENT, subject = SUBJECT, videoNumber = 1)

            answer = service.save(answer).block()!!
            id = answer.id

            var answerDB = repo.findAnswerById(id!!).block()!!

            answerDB.apply {
                assertEquals(SUBJECT, subject)
                assertEquals(CONTENT, content)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) repo.deleteById(id!!).block()
        }
    }

    @Test
    fun testAddAnswerInvalidVideo() {
        var id: Int? = null
        val VIDEO = -1

        try {
            val answer = service.save(Answer(videoNumber = VIDEO)).block()!!
            id = answer.id
            fail("Should not save a answer without a video")
        }catch (ex : Exception){

        } finally {
            if (id != null) repo.deleteById(id!!).block()
        }
    }

    @Test
    fun testUpdateAnswer() {
        var id: Int? = null

        try {
            val SUBJECT = "struts velociry jsp jsf"
            val CONTENT = "oracle msqsl sybase sqlserver"
            val SUBJECT_UPDATED = "angular react vue timeleaf"
            val CONTENT_UPDATED = "mysql mongodb postgres mariadb"

            var answer = Answer(content = CONTENT, subject = SUBJECT, videoNumber = 1)
            answer = service.save(answer).block()!!
            id = answer.id

            answer = repo.findById(id!!).block()!!

            answer.apply {
                content = CONTENT_UPDATED
                subject = SUBJECT_UPDATED
            }

            service.update(answer).block()

            answer = repo.findById(id).block()!!

            answer.apply {
                assertEquals(SUBJECT_UPDATED, subject)
                assertEquals(CONTENT_UPDATED, content)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            if (id != null) repo.deleteById(id)
        }
    }

    @Test
    fun getTopAnswers() {
        val topAnswers = service.getTopAnswers(false).collectList().block()
        assertTrue(topAnswers!![0].clicks!! > 300)
        for (i in 1 until topAnswers.size) {
            assertTrue(topAnswers[i - 1].clicks!! >= topAnswers[i].clicks!!)
        }
        val questions = questionRepo.findByAnswerIdAndFeedbackIsNull(topAnswers.last().id!!).collectList().block()
        assertTrue(questions?.size!! > 0)
    }

    @Test
    fun getTopAnswersFeedBack() {
        val topAnswers = service.getTopAnswers(true).collectList().block()
        Assert.assertTrue(topAnswers!![0].clicks!! >= 3)

        for (i in 1 until topAnswers.size) {
            assertTrue(topAnswers[i - 1].clicks!! >= topAnswers[i].clicks!!)
        }

        val questions = questionRepo.findByAnswerIdAndFeedbackIsNotNull(topAnswers.last().id!!).collectList().block()
        Assert.assertTrue(questions?.size!! > 0)
    }

    @Test
    fun getCountAnswers() {
        val countAnswers = service.getCountAnswers().block()
        assertTrue(countAnswers!! >= 1200)
    }
}