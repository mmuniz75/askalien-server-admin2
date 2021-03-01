package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("country")
data class Country (

    @Id
    var id: Int,

    @Transient
    @JsonSerialize
    @JsonDeserialize
    var countQuestions: Long,

    var ip: String?,

    var country: String)
{

    fun populate(sourceObject: Country) {
        country = sourceObject.country
        ip = sourceObject.ip
    }

}