package com.maoungedev.smartjobsheet.domain.model

data class JobModel(
    val title: String = "",
    val objectives: List<String> = listOf(),
    val indicators: List<String> = listOf(),
    val tools: List<String> = listOf(),
    val materials: List<String> = listOf(),
    val work_safety: List<String> = listOf(),
    val work_steps: List<String> = listOf(),
    val task: List<String> = listOf()
)
