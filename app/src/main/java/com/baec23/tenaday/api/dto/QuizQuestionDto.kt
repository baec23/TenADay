package com.baec23.tenaday.api.dto

import com.baec23.tenaday.model.EnglishWord
import com.baec23.tenaday.model.KoreanWord

data class QuizQuestionDto(
    val baseWord: EnglishWord,
    val incorrectAnswers: List<KoreanWord>
)
