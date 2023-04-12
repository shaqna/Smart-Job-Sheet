package com.maoungedev.smartjobsheet.data.sources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maoungedev.smartjobsheet.data.sources.local.dao.QuizDao
import com.maoungedev.smartjobsheet.data.sources.local.converters.ListConverter
import com.maoungedev.smartjobsheet.data.sources.local.dao.PracticeDao
import com.maoungedev.smartjobsheet.data.sources.local.entity.PracticeEntity
import com.maoungedev.smartjobsheet.data.sources.local.entity.QuestionEntity

@Database(entities = [QuestionEntity::class, PracticeEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class JobSheetDatabase: RoomDatabase() {

    abstract fun quizDao(): QuizDao
    abstract fun practiceDao(): PracticeDao
}