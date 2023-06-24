package com.maoungedev.smartjobsheet.data.repository

import com.maoungedev.smartjobsheet.data.sources.remote.service.ScoreService
import com.maoungedev.smartjobsheet.domain.model.Student
import com.maoungedev.smartjobsheet.domain.repository.ScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ScoreRepositoryImpl(private val scoreService: ScoreService): ScoreRepository {
    override suspend fun setStudentScore(student: Student) {
        withContext(Dispatchers.IO) {
            scoreService.setStudentScore(student)
        }
    }

    companion object {
        fun inject() = module {
            singleOf(::ScoreRepositoryImpl) {
                bind<ScoreRepository>()
            }
        }
    }
}