package com.maoungedev.smartjobsheet.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maoungedev.smartjobsheet.R
import com.maoungedev.smartjobsheet.databinding.ActivityQuizBinding
import com.maoungedev.smartjobsheet.databinding.DialogLayoutBinding
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.ui.home.HomeActivity
import com.maoungedev.smartjobsheet.ui.score.ScoreActivity
import com.maoungedev.smartjobsheet.utils.Constants
import com.maoungedev.smartjobsheet.utils.pref.ListAnswerPreference
import com.maoungedev.smartjobsheet.utils.pref.PointPreference
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class QuizActivity : AppCompatActivity() {

    private val binding: ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }

    private val viewModel: QuizViewModel by viewModel()

    private val adapter: QuizAdapter by lazy {
        QuizAdapter().apply {
            setOnItemClickCallBack(object : QuizAdapter.OnBtnSubmitCallBack {
                override fun checkAnswer(
                    answer: String,
                    correctAnswer: String
                ) {
                    if (answer.isNotEmpty()) {
                        if (answer == correctAnswer) {
                            viewModel.updatePoint()
                            viewModel.updateList(Constants.Answer.CORRECT)
                            Log.d("MyPoint", viewModel.point.toString())
                        } else {
                            viewModel.updateList(Constants.Answer.INCORRECT)
                        }
                    } else {
                        viewModel.updateList(Constants.Answer.EMPTY)
                    }
                    nextPageOrFinish()
                }
            })
        }
    }

    private val pointPreference: PointPreference by lazy { PointPreference(this) }

    private val answerPreference: ListAnswerPreference by lazy { ListAnswerPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(QuizViewModel.inject())
        setContentView(binding.root)

        showDialogFormName()
        setupViewPager()
    }

    private fun allowAccessQuiz() {
        fetchQuestions()
        observe()
    }

    private fun showDialogFormName() {
        val materialBuilder = MaterialAlertDialogBuilder(this).create()
        val inflater = DialogLayoutBinding.inflate(layoutInflater)

        inflater.apply {
            btnSend.setOnClickListener {
                val name = inflater.edtName.text.toString()
                if(name.isBlank()) {
                    inflater.edtName.error = "Tidak boleh kosong"
                } else {
                    materialBuilder.dismiss()
                    viewModel.setName(name)
                    allowAccessQuiz()
                }

            }

            btnBack.setOnClickListener {
                materialBuilder.dismiss()
                finish()
            }
        }


        materialBuilder.apply {
            setView(inflater.root)
            setCancelable(false)
            show()
        }
    }

    private fun fetchQuestions() {
        viewModel.processIntent(intent)
        viewModel.fetchQuestions()
    }

    private fun observe() {
        viewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                handleStateChange(state)
            }
            .launchIn(lifecycleScope)

        viewModel.seconds.observe(this@QuizActivity) {
            binding.tvTimer.text = it.toString()
        }

        viewModel.onTimerFinish.observe(this@QuizActivity) {
            if (it) {
                nextPageOrFinish().apply {
                    Toast.makeText(this@QuizActivity, "Waktu habis", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun handleStateChange(state: QuizActivityState) {
        when (state) {
            is QuizActivityState.ErrorFetchedQuestions -> handleErrorFetchQuestions(state.message)
            QuizActivityState.Init -> Unit
            is QuizActivityState.IsLoading -> handleLoading(state.isLoading)
            is QuizActivityState.ShowToast -> showToast(state.message)
            is QuizActivityState.SuccessFetchedQuestions -> handleSuccessFetchQuestions(state.questions)
        }
    }

    private fun handleSuccessFetchQuestions(questions: List<Question>) {
        binding.sliderViewPager.adapter = adapter
        adapter.setItems(questions)

        binding.sliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvNumber.text = "Soal ${position + 1}"
                viewModel.timer.start()
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this@QuizActivity, message, Toast.LENGTH_LONG).show()
        Log.d("ERROR", "showToast: $message")
    }

    private fun handleLoading(loading: Boolean) {
        binding.animationLoading.isVisible = loading
        binding.layoutBar.isVisible = !loading
    }

    private fun handleErrorFetchQuestions(message: String) {
        showToast(message)
    }

    private fun setupViewPager() {
        with(binding) {
            sliderViewPager.offscreenPageLimit = 10
            sliderViewPager.isUserInputEnabled = false
        }
    }

    private fun nextPageOrFinish() {
        if (binding.sliderViewPager.currentItem + 1 < adapter.itemCount) {
            binding.sliderViewPager.currentItem += 1

        } else {
            startActivity(
                Intent(this@QuizActivity, ScoreActivity::class.java)
            ).apply {
                pointPreference.setPoint(viewModel.point)
                pointPreference.setName(viewModel.studentName)
                answerPreference.setList(viewModel.list)
            }.also {
                finish()
            }
        }
    }
}