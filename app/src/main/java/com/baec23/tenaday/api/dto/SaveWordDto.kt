package com.baec23.tenaday.api.dto

import com.baec23.tenaday.model.PartOfSpeech

data class SaveWordDto(
    val word: String,
    val pos: PartOfSpeech,
    val definitions: List<String>
)
