package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.text.DateFormatSymbols
import java.util.*


@Table("view_question")
data class View (
    @Id
    val id: Int?,
    val year: Short?,
    val month: Byte,
    val number: Long
){
    val monthName
        get(): String? {
            val months = DateFormatSymbols(Locale.US).months
            return months[month!! - 1]
        }
}