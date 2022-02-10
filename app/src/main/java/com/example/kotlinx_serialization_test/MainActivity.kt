package com.example.kotlinx_serialization_test

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinx_serialization_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initWebView()
    }

    override fun onBackPressed() {
        with(binding.webView) {
            if (canGoBack())
                goBack()
            else
                super.onBackPressed()
        }
    }

    private fun initWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true

            addJavascriptInterface(WebBridge(binding.webView), "native")
            loadUrl("file:///android_asset/main.html")
        }
    }
}