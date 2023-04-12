package com.maoungedev.smartjobsheet.ui.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maoungedev.smartjobsheet.databinding.ActivityPracticeMenuBinding
import com.maoungedev.smartjobsheet.utils.Constants

class PracticeMenuActivity : AppCompatActivity() {

    private val binding: ActivityPracticeMenuBinding by lazy {
        ActivityPracticeMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onActionMenuSelected()
    }

    private fun onActionMenuSelected() {
        binding.apply {
            firstPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_1)
                    startActivity(it)
                }
            }

            secondPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_2)
                    startActivity(it)
                }
            }

            thirdPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_3)
                    startActivity(it)
                }
            }

            fourthPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_4)
                    startActivity(it)
                }
            }

            fifthPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_5)
                    startActivity(it)
                }
            }

            sixthPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_6)
                    startActivity(it)
                }
            }

            seventhPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_7)
                    startActivity(it)
                }
            }

            eighthPractice.setOnClickListener {
                Intent(this@PracticeMenuActivity, PracticeActivity::class.java).also {
                    it.putExtra("PRACTICE", Constants.PracticeDocument.PRACTICE_8)
                    startActivity(it)
                }
            }
        }
    }
}