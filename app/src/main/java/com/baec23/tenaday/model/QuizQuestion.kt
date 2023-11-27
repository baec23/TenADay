package com.baec23.tenaday.model

data class QuizQuestion(
    val baseWord: EnglishWord,
    val incorrectAnswers: List<KoreanWord>
)
