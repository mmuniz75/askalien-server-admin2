package edu.muniz.askalien.admin.repository

import java.util.*

data class QuestionFilter(
        val justFeedback: Boolean = false,
        val justThisMonth: Boolean = false,
        val startDate: Date?,
        val endDate: Date?,
        val answerId: Int?,
        val question: String?,
        val ipFilter: String?
)