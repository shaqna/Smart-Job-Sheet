package com.maoungedev.smartjobsheet.ui.subject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maoungedev.smartjobsheet.databinding.ActivitySubjectBinding
import com.maoungedev.smartjobsheet.utils.Constants.WebUrl.MATERI_1
import com.maoungedev.smartjobsheet.utils.Constants.WebUrl.MATERI_2
import com.maoungedev.smartjobsheet.utils.Constants.WebUrl.MATERI_3

class SubjectActivity : AppCompatActivity() {
    private val binding: ActivitySubjectBinding by lazy {
        ActivitySubjectBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            firstSubject.setOnClickListener {
                Intent(this@SubjectActivity, WebActivity::class.java).also {
                    it.putExtra("URL", MATERI_1)
                    startActivity(it)
                }
            }
            secondSubject.setOnClickListener {
                Intent(this@SubjectActivity, WebActivity::class.java).also {
                    it.putExtra("URL", MATERI_2)
                    startActivity(it)
                }
            }
            thirdSubject.setOnClickListener {
                Intent(this@SubjectActivity, WebActivity::class.java).also {
                    it.putExtra("URL", MATERI_3)
                    startActivity(it)
                }
            }
        }
    }
}