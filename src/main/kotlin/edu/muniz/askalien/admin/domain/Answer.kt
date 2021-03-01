package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("answer")
data class Answer (

    @Id
    @JsonProperty("number")
    val id: Int,

    @JsonProperty("question")
    val subject: String,

    val content: String,

    @JsonProperty("link")
    val url: String?,

    val videoNumber: Int,

    @Transient
    @JsonSerialize
    @JsonDeserialize
    val clicks: Long?
)