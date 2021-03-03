package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

data class Usage (
    @Id
    val id: Int?,
    val year: Short?,
    val month: Byte?,

    @Column("numberusers")
    val numberUsers: Int?,

    @Column("newusers")
    val newUsers: Int?
)