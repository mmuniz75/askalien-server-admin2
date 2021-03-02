package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Transient

@Table("answer")
data class Answer (

    @Id
    @JsonProperty("number")
    var id: Int? = null,

    @JsonProperty("question")
    var subject: String? = null,

    var content: String? = null,

    @JsonProperty("link")
    var url: String? = null,

    @Column("videoNumber")
    var videoNumber: Int? = null
)
{

    @Transient
    var clicks: Long? = null

    @Transient
    var video: Video? = null
}