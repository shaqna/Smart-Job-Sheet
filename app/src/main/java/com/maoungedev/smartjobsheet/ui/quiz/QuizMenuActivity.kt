package com.maoungedev.smartjobsheet.ui.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maoungedev.smartjobsheet.databinding.ActivityQuizMenuBinding
import com.maoungedev.smartjobsheet.utils.Constants

class QuizMenuActivity : AppCompatActivity() {
    
    private val binding: ActivityQuizMenuBinding by lazy {
        ActivityQuizMenuBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            firstQuiz.setOnClickListener {
                Intent(this@QuizMenuActivity, QuizActivity::class.java).also {
                    it.putExtra("QUIZ", Constants.Quiz.QUIZ_1)
                    startActivity(it)
                }
            }
            secondQuiz.setOnClickListener {
                Intent(this@QuizMenuActivity, QuizActivity::class.java).also {
                    it.putExtra("QUIZ", Constants.Quiz.QUIZ_2)
                    startActivity(it)
                }
            }
            thirdQuiz.setOnClickListener {
                Intent(this@QuizMenuActivity, QuizActivity::class.java).also {
                    it.putExtra("QUIZ", Constants.Quiz.QUIZ_3)
                    startActivity(it)
                }
            }
        }
    }
}