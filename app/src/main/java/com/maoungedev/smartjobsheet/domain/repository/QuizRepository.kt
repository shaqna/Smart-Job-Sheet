package com.maoungedev.smartjobsheet.domain.repository

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun getQuestions(collection: String): Flow<Resource<List<Question>>>



}