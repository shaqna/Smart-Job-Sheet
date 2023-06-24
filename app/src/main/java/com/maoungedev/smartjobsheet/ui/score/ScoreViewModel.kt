package com.maoungedev.smartjobsheet.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maoungedev.smartjobsheet.domain.model.Student
import com.maoungedev.smartjobsheet.domain.usecase.ScoreUseCase
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class ScoreViewModel(private val scoreUseCase: ScoreUseCase): ViewModel() {

    fun setStudentScore(student: Student) {
        viewModelScope.launch {
            scoreUseCase.setStudentScore(student)
        }
    }


    companion object {
        fun inject() = module {
            viewModelOf(::ScoreViewModel)
        }
    }

}