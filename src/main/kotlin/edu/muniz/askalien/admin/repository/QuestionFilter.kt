package edu.muniz.askalien.admin.repository

import java.util.*

data class QuestionFilter(
        var justFeedback: Boolean = false,
        var justThisMonth: Boolean = false,
        var startDate: Date? = null,
        var endDate: Date? = null,
        var answerId: Int? = null,
        var question: String? = null,
        var ipFilter: String? = null
)