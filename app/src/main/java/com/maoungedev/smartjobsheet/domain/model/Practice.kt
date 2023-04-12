package com.maoungedev.smartjobsheet.domain.model

data class Practice(
    val id:Int,
    val title: String,
    val achievementIndicator: List<String>,
    val basicCompetencies: List<String>,
    val information: List<String>,
    val instruction: List<String>,
    val materials: List<String>,
    val objective: List<String>,
    val tools: List<String>,
    val task: List<String>,
    val workSafety: List<String>,
    val workSteps: List<String>,
    val picture: String
)
