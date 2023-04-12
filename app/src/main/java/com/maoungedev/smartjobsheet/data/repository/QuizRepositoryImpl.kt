package com.maoungedev.smartjobsheet.data.repository

import com.maoungedev.smartjobsheet.data.NetworkBoundResource
import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.data.mapper.DataMapper
import com.maoungedev.smartjobsheet.data.sources.local.dao.QuizDao
import com.maoungedev.smartjobsheet.data.sources.remote.response.FirebaseResponse
import com.maoungedev.smartjobsheet.data.sources.remote.response.QuestionResponse
import com.maoungedev.smartjobsheet.data.sources.remote.service.QuizService
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class QuizRepositoryImpl(
    private val quizDao: QuizDao,
    private val quizService: QuizService
) : QuizRepository {
    override fun getQuestions(collection: String): Flow<Resource<List<Question>>> =
        object : NetworkBoundResource<List<Question>, List<QuestionResponse>>() {
            override fun loadFromDB(): Flow<List<Question>?> =
                quizDao.getQuestions(collection).map {
                    DataMapper.QuizMapper.mapEntitiesToDomain(it)
                }

            override fun createCall(): Flow<FirebaseResponse<List<QuestionResponse>>> =
                quizService.getQuestions(collection)

            override suspend fun saveCallResult(data: List<QuestionResponse>) {
                val questionList = DataMapper.QuizMapper.mapResponsesToEntities(data, collection)
                quizDao.insertQuestions(questionList)
            }

            override fun shouldFetch(data: List<Question>?): Boolean = true

        }.asFlow()

    companion object {
        fun inject() = module {
            singleOf(::QuizRepositoryImpl) {
                bind<QuizRepository>()
            }
        }
    }
}