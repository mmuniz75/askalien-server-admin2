package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Table("video")
data class Video (
    @Id
    var id: Integer? = null,
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