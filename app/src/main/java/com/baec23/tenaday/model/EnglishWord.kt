package com.baec23.tenaday.model

data class EnglishWord(
    val text: String,
    val partOfSpeech: PartOfSpeech,
    val definitions: List<String>
)
