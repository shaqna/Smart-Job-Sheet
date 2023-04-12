package com.maoungedev.smartjobsheet.data.sources.remote.service

import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.data.sources.remote.response.QuestionResponse
import kotlinx.coroutines.flow.Flow

class QuizService: FirebaseService() {

    fun getQuestions(collection: String): Flow<FirebaseResponse<List<QuestionResponse>>> =
        getCollection(collection)
}