package com.maoungedev.smartjobsheet.data.repository

import com.maoungedev.smartjobsheet.data.NetworkBoundResource
import com.maoungedev.smartjobsheet.data.mapper.DataMapper
import com.maoungedev.smartjobsheet.data.sources.local.dao.PracticeDao
import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.data.sources.remote.response.PracticeResponse
import com.maoungedev.smartjobsheet.data.sources.remote.service.PracticeService
import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Practice
import com.maoungedev.smartjobsheet.domain.repository.PracticeRepository
import com.maoungedev.smartjobsheet.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class PracticeRepositoryImpl(
    private val dao: PracticeDao,
    private val service: PracticeService
): PracticeRepository {
    override fun getPractice(docId: String): Flow<Resource<Practice>> =
        object : NetworkBoundResource<Practice, PracticeResponse>() {
            override fun loadFromDB(): Flow<Practice?> {
                return dao.getPractice(docId).map {
                    DataMapper.PracticeMapper.mapEntitiesToDomain(it)
                }
            }

            override fun createCall(): Flow<FirebaseResponse<PracticeResponse>> {
                return service.getPractice(docId)
            }

            override suspend fun saveCallResult(data: PracticeResponse) {
                val practiceEntity = DataMapper.PracticeMapper.mapResponsesToEntities(data, docId)
                dao.insertPractice(practiceEntity)
            }

            override fun shouldFetch(data: Practice?): Boolean {
                return true
            }

        }.asFlow()

    companion object {
        fun inject() = module {
            singleOf(::PracticeRepositoryImpl) {
                bind<PracticeRepository>()
            }
        }
    }
}