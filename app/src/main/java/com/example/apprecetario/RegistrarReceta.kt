package com.example.apprecetario

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get

class RegistrarReceta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_receta)

        val txtNombreReceta = findViewById<EditText>(R.id.txtNombreReceta)
        val txtDescripcionReceta = findViewById<EditText>(R.id.txtDescripcionReceta)
        val txtIngredietesReceta = findViewById<EditText>(R.id.txtIngredientesReceta)
        val txtPreparacionReceta = findViewById<EditText>(R.id.txtPreparacionReceta)

        val btnAgregarReceta = findViewById<Button>(R.id.btnAgregarReceta)

        val spinner = findViewById<Spinner>(R.id.spCategorias)

        val listaCategorias = arrayOf("desayuno", "comida", "cena", "postre")

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCategorias)

        spinner.adapter = adaptador

        btnAgregarReceta.setOnClickListener {
            Log.d("El SPINERRRRRRR",spinner.selectedItem.toString())

            if(txtNombreReceta.text.isNotEmpty() && txtDescripcionReceta.text.isNotEmpty() &&
                    txtIngredietesReceta.text.isNotEmpty() && txtPreparacionReceta.text.isNotEmpty()){
                val admin = AdminSQLite (this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val registro = ContentValues()
                registro.put("nombre", txtNombreReceta.getText().toString())
                registro.put("descripcion", txtDescripcionReceta.getText().toString())
                registro.put("ingredientes", txtIngredietesReceta.getText().toString())
                registro.put("preparacion", txtPreparacionReceta.getText().toString())
                registro.put("categoria", spinner.selectedItem.toString())

                bd.insert("recetas", null, registro)
                bd.close()

                txtNombreReceta.setText("")
                txtDescripcionReceta.setText("")
                txtIngredietesReceta.setText("")
                txtPreparacionReceta.setText("")

                Toast.makeText(this, "Se ha registrado la receta", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        //----Salir de la ventana----

        val volver = findViewById<Button>(R.id.btnVolverRegistrar)
        volver.setOnClickListener {
            finish()
        }
    }
}