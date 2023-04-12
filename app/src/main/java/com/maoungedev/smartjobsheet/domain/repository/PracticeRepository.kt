package com.maoungedev.smartjobsheet.domain.repository

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Practice
import kotlinx.coroutines.flow.Flow

interface PracticeRepository {

    fun getPractice(docId: String): Flow<Resource<Practice>>

}