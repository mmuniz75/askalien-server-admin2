package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Table("video")
data class Video(
        @Id
        var id: Int? = null,
        var number: Int? = null,

        @Column("creationdate")
        var creationDate: LocalDate? = null
    )

{

    fun populate(newObject: Video) {
        number = newObject.number
        creationDate = newObject.creationDate
    }

    fun getFormatedCreationDate(): String? {
        var date = ""
        if (creationDate != null) {
            val dt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            date = dt1.format(creationDate)
        }
        return date
    }


}