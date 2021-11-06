package com.example.apprecetario

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class CategoriaCena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_cena)

        val txtNombreCena = findViewById<TextView>(R.id.txtNombreCena)
        val txtDescripcionCena = findViewById<TextView>(R.id.txtDescripcionCena)
        val txtIngredientesCena = findViewById<TextView>(R.id.txtIngredientesCena)
        val txtPreparacionCena = findViewById<TextView>(R.id.txtPreparacionCena)
        val txtIdCena = findViewById<TextView>(R.id.txtIdCena)

        val btnActualizarCena = findViewById<Button>(R.id.btnActualizarCena)
        val btnEliminarCena = findViewById<Button>(R.id.btnEliminarCena)

        val lista = findViewById<ListView>(R.id.lstCenas)

        //----Ingreso de la información a la lista----

        val admin = AdminSQLite (this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val datos = bd.rawQuery("SELECT nombre FROM recetas WHERE categoria = 'cena'", null)

        var listaNombres : MutableList<String> = ArrayList()

        while(datos.moveToNext()){
            listaNombres.add(datos.getString(0))
        }

        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombres)
        lista.adapter = adaptador1

        Log.d("Esto es lo que imprime", listaNombres.toString())

        bd.close()

        //----Consultar Cena-----


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
            val informacionIdCena = bd.rawQuery("SELECT id FROM recetas WHERE categoria = 'cena'", null)

            var listaId : MutableList<String> = ArrayList()

            while(informacionIdCena.moveToNext()){
                listaId.add(informacionIdCena.getString(0))
            }

            Log.d("El valor de la consulta", informacionIdCena.toString())

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

            txtIdCena.setText(idReceta[0])
            txtNombreCena.setText(nombreReceta[0])
            txtDescripcionCena.setText(descripcionReceta[0])
            txtIngredientesCena.setText(ingredientesReceta[0])
            txtPreparacionCena.setText(preparacionReceta[0])

            bd.close()

            Toast.makeText(this, "Consulta éxitosa", Toast.LENGTH_SHORT).show()
        }

        //----Actualizar Cena----

        btnActualizarCena.setOnClickListener{
            var auxiliar = txtIdCena.text.toString()
            val admin = AdminSQLite(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()

            registro.put("nombre", txtNombreCena.text.toString())
            registro.put("descripcion", txtDescripcionCena.text.toString())
            registro.put("ingredientes", txtIngredientesCena.text.toString())
            registro.put("preparacion", txtPreparacionCena.text.toString())

            val actualizar = bd.update("recetas", registro, "id = '${auxiliar}'", null)
            bd.close()

            if(actualizar == 1)
                Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()
        }

        //----Eliminar Cena----
        btnEliminarCena.setOnClickListener{
            val admin = AdminSQLite (this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val eliminar = bd.delete("recetas", "nombre = '${txtNombreCena.text.toString()}'", null )

            bd.close()

            txtNombreCena.setText("")
            txtDescripcionCena.setText("")
            txtIngredientesCena.setText("")
            txtPreparacionCena.setText("")

            if(eliminar == 1)
                Toast.makeText(this, "Eliminacion exitosa de la receta", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No se encontró la receta", Toast.LENGTH_SHORT).show()

        }

        //----Salir de la ventana----

        val volver = findViewById<Button>(R.id.btnVolverCena)
        volver.setOnClickListener {
            finish()
        }
    }
}