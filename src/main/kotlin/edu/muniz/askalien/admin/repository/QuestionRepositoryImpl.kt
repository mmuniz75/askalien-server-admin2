package edu.muniz.askalien.admin.repository

import edu.muniz.askalien.admin.domain.Question
import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux
import java.time.LocalDateTime
import java.util.*
import java.util.function.BiFunction


class QuestionRepositoryImpl : QuestionCustomizedRepository {

    @Autowired
    lateinit var databaseClient: DatabaseClient

    override fun findAll(filter: QuestionFilter): Flux<Question> {

        val sql = StringBuilder("select * from Question obj where 1=1 ")
        if (filter.justFeedback) sql.append(" and obj.feedback is not null")
        if (filter.answerId != null && filter.answerId!! > 0) sql.append(" and obj.answer.id =" + filter.answerId)
        if (filter.question != null && filter.question!!.isNotEmpty()) sql.append(" and obj.text like '%" + filter.question + "%'")
        if (filter.ipFilter != null && filter.ipFilter!!.isNotEmpty()) sql.append(" and obj.ip like '%" + filter.ipFilter + "%'")

        if (filter.justThisMonth) {
            val cal = Calendar.getInstance()
            cal[Calendar.DATE] = 1
            cal[Calendar.HOUR] = 0
            cal[Calendar.MINUTE] = 0
            cal[Calendar.SECOND] = 0
            cal[Calendar.MILLISECOND] = 0
            cal[Calendar.AM_PM] = Calendar.AM
            sql.append(" and obj.creationDate >= ${cal.time}")
        }

        if (filter.startDate != null) sql.append(" and obj.creationDate >= ${filter.startDate}")
        if (filter.endDate != null) sql.append(" and obj.creationDate <= :${filter.endDate}")
        sql.append(" order by creationdate desc")

        val MAPPING_FUNCTION: BiFunction<Row, RowMetadata, Question> = BiFunction<Row, RowMetadata, Question>
        { row: Row, rowMetaData: RowMetadata? ->
            Question(
                    row.get("id", Int::class.java),
                    row.get("ip", String::class.java),
                    row.get("text", String::class.java),
                    row.get("email", String::class.java),
                    row.get("feedback", String::class.java),
                    row.get("creationDate", LocalDateTime::class.java),
                    row.get("creator", String::class.java)
                    )
        }

        return  databaseClient.sql(sql.toString())
                .map(MAPPING_FUNCTION)
                .all()
    }
}
