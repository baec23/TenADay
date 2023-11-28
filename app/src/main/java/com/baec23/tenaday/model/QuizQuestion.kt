package com.baec23.tenaday.model

data class QuizQuestion(
    val questionString: String,
    val potentialAnswers: List<KoreanWord>,
    val correctAnswerIndex: Int
)
