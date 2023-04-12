package com.maoungedev.smartjobsheet.ui.practice

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Practice
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.domain.usecase.PracticeUseCase
import com.maoungedev.smartjobsheet.ui.quiz.QuizActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


class PracticeViewModel(
    private val useCase: PracticeUseCase
): ViewModel() {
    private var practiceDocId: String? = null

    private val _state = MutableStateFlow<PracticeActivityState>(PracticeActivityState.Init)
    val state: StateFlow<PracticeActivityState> get() = _state

    fun processIntent(intent: Intent?) {
        intent?.apply {
            practiceDocId = intent.getStringExtra("PRACTICE")
        }
    }

    private fun setLoading() {
        _state.value = PracticeActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = PracticeActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = PracticeActivityState.ShowToast(message)
    }

    fun fetchPractice() {
        viewModelScope.launch {
            useCase.getPractice(practiceDocId ?: "")
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { resource ->
                    hideLoading()
                    when (resource) {
                        is Resource.Error -> _state.value =
                            PracticeActivityState.ErrorFetchedPractice(resource.message.toString())
                        is Resource.Loading -> setLoading()
                        is Resource.Success -> {
                            resource.data?.let {
                                _state.value = PracticeActivityState.SuccessFetchedPractice(it)
                            }
                        }
                    }
                }
        }
    }

    companion object {
        fun inject() = module {
            viewModelOf(::PracticeViewModel)
        }
    }
}

sealed class PracticeActivityState {
    object Init : PracticeActivityState()
    data class IsLoading(val isLoading: Boolean) : PracticeActivityState()
    data class ShowToast(val message: String) : PracticeActivityState()
    data class SuccessFetchedPractice(val practice: Practice) : PracticeActivityState()
    data class ErrorFetchedPractice(val message: String) : PracticeActivityState()
}