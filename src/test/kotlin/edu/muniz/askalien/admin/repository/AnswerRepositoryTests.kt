package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Answer
import edu.muniz.askalien.admin.domain.Video
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
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
        val answer = repo.findAnswerById(708).block()
        assertEquals("Why there is some explotions seen from moon?",answer?.subject)
        assertTrue(98 === answer?.video?.number)
    }

    @Test
    fun testAddAnswer() {
        var id: Int? = null
        try {
            val SUBJECT = "sample question"
            val CONTENT = "we dont have answer for that"
            val URL = "www.youyube.com.br"
            val VIDEO = 99
            var answer = Answer()

            answer.apply {
                content = CONTENT
                subject = SUBJECT
                url = URL
                videoNumber = VIDEO
            }

            answer = repo.save(answer).block()!!
            id = answer.id

            answer = repo.findById(id!!).block()!!
            answer.apply {
                assertEquals(SUBJECT, subject)
                assertEquals(CONTENT, content)
                assertEquals(URL, url)
                assertTrue(VIDEO === videoNumber)
            }

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
            answer.apply {
                content = CONTENT
                subject = SUBJECT
                url = URL
                videoNumber = VIDEO
            }

            answer = repo.save(answer).block()!!
            id = answer.id

            answer = repo.findById(id!!).block()!!

            answer.apply {
                content = CONTENT_UDATED
                subject = SUBJECT_UDATED
                url = URL_UDATED
                videoNumber = VIDEO_UDATED
            }

            repo.save(answer).block()

            answer = repo.findById(id).block()!!

            answer.apply {
                assertEquals(SUBJECT_UDATED, subject)
                assertEquals(CONTENT_UDATED, content)
                assertEquals(URL_UDATED, url)
                assertTrue(VIDEO_UDATED === videoNumber)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        }finally {
            repo.deleteById(id!!)
        }
    }
}