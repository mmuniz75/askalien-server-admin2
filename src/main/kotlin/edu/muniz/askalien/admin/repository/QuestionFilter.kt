package edu.muniz.askalien.admin.repository

import java.time.LocalDate

data class QuestionFilter(
        var justFeedback: Boolean = false,
        var justThisMonth: Boolean = false,
        var startDate: LocalDate? = null,
        var endDate: LocalDate? = null,
        var answerId: Int? = null,
        var question: String? = null,
        var ipFilter: String? = null
)