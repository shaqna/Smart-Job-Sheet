package com.maoungedev.smartjobsheet.ui.score

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maoungedev.smartjobsheet.databinding.ActivityScoreBinding
import com.maoungedev.smartjobsheet.domain.model.Student
import com.maoungedev.smartjobsheet.ui.quiz.QuizMenuActivity
import com.maoungedev.smartjobsheet.utils.pref.PointPreference
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ScoreActivity : AppCompatActivity() {

    private val binding: ActivityScoreBinding by lazy { ActivityScoreBinding.inflate(layoutInflater) }
    private val pointPreference: PointPreference by lazy { PointPreference(this) }
    private val viewModel: ScoreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(ScoreViewModel.inject())

        setStudentScore()
        animatedScore()
        setView()

    }

    private fun setStudentScore() {
        val student = Student(name = pointPreference.studentName, score = pointPreference.point)
        viewModel.setStudentScore(student)
    }

    private fun setView() {
        with(binding) {
            btnBackToMain.setOnClickListener {
                Intent(this@ScoreActivity, QuizMenuActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun animatedScore() {
        val animator = ValueAnimator.ofInt(0, pointPreference.point)
        with(animator) {
            addUpdateListener { value ->
                binding.tvScore.text = value.animatedValue.toString()
            }
            duration = 1000
            start()
        }

    }
}