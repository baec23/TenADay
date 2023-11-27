package com.baec23.tenaday.repository

import com.baec23.tenaday.api.TenADayApi
import com.baec23.tenaday.model.QuizQuestion
import javax.inject.Inject
import kotlinx.coroutines.delay

class QuizQuestionRepository @Inject constructor(private val tenADayApi: TenADayApi) {
    suspend fun getRandomQuizQuestion(): Result<QuizQuestion> {
        delay(3000)
        return try {
            Result.success(tenADayApi.getQuizQuestion())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
