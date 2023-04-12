package com.maoungedev.smartjobsheet.data.sources.remote.service

import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.data.sources.remote.response.PracticeResponse
import com.maoungedev.smartjobsheet.utils.Constants
import kotlinx.coroutines.flow.Flow

class PracticeService: FirebaseService() {

    fun getPractice(docId: String): Flow<FirebaseResponse<PracticeResponse>> =
        getDocument(Constants.PracticeCollection.VALUE, docId)

}