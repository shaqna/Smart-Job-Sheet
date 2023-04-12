package com.maoungedev.smartjobsheet.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maoungedev.smartjobsheet.databinding.ActivityHomeBinding
import com.maoungedev.smartjobsheet.ui.about.AboutActivity
import com.maoungedev.smartjobsheet.ui.practice.PracticeMenuActivity
import com.maoungedev.smartjobsheet.ui.quiz.QuizActivity
import com.maoungedev.smartjobsheet.ui.quiz.QuizMenuActivity
import com.maoungedev.smartjobsheet.ui.subject.SubjectActivity

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.quizMenu.setOnClickListener {
            Intent(this, QuizMenuActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.subjectMenu.setOnClickListener {
            Intent(this, SubjectActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.procedureMenu.setOnClickListener {
            Intent(this, PracticeMenuActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.infoMenu.setOnClickListener {
            Intent(this, AboutActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}