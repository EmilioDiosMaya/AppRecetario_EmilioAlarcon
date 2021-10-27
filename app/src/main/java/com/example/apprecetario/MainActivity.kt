package com.example.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val btnRegistrarReceta = findViewById<Button>(R.id.btnRegistrarReceta)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrarReceta.setOnClickListener {
            val intento1 = Intent(this, RegistrarReceta::class.java)
            startActivity(intento1)
        }
    }

}