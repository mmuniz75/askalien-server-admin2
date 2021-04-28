package edu.muniz.askalien.admin.dao

import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.repository.QuestionFilter
import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.function.BiFunction


@Service
class QuestionDAO {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    private fun formatDBDate(date : LocalDate) : String {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date)
    }

    fun findAll(filter: QuestionFilter): Flux<Question> {

        val sql = StringBuilder("select * from Question obj where 1=1 ")
        if (filter.justFeedback) sql.append(" and obj.feedback is not null")
        if (filter.answerId != null && filter.answerId!! > 0) sql.append(" and obj.answer_id =" + filter.answerId)
        if (filter.question != null && filter.question!!.isNotEmpty()) sql.append(" and obj.text like '%" + filter.question + "%'")
        if (filter.ipFilter != null && filter.ipFilter!!.isNotEmpty()) sql.append(" and obj.ip like '%" + filter.ipFilter + "%'")

        if (filter.justThisMonth) {
            val date = LocalDate.now().withDayOfMonth(1)
            sql.append(" and obj.creationDate >= '${formatDBDate(date)}'")
        }

        if (filter.startDate != null) sql.append(" and obj.creationDate >= '${formatDBDate(filter.startDate!!)}'")
        if (filter.endDate != null) sql.append(" and obj.creationDate <= '${formatDBDate(filter.endDate!!)}'")
        sql.append(" order by creationdate desc")

        val MAPPING_FUNCTION: BiFunction<Row, RowMetadata, Question> = BiFunction<Row, RowMetadata, Question>
        { row: Row, rowMetaData: RowMetadata? ->
            Question(
                row.get("id", Integer::class.java),
                row.get("ip", String::class.java),
                row.get("text", String::class.java),
                row.get("email", String::class.java),
                row.get("feedback", String::class.java),
                row.get("creationDate", LocalDateTime::class.java),
                row.get("creator", String::class.java),
                row.get("country", String::class.java),
                row.get("answer_id", Integer::class.java)
            )
        }

        return  databaseClient.sql(sql.toString())
            .map(MAPPING_FUNCTION)
            .all()
    }

}