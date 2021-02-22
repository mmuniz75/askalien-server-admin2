package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("answer")
data class Answer (

    @Id
    @JsonProperty("number")
    val id: Int? = null,

    @JsonProperty("question")
    val subject: String? = null,

    val content: String? = null,

    @JsonProperty("link")
    val url: String? = null,

    val video: Video? = null
)