package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.model.Student
import com.maoungedev.smartjobsheet.domain.repository.ScoreRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class ScoreInteractor(private val scoreRepository: ScoreRepository): ScoreUseCase {
    override suspend fun setStudentScore(student: Student) {
        scoreRepository.setStudentScore(student)
    }

    companion object {
        fun inject() = module {
            factoryOf(::ScoreInteractor) {
                bind<ScoreUseCase>()
            }
        }
    }
}