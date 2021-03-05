package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("question")
data class Question(

        @Id
        var id: Integer? = null,

        var ip: String? = null,

        var text: String? = null,

        var email: String? = null,

        var feedback: String? = null,

        @Column("creationdate")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy hh:mm:ss a")
        var creationDate: LocalDateTime? = LocalDateTime.now(),

        var creator: String? = null,

        var country: String? = null,

        @Column("answer_id")
        var answerId: Integer? = null
)
