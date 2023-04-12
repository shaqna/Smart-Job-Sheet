package com.maoungedev.smartjobsheet.data.sources.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "practice")
data class PracticeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "achievementIndicator") val achievementIndicator: List<String>,
    @ColumnInfo(name = "basicCompetencies") val basicCompetencies: List<String>,
    @ColumnInfo(name = "information") val information: List<String>,
    @ColumnInfo(name = "instruction") val instruction: List<String>,
    @ColumnInfo(name = "materials") val materials: List<String>,
    @ColumnInfo(name = "objective") val objective: List<String>,
    @ColumnInfo(name = "tools") val tools: List<String>,
    @ColumnInfo(name = "task") val task: List<String>,
    @ColumnInfo(name = "workSafety") val workSafety: List<String>,
    @ColumnInfo(name = "workSteps") val workSteps: List<String>,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "docId") val docId: String,

)
