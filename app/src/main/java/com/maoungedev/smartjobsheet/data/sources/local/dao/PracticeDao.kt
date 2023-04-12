package com.maoungedev.smartjobsheet.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maoungedev.smartjobsheet.data.sources.local.entity.PracticeEntity
import com.maoungedev.smartjobsheet.data.sources.local.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PracticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPractice(practice: PracticeEntity)


    @Query("select * from practice where docId = :docId")
    fun getPractice(docId: String): Flow<PracticeEntity>


    @Query("delete from practice")
    suspend fun clearPractice()
}