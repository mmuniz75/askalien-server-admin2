package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Video
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest
class VideoRepositoryTests {

    @Autowired
    lateinit var repo: VideoRepository

    @Test
    fun testFindVideo(){
        val video = repo.findById(1).block()
        assertEquals(1, video?.number)
    }

    @Test
    fun testVideoQuestions() {
        val videos = repo.findAllByOrderByNumberDesc().collectList().block()!!
        val size = videos.size
        Assert.assertTrue(size >= 153)
        val video = videos[size - 2]
        assertTrue(video.number == 1)
        val creationDateTest = LocalDate.of(2010, 9, 10)
        Assert.assertTrue(video.creationDate?.compareTo(creationDateTest) == 0)
    }

    @Test
    fun saveVideo() {
        val NUMBER = -1
        val CREATION_DATE = LocalDate.of(2100, 7, 15)
        var video = Video(creationDate = CREATION_DATE, number = NUMBER)

        video = repo.save(video).block()!!
        val id = video.id

        try {
            video = repo .findById(id!!).block()!!
            Assert.assertEquals(video.number, NUMBER)
            Assert.assertTrue(video.creationDate!!.compareTo(CREATION_DATE) == 0)

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            repo.deleteById(id!!).block()
        }
    }

    @Test
    fun updateVideo() {
        val NUMBER = -1
        val CREATION_DATE = LocalDate.of(2100, 7, 15)
        val CREATION_DATE_UPDATED = LocalDate.of(2100, 7, 15)
        var video = Video(creationDate = CREATION_DATE, number = NUMBER)

        video = repo.save(video).block()!!
        val id = video.id!!

        try {
            video = repo.findById(id).block()!!
            video.creationDate = CREATION_DATE_UPDATED
            repo.save(video).block()

            video = repo.findById(id).block()!!

            video.apply {
                assertEquals(NUMBER, number)
                assertTrue(creationDate?.compareTo(CREATION_DATE_UPDATED) == 0)
            }

        }catch (ex : Exception){
            ex.printStackTrace()
            throw ex
        } finally {
            repo.deleteById(id!!).block()
        }
    }
}