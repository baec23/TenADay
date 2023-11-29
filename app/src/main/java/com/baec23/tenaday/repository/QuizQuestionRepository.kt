package com.baec23.tenaday.repository

import com.baec23.tenaday.api.TenADayApi
import com.baec23.tenaday.model.KoreanWord
import com.baec23.tenaday.model.QuizQuestion
import com.baec23.tenaday.repository.util.RetryUtil
import javax.inject.Inject

private const val TAG = "QuizQuestionRepository"

class QuizQuestionRepository @Inject constructor(private val tenADayApi: TenADayApi) {
    suspend fun getRandomQuizQuestion(): Result<QuizQuestion> {
        return RetryUtil.withRetry { tryGetRandomQuizQuestion() }
    }

    private suspend fun tryGetRandomQuizQuestion(): Result<QuizQuestion> {
        return try {
            val dto = tenADayApi.getQuizQuestion()
            val questionString = dto.baseWord.text
            val correctAnswer = KoreanWord(
                text = dto.baseWord.definitions.random(),
                partOfSpeech = dto.baseWord.partOfSpeech,
                definitions = listOf(dto.baseWord.text)
            )
            val potentialAnswers =
                dto.incorrectAnswers.plus(
                    correctAnswer
                ).shuffled()
            val correctAnswerIndex = potentialAnswers.indexOf(correctAnswer)
            Result.success(
                QuizQuestion(
                    questionString = questionString,
                    potentialAnswers = potentialAnswers,
                    correctAnswerIndex = correctAnswerIndex
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
