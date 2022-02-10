package com.example.kotlinx_serialization_test

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class WebBridge(
    private val webView: WebView,
) {

    @JavascriptInterface
    fun postMessage(stringfiedJsonObject: String) {
        val message = Json.decodeFromString<Message>(stringfiedJsonObject)
        Log.d(TAG, "postMessage: $message")

        when (message.function) {
            "showToastMessage" -> makeToast(message.params)
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(webView.context, "$message", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "WebAppTest"
    }
}