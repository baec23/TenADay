package com.baec23.tenaday.api

import com.baec23.tenaday.api.dto.SaveWordDto
import com.baec23.tenaday.model.EnglishWord
import com.baec23.tenaday.model.QuizQuestion
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TenADayApi {
    @POST("word/english")
    suspend fun saveWord(
        @Body saveWordBody: SaveWordDto
    ): EnglishWord

    @GET("quiz/english")
    suspend fun getQuizQuestion(): QuizQuestion

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/api/v1/"
    }
}
