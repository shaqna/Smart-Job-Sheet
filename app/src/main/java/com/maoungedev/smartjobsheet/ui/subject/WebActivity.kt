package com.maoungedev.smartjobsheet.ui.subject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.maoungedev.smartjobsheet.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private val binding: ActivityWebBinding by lazy {
        ActivityWebBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")!!

        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            settings.loadsImagesAutomatically = true
            loadUrl(url)
        }

        binding.apply {
            refreshLayout.setOnRefreshListener {
                webView.reload()
                refreshLayout.isRefreshing = false
            }
        }
    }
}