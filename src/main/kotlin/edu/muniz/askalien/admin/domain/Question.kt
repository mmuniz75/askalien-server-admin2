package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.text.SimpleDateFormat
import java.util.*
import org.springframework.data.annotation.Transient

@Table("question")
data class Question (

    @Id
    var id: Int? = null,

    var ip: String? = null,

    var text: String? = null,

    var email: String? = null,

    var feedback: String? = null,

    var creationDate: Date? = null,

    var creator: String? = null,

    var country: String? = null,

    var answerId: Long? = null
)
{

    @Transient
    var countUsers: Long? = null

    @Transient
    var answer: Answer? = null

    fun populate(newObject: Question) {
        creator = newObject.creator
        email = newObject.email
        feedback = newObject.feedback
        country = newObject.country
    }

    val date: String
        get() {
            var date = ""
            val dt1 = SimpleDateFormat("MM/dd/yyyy hh:mm")
            date = dt1.format(creationDate)
            return date
        }

}