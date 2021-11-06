package com.example.apprecetario

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLite (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table if not exists recetas (id integer primary key autoincrement, nombre text not null, descripcion text not null, ingredientes text not null, preparacion text not null, categoria text not null)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion:Int, newversion: Int) {

    }
}