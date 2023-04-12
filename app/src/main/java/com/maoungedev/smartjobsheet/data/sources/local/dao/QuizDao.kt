package com.maoungedev.smartjobsheet.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maoungedev.smartjobsheet.data.sources.local.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)


    @Query("select * from quiz where collection = :collection")
    fun getQuestions(collection: String): Flow<List<QuestionEntity>>


    @Query("delete from quiz")
    suspend fun clearQuestions()
}