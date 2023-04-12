package com.maoungedev.smartjobsheet.ui.quiz

import android.content.Intent
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maoungedev.smartjobsheet.domain.Resource
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.domain.usecase.QuizUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf

class QuizViewModel(
    private val quizUseCase: QuizUseCase
) : ViewModel() {

    private var quizCollection: String? = null

    private val _seconds = MutableLiveData<Int>()
    val seconds = _seconds

    private val _onTimerFinish = MutableLiveData<Boolean>()
    val onTimerFinish = _onTimerFinish

    var point = 0

    var list: ArrayList<String> = arrayListOf()

    private val _state = MutableStateFlow<QuizActivityState>(QuizActivityState.Init)
    val state: StateFlow<QuizActivityState> get() = _state

    var timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(p0: Long) {
            val timeLeft = p0/1000
            _seconds.value = timeLeft.toInt()
        }

        override fun onFinish() {
            _onTimerFinish.value = true
        }

    }

    fun processIntent(intent: Intent?) {
        intent?.apply {
            quizCollection = intent.getStringExtra("QUIZ")
        }
    }

    fun updateList(item: String) {
        list.add(item)
    }

    fun updatePoint() {
        point += 10
    }

    private fun setLoading() {
        _state.value = QuizActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = QuizActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = QuizActivityState.ShowToast(message)
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            quizUseCase.getQuestions(quizCollection ?: "")
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
                            QuizActivityState.ErrorFetchedQuestions(resource.message.toString())
                        is Resource.Loading -> setLoading()
                        is Resource.Success -> {
                            resource.data?.let {
                                _state.value = QuizActivityState.SuccessFetchedQuestions(it)
                            }
                        }
                    }
                }
        }
    }

    companion object {
        fun inject() = module {
            viewModelOf(::QuizViewModel)
        }
    }

}

sealed class QuizActivityState {
    object Init : QuizActivityState()
    data class IsLoading(val isLoading: Boolean) : QuizActivityState()
    data class ShowToast(val message: String) : QuizActivityState()
    data class SuccessFetchedQuestions(val questions: List<Question>) : QuizActivityState()
    data class ErrorFetchedQuestions(val message: String) : QuizActivityState()
}