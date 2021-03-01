package edu.muniz.askalien.admin.dto

data class StatisticDTO (

    val totalQuestion: Long,
    val totalFrequentUsers: Int,
    val totalUsers: Long?,
    val totalCountries: Long?,
    val totalAnswers: Long?
)