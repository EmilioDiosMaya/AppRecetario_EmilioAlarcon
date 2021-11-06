package com.example.apprecetario

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class CategoriaComida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_comida)

        val txtNombreComida = findViewById<TextView>(R.id.txtNombreComida)
        val txtDescripcionComida = findViewById<TextView>(R.id.txtDescripcionComida)
        val txtIngredientesComida = findViewById<TextView>(R.id.txtIngredientesComida)
        val txtPreparacionComida = findViewById<TextView>(R.id.txtPreparacionComida)
        val txtIdComida = findViewById<TextView>(R.id.txtIdComida)

        val btnActualizarComida = findViewById<Button>(R.id.btnActualizarComida)
        val btnEliminarComida = findViewById<Button>(R.id.btnEliminarComida)

        val lista = findViewById<ListView>(R.id.lstComidas)

        //----Ingreso de la información a la lista----

        val admin = AdminSQLite (this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val datos = bd.rawQuery("SELECT nombre FROM recetas WHERE categoria = 'comida'", null)

        var listaNombres : MutableList<String> = ArrayList()

        while(datos.moveToNext()){
            listaNombres.add(datos.getString(0))
        }

        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombres)
        lista.adapter = adaptador1

        Log.d("Esto es lo que imprime", listaNombres.toString())

        bd.close()

        //----Consultar Comida-----


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
            val informacionIdComida = bd.rawQuery("SELECT id FROM recetas WHERE categoria = 'comida'", null)

            var listaId : MutableList<String> = ArrayList()

            while(informacionIdComida.moveToNext()){
                listaId.add(informacionIdComida.getString(0))
            }

            Log.d("El valor de la consulta", informacionIdComida.toString())

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

            txtIdComida.setText(idReceta[0])
            txtNombreComida.setText(nombreReceta[0])
            txtDescripcionComida.setText(descripcionReceta[0])
            txtIngredientesComida.setText(ingredientesReceta[0])
            txtPreparacionComida.setText(preparacionReceta[0])

            bd.close()

            Toast.makeText(this, "Consulta éxitosa", Toast.LENGTH_SHORT).show()
        }

        //----Actualizar Comida----

        btnActualizarComida.setOnClickListener{
            var auxiliar = txtIdComida.text.toString()
            val admin = AdminSQLite(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()

            registro.put("nombre", txtNombreComida.text.toString())
            registro.put("descripcion", txtDescripcionComida.text.toString())
            registro.put("ingredientes", txtIngredientesComida.text.toString())
            registro.put("preparacion", txtPreparacionComida.text.toString())

            val actualizar = bd.update("recetas", registro, "id = '${auxiliar}'", null)
            bd.close()

            if(actualizar == 1)
                Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()
        }

        //----Eliminar Comida----
        btnEliminarComida.setOnClickListener{
            val admin = AdminSQLite (this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val eliminar = bd.delete("recetas", "nombre = '${txtNombreComida.text.toString()}'", null )

            bd.close()

            txtNombreComida.setText("")
            txtDescripcionComida.setText("")
            txtIngredientesComida.setText("")
            txtPreparacionComida.setText("")

            if(eliminar == 1)
                Toast.makeText(this, "Eliminacion exitosa de la receta", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()

        }

        //----Salir de la ventana----

        val volver = findViewById<Button>(R.id.btnVolverComida)
        volver.setOnClickListener {
            finish()
        }
    }
}