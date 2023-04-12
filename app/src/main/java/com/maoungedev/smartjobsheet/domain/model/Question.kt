package com.maoungedev.smartjobsheet.domain.model

data class Question(
    val uid: String,
    val number: Int,
    val question: String,
    val answer: List<String>,
    val correctAnswer: String
)
