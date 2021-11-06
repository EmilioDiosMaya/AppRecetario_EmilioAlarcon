package com.example.apprecetario

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import kotlin.math.log

class CategoriaDesayuno: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_desayuno)

        val txtNombreDesayuno = findViewById<TextView>(R.id.txtNombreDesayuno)
        val txtDescripcionDesayuno = findViewById<TextView>(R.id.txtDescripcionDesayuno)
        val txtIngredientesDesayuno = findViewById<TextView>(R.id.txtIngredientesDesayuno)
        val txtPreparacionDesayuno = findViewById<TextView>(R.id.txtPreparacionDesayuno)
        val txtIdDesayuno = findViewById<TextView>(R.id.txtIdDesayuno)

        val btnActualizarDesayuno = findViewById<Button>(R.id.btnActualizarDesayuno)
        val btnEliminarDesayuno = findViewById<Button>(R.id.btnEliminarDesayuno)

        val lista = findViewById<ListView>(R.id.lstDesayunos)

        //----Ingreso de la información a la lista----

        val admin = AdminSQLite (this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val datos = bd.rawQuery("SELECT nombre FROM recetas WHERE categoria = 'desayuno'", null)

        var listaNombres : MutableList<String> = ArrayList()

        while(datos.moveToNext()){
            listaNombres.add(datos.getString(0))
        }

        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombres)
        lista.adapter = adaptador1

        Log.d("Esto es lo que imprime", listaNombres.toString())

        bd.close()

        //----Consultar Desayuno-----


            lista.setOnItemClickListener { adapterView, view, i, l ->

                Log.d("Este es el valor de i ", i.toString())

                var idReceta : MutableList<String> = ArrayList()
                var nombreReceta : MutableList<String> = ArrayList()
                var descripcionReceta : MutableList<String> = ArrayList()
                var ingredientesReceta : MutableList<String> = ArrayList()
                var preparacionReceta : MutableList<String> = ArrayList()

                idReceta.clear()
                nombreReceta.clear()
                descripcionReceta.clear()
                ingredientesReceta.clear()
                preparacionReceta.clear()

                val admin = AdminSQLite (this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val informacionIdDesayuno = bd.rawQuery("SELECT id FROM recetas WHERE categoria = 'desayuno'", null)

                var listaId : MutableList<String> = ArrayList()

                while(informacionIdDesayuno.moveToNext()){
                    listaId.add(informacionIdDesayuno.getString(0))
                }

                Log.d("El valor de la consulta", informacionIdDesayuno.toString())

                //Recuperar información de la fila de nombre

                val consultaNombreReceta = bd.rawQuery("SELECT nombre FROM recetas WHERE id = '${listaId[i]}'", null)

                while(consultaNombreReceta.moveToNext()){
                    nombreReceta.add(consultaNombreReceta.getString(0))
                }

                //Recuperar información de la fila de id

                val consultaIdReceta = bd.rawQuery("SELECT id FROM recetas WHERE nombre = '${nombreReceta[0]}'", null)

                while(consultaIdReceta.moveToNext()){
                    idReceta.add(consultaIdReceta.getString(0))
                }

                //Recuperar información de la fila de descripción

                val consultaDescripcionReceta = bd.rawQuery("SELECT descripcion FROM recetas WHERE id = '${listaId[i]}'", null)

                while(consultaDescripcionReceta.moveToNext()){
                    descripcionReceta.add(consultaDescripcionReceta.getString(0))
                }

                //Recuperar información de la fila de ingredientes

                val consultaIngredientesReceta = bd.rawQuery("SELECT ingredientes FROM recetas WHERE id = '${listaId[i]}'", null)

                while(consultaIngredientesReceta.moveToNext()){
                    ingredientesReceta.add(consultaIngredientesReceta.getString(0))
                }

                //Recuperar información de la fila de preparación

                val consultaPreparacionReceta = bd.rawQuery("SELECT preparacion FROM recetas WHERE id = '${listaId[i]}'", null)

                while(consultaPreparacionReceta.moveToNext()){
                    preparacionReceta.add(consultaPreparacionReceta.getString(0))
                }

                txtIdDesayuno.setText(idReceta[0])
                txtNombreDesayuno.setText(nombreReceta[0])
                txtDescripcionDesayuno.setText(descripcionReceta[0])
                txtIngredientesDesayuno.setText(ingredientesReceta[0])
                txtPreparacionDesayuno.setText(preparacionReceta[0])

                bd.close()

                Toast.makeText(this, "Consulta éxitosa", Toast.LENGTH_SHORT).show()
            }

        //----Actualizar Desayuno----

        btnActualizarDesayuno.setOnClickListener{
            var auxiliar = txtIdDesayuno.text.toString()
            val admin = AdminSQLite(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()

            registro.put("nombre", txtNombreDesayuno.text.toString())
            registro.put("descripcion", txtDescripcionDesayuno.text.toString())
            registro.put("ingredientes", txtIngredientesDesayuno.text.toString())
            registro.put("preparacion", txtPreparacionDesayuno.text.toString())

            val actualizar = bd.update("recetas", registro, "id = '${auxiliar}'", null)
            bd.close()

            if(actualizar == 1)
                Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()
        }

        //----Eliminar Desayuno----
        btnEliminarDesayuno.setOnClickListener{
            val admin = AdminSQLite (this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val eliminar = bd.delete("recetas", "nombre = '${txtNombreDesayuno.text.toString()}'", null )

            bd.close()

            txtNombreDesayuno.setText("")
            txtDescripcionDesayuno.setText("")
            txtIngredientesDesayuno.setText("")
            txtPreparacionDesayuno.setText("")

            if(eliminar == 1)
                Toast.makeText(this, "Eliminacion exitosa de la receta", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()

        }

        //----Salir de la ventana----

        val volver = findViewById<Button>(R.id.btnVolverDesayuno)
        volver.setOnClickListener {
            finish()
        }
    }
}
