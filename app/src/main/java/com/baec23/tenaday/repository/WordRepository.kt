package com.baec23.tenaday.repository

import com.baec23.tenaday.api.TenADayApi
import com.baec23.tenaday.api.dto.SaveWordDto
import com.baec23.tenaday.model.EnglishWord
import com.baec23.tenaday.model.PartOfSpeech
import javax.inject.Inject
import kotlinx.coroutines.delay

class WordRepository @Inject constructor(private val tenADayApi: TenADayApi) {

    suspend fun saveWord(
        baseWord: String,
        partOfSpeech: PartOfSpeech,
        definitions: List<String>
    ): Result<EnglishWord> {
        val savedWordDto = SaveWordDto(
            baseWord,
            partOfSpeech,
            definitions
        )
        delay(2000)
        return try {
            val savedWord = tenADayApi.saveWord(savedWordDto)
            Result.success(savedWord)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
