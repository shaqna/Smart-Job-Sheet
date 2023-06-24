package com.maoungedev.smartjobsheet.data.sources.remote.response

data class QuestionResponse(
    val uid: String = "",
    val number: Int = 0,
    val question: String = "",
    val answer: List<String> = listOf(),
    val correct_answer: String = "",
    val picture: String = ""
)
