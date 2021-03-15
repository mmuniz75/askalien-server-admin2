package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.text.DateFormatSymbols
import java.util.*

@Table("usage")
data class Usage(
        @Id
        val id: Int?,
        val year: Short?,
        val month: Byte?,

        @Column("numberusers")
        val numberUsers: Int?,

        @Column("newusers")
        val newUsers: Int?
)
{
    val monthName
        get(): String? {
                        if(month == null)
                            return year.toString()

                        val months = DateFormatSymbols(Locale.US).shortMonths
                        return months[month!! - 1]
                        }

    val oldUsers
        get() = numberUsers?.minus(newUsers?:0)

}