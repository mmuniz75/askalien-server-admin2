package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.text.SimpleDateFormat
import java.util.*


@Table("video")
data class Video (
    @Id
    var id: Int? = null,
    var number: Int? = null,
    var creationDate: Date? = null
    )

{

    fun populate(newObject: Video) {
        number = newObject.number
        creationDate = newObject.creationDate
    }

    fun getFormatedCreationDate(): String? {
        var date = ""
        if (creationDate != null) {
            val dt1 = SimpleDateFormat("yyyy-MM-dd")
            date = dt1.format(creationDate)
        }
        return date
    }


}