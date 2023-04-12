package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuizUseCase {

    fun getQuestions(collection: String): Flow<Resource<List<Question>>>

}