package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("answer")
data class AnswerSummary (

    @Id
    var id: Int? = null,
    var subject: String? = null

)
