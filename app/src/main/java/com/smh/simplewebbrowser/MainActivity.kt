package com.smh.simplewebbrowser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val addressBar: EditText by lazy {
        findViewById(R.id.addressBar)
    }

    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    @SuppressLint("SetJavaScriptEnabled") // 보안경고무시
    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true // 구글 메뉴누를수있게해주는 코드
            loadUrl("http://www.google.com")  // http는 보안문제가 있지만 웹페이지 구현만 할것이기 때문에 Manifests에 android:usesCleartextTraffic="true" 추가
        }
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(v.text.toString())
            }

            return@setOnEditorActionListener false
        }
    }

}