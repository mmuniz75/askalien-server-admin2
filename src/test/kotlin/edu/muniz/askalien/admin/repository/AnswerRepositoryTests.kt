package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.domain.Video
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.reactive.awaitFirst
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class AnswerRepositoryTests {

    @Autowired
    lateinit var repo: AnswerRepository

    @Test
    fun testFindAnwer() {
        val answer = repo.findById(1).block()
        assertEquals(answer?.subject, "Is the planet Earth is undergoing a transformation in the near future?")
    }

    @Test
    fun testAddAnswer() {
        var id: Int? = null
        try {
            val SUBJECT = "sample question"
            val CONTENT = "we dont have answer for that"
            val URL = "www.youyube.com.br"
            val VIDEO = 10
            var answer = Answer()
            answer.content = CONTENT
            answer.subject = SUBJECT
            answer.url = URL
            answer.videoNumber = VIDEO

            answer = repo.save(answer).block()!!
            id = answer.id

            answer = repo.findAnswerById(id!!).block()!!
            org.junit.Assert.assertEquals(SUBJECT, answer.subject)
            org.junit.Assert.assertEquals(CONTENT, answer.content)
            org.junit.Assert.assertEquals(URL, answer.url)
            org.junit.Assert.assertTrue(VIDEO === answer?.videoNumber)
        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            repo.deleteById(id!!).block()
        }
    }

    @Test
    fun testUpdateAnswer() {
        var id: Int? = null
        try {
            val SUBJECT = "sample question"
            val CONTENT = "we dont have answer for that"
            val URL = "www.youyube.com.br"
            val VIDEO = 10
            val SUBJECT_UDATED = "other question"
            val CONTENT_UDATED = "for this question we have answer"
            val URL_UDATED = "www.google.com.br"
            val VIDEO_UDATED = 20
            var answer = Answer()
            answer.content = CONTENT
            answer.subject = SUBJECT
            answer.url = URL
            answer.videoNumber = VIDEO
            repo.save(answer).block()
            id = answer.id

            answer = repo.findAnswerById(id!!).block()!!
            answer.content = CONTENT_UDATED
            answer.subject = SUBJECT_UDATED
            answer.url = URL_UDATED
            answer.videoNumber = VIDEO_UDATED
            repo.save(answer).block()

            answer = repo.findAnswerById(id).block()!!
            org.junit.Assert.assertEquals(SUBJECT_UDATED, answer.subject)
            org.junit.Assert.assertEquals(CONTENT_UDATED, answer.content)
            org.junit.Assert.assertEquals(URL_UDATED, answer.url)
            org.junit.Assert.assertTrue(VIDEO_UDATED === answer?.videoNumber)
        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        }finally {
            repo.deleteById(id!!)
        }
    }
}