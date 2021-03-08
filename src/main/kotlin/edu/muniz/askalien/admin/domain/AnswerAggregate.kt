package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("answer")
data class AnswerAggregate (

    @Id
    var id: Int? = null,

    @JsonProperty("question")
    var subject: String? = null,

    var content: String? = null,

    @JsonProperty("link")
    var url: String? = null,

    var clicks: Long? = null,

    @Column("videoNumber")
    var videoNumber: Int? = null,

    @Column("numberfromvideo")
    var numberFromVideo: Int? = null,

    @Column("datefromvideo")
    var dateFromVideo: LocalDate? = null
)
{
    val video: Video
        get() = Video(number = this.numberFromVideo, creationDate = this.dateFromVideo)

}