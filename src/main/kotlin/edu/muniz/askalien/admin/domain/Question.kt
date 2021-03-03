package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.text.SimpleDateFormat
//import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

@Table("question")
data class Question (

    @Id
    var id: Int? = null,

    var ip: String? = null,

    var text: String? = null,

    var email: String? = null,

    var feedback: String? = null,

    @Column("creationdate")
    var creationDate: LocalDateTime? = null,

    var creator: String? = null,

    var country: String? = null,

    var answerId: Long? = null
)
{

    @Transient
    var countUsers: Long? = null

    val answer: Answer
        get() = Answer(content = this.content, subject = this.subject)

    @Transient
    var content: String? = null

    @Transient
    var subject: String? = null

    fun populate(newObject: Question) {
        creator = newObject.creator
        email = newObject.email
        feedback = newObject.feedback
        country = newObject.country
    }

    val date: String
        get() {
            val dt1 = SimpleDateFormat("MM/dd/yyyy hh:mm")
            return dt1.format(creationDate)
        }

}