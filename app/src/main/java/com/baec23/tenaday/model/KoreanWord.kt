package com.baec23.tenaday.model

data class KoreanWord(
    val text: String,
    val partOfSpeech: PartOfSpeech,
    val definitions: List<String>
)
