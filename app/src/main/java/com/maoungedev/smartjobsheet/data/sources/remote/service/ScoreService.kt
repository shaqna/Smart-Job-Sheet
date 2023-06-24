package com.maoungedev.smartjobsheet.data.sources.remote.service

import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.domain.model.Student
import kotlinx.coroutines.flow.Flow

class ScoreService: FirebaseService() {

    fun setStudentScore(student: Student) {
        firestore.collection("students").document(student.name).set(student)
    }
}