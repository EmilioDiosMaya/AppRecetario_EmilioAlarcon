package com.example.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Código para los botones

        val btnRegistrarReceta = findViewById<Button>(R.id.btnRegistrarReceta)
        btnRegistrarReceta.setOnClickListener {
            val intento1 = Intent(this, RegistrarReceta::class.java)
            startActivity(intento1)
        }

        val btnConsultarReceta = findViewById<Button>(R.id.btnConsultarReceta)
        btnConsultarReceta.setOnClickListener {
            val intento1 = Intent(this, ConsultarReceta::class.java)
            startActivity(intento1)
        }

        //Código para navegación web

        val url = findViewById<EditText>(R.id.txtURL)
        val btnIr = findViewById<Button>(R.id.btnNavegar)

        btnIr.setOnClickListener {
            val intento1 = Intent(this, Navegador::class.java)
            intento1.putExtra("direccion", url.text.toString())
            startActivity(intento1)
        }
    }

}