package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("answer")
data class Answer (

    @Id
    var id: Int? = null,

    @JsonProperty("question")
    var subject: String? = null,

    var content: String? = null,

    @JsonProperty("link")
    var url: String? = null,

    @Column("videoNumber")
    var videoNumber: Int? = null
)
