package com.example.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConsultarReceta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_receta)

        val volver = findViewById<Button>(R.id.btnVolverConsultar)
        volver.setOnClickListener {
            finish()
        }
    }
}