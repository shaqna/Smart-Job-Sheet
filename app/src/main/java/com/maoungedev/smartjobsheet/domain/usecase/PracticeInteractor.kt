package com.maoungedev.smartjobsheet.domain.usecase

import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Practice
import com.maoungedev.smartjobsheet.domain.repository.PracticeRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class PracticeInteractor(private val repository: PracticeRepository): PracticeUseCase {
    override fun getPractice(docId: String): Flow<Resource<Practice>> {
        return repository.getPractice(docId)
    }

    companion object {
        fun inject() = module {
            factoryOf(::PracticeInteractor) {
                bind<PracticeUseCase>()
            }
        }
    }
}