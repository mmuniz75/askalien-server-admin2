package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("answer")
data class AnswerSummary (

    @Id
    var id: Int? = null,

    @JsonProperty("question")
    var subject: String? = null

)
