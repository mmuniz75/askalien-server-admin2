package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.text.SimpleDateFormat
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

@Table("question")
data class QuestionAggregate (

    @Id
    var id: Integer? = null,

    var ip: String? = null,

    var text: String? = null,

    var email: String? = null,

    var feedback: String? = null,

    @Column("creationdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy hh:mm")
    var creationDate: LocalDateTime? = LocalDateTime.now(),

    var creator: String? = null,

    var country: String? = null,

    var answerId: Long? = null
)
{

    var countUsers: Long? = null

    val answer: Answer
        get() = Answer(content = this.content, subject = this.subject)

    var content: String? = null

    var subject: String? = null

}