package com.maoungedev.smartjobsheet.data.sources.remote.response

data class PracticeResponse(
    val id: Int = 0,
    val title: String = "",
    val achievement_indicator: List<String> = listOf(),
    val basic_competencies: List<String> = listOf(),
    val information: List<String> = listOf(),
    val instruction: List<String> = listOf(),
    val materials: List<String> = listOf(),
    val objective: List<String> = listOf(),
    val tools: List<String> = listOf(),
    val task: List<String> = listOf(),
    val work_safety: List<String> = listOf(),
    val work_steps: List<String> = listOf(),
    val picture: String = ""
)
