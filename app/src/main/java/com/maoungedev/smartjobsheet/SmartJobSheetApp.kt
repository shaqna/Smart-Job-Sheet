package com.maoungedev.smartjobsheet

import android.app.Application
import com.maoungedev.smartjobsheet.data.di.databaseModule
import com.maoungedev.smartjobsheet.data.di.serviceModule
import com.maoungedev.smartjobsheet.data.repository.PracticeRepositoryImpl
import com.maoungedev.smartjobsheet.data.repository.QuizRepositoryImpl
import com.maoungedev.smartjobsheet.data.sources.remote.service.JobSheetService
import com.maoungedev.smartjobsheet.domain.usecase.PracticeInteractor
import com.maoungedev.smartjobsheet.domain.usecase.QuizInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SmartJobSheetApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SmartJobSheetApp)
            modules(
                listOf(
                    JobSheetService.inject(),
                    QuizRepositoryImpl.inject(),
                    PracticeRepositoryImpl.inject(),
                    serviceModule,
                    databaseModule,
                    QuizInteractor.inject(),
                    PracticeInteractor.inject()
                )

            )
        }
    }
}