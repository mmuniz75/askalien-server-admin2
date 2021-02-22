package edu.muniz.askalien.admin.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("country")
data class Country (

    @Id
    var id: Int? = null,

    @Transient
    @JsonSerialize
    @JsonDeserialize
    var countQuestions: Long? = null,

    var ip: String? = null,

    var country: String? = null)

    {

    constructor(ip: String?, country: String?)  {
        this.ip = ip
        this.country = country
    }

    constructor(country: String?, countQuestions: Long?) {
        this.countQuestions = countQuestions
        this.country = country
    }

    fun populate(sourceObject: Country) {
        country = sourceObject.country
        ip = sourceObject.ip
    }

}