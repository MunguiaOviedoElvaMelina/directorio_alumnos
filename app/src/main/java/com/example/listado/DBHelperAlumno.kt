package com.example.listado
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelperAlumno (context: Context):SQLiteOpenHelper(context,DB_name, null,DB_version){
    companion object{
        private var DB_name= "bdalumnos"
        private var DB_version=1
        private var nomTable="alumnos"
        private var keyId ="id"
        private var nom= "nombre"
        private var cue="cuenta"
        private var cor="correo"
        private var img="imagen"
    }
    val SQLCreate: String="CREATE TABLE $nomTable ($keyId INTEGER PRIMARY KEY, $nom TEXT, $cue TEXT, $cor TEXT, $img TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
       db.execSQL(SQLCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, old_version:Int, new_version:Int) {
        db.execSQL("DROP TABLE IF EXISTS $nomTable")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}