package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("view")
data class View (
    @Id
    val id: Int?,
    val year: Short?,
    val month: Byte,
    val number: Long
)