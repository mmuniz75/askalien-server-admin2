package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Table("video")
data class Video(
        @Id
        var id: Int? = null,
        var number: Int? = null,

        @Column("creationdate")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var creationDate: LocalDate? = null
    )

{

}