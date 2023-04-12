package com.maoungedev.smartjobsheet.data.di

import androidx.room.Room
import com.maoungedev.smartjobsheet.data.sources.local.db.JobSheetDatabase
import com.maoungedev.smartjobsheet.data.sources.remote.service.PracticeService
import com.maoungedev.smartjobsheet.data.sources.remote.service.QuizService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), JobSheetDatabase::class.java, "JobSheet.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    factory {
        get<JobSheetDatabase>().quizDao()
    }

    factory {
        get<JobSheetDatabase>().practiceDao()
    }
}
val serviceModule = module {
    factoryOf(::QuizService)
    factoryOf(::PracticeService)
}
