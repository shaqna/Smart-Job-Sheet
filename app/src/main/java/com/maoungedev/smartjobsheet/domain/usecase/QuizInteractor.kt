package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.domain.model.Student
import com.maoungedev.smartjobsheet.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class QuizInteractor(
    private val repository: QuizRepository
): QuizUseCase {
    override fun getQuestions(collection: String): Flow<Resource<List<Question>>> {
        return repository.getQuestions(collection)
    }



    companion object {
        fun inject() = module {
            factoryOf(::QuizInteractor) {
                bind<QuizUseCase>()
            }
        }
    }
}