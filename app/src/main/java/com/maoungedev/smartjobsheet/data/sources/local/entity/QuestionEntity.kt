package com.maoungedev.smartjobsheet.data.sources.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    val uid: String,

    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name = "question")
    val question: String,

    @ColumnInfo(name = "answer")
    val answer: List<String>,

    @ColumnInfo(name = "correctAnswer")
    val correctAnswer: String,

    @ColumnInfo(name = "quizPicture")
    val quizPicture: String,

    @ColumnInfo(name = "collection")
    var collection: String = ""
)

