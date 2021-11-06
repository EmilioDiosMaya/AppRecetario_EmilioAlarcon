package com.example.apprecetario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button

class Navegador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegador)

        val volver = findViewById<Button>(R.id.btnVolverNavegador)
        volver.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        val url = bundle?.getString("direccion")

        val webview = findViewById<WebView>(R.id.WebView)

        webview.loadUrl("https://www.${url}.com")
    }
}