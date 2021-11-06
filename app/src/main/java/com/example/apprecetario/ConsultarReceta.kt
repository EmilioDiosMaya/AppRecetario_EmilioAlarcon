package com.example.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ConsultarReceta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_receta)

        val volver = findViewById<Button>(R.id.btnVolverConsultar)
        volver.setOnClickListener {
            finish()
        }

        val btnCategoriaDesayuno = findViewById<ImageButton>(R.id.btnCategoriaDesayuno)
        btnCategoriaDesayuno.setOnClickListener {
            val intento1 = Intent(this, CategoriaDesayuno::class.java)
            startActivity(intento1)
        }

        val btnCategoriaComida = findViewById<ImageButton>(R.id.btnCategoriaComida)
        btnCategoriaComida.setOnClickListener {
            val intento1 = Intent(this, CategoriaComida::class.java)
            startActivity(intento1)
        }

        val btnCategoriaCena = findViewById<ImageButton>(R.id.btnCategoriaCena)
        btnCategoriaCena.setOnClickListener {
            val intento1 = Intent(this, CategoriaCena::class.java)
            startActivity(intento1)
        }

        val btnCategoriaPostre = findViewById<ImageButton>(R.id.btnCategoriaPostre)
        btnCategoriaPostre.setOnClickListener {
            val intento1 = Intent(this, CategoriaPostre::class.java)
            startActivity(intento1)
        }
    }
}