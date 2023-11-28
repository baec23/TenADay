package com.baec23.tenaday.repository

import com.baec23.tenaday.api.TenADayApi
import com.baec23.tenaday.model.KoreanWord
import com.baec23.tenaday.model.QuizQuestion
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt
import kotlinx.coroutines.delay

class QuizQuestionRepository @Inject constructor(private val tenADayApi: TenADayApi) {
    suspend fun getRandomQuizQuestion(): Result<QuizQuestion> {
        delay(1000)
        return try {
            val rand = Random.nextInt(10)
            if (rand == 1) {
                return Result.failure(Exception())
            }
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
