package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Practice
import kotlinx.coroutines.flow.Flow

interface PracticeUseCase {

    fun getPractice(docId: String): Flow<Resource<Practice>>
}