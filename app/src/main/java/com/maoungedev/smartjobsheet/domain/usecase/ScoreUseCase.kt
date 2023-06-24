package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.model.Student

interface ScoreUseCase {

    suspend fun setStudentScore(student: Student)
}