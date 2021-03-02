package edu.muniz.askalien.admin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
//import org.springframework.data.annotation.Transient

@Table("country")
data class Country (

    @Id
    var id: Int? = null,

    @Transient
    @Column("countquestions")
    var countQuestions: Long? = null,

    var ip: String?,

    var country: String)
{

    fun populate(sourceObject: Country) {
        country = sourceObject.country
        ip = sourceObject.ip
    }

}