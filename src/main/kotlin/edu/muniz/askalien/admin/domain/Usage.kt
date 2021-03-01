package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id

data class Usage (
    @Id
    val id: Int?,
    val year: Short?,
    val month: Byte?,
    val numberUsers: Int?,
    val newUsers: Int?
)