package com.maoungedev.smartjobsheet.data.sources.remote.service

import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.domain.model.JobModel
import com.maoungedev.smartjobsheet.utils.Constants.JOB_COLLECTION
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class JobSheetService: FirebaseService() {

    fun getJobSheet(docId: String): Flow<FirebaseResponse<JobModel>> =
        getDocument(JOB_COLLECTION, docId)


    companion object {
        fun inject() = module {
            singleOf(::JobSheetService)
        }
    }
}