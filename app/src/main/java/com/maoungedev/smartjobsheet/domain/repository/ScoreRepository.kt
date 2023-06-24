package com.maoungedev.smartjobsheet.domain.repository

import com.maoungedev.smartjobsheet.domain.model.Student

interface ScoreRepository {
    suspend fun setStudentScore(student: Student)
}