package edu.muniz.askalien.admin.repository

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime


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