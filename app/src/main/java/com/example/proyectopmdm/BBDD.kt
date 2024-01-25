package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION){


    // Constantes para la base de datos y sus columnas
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "BBDD.db"

        //CONSTANTES DE LA TABLA ARTICULOS
        private const val TABLA_ARTICULOS = "articulos"
        private const val KEY_ID = "id"
        private const val COLUMN_NOMBRE_ARTICULO = "nombre"
        private const val COLUMN_PESO = "icon_peso"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_TIPOARTICULO = "tipoArticulo"
        private const val COLUMN_IMAGEN = "imagen"

        //CONSTANTES DE LA TABLA PERSONAJE
        private const val TABLA_PERSONAJE = "articulos"
        private const val KEY_ID_PERSONAJE = "id"
        private const val COLUMN_NOMBRE_PERSONAJE = "nombrePersonaje"
        private const val COLUMN_RAZA = "raza"
        private const val COLUMN_CLASE = "clase"
        private const val COLUMN_ESTADO_VITAL = "estadoVital"
        private const val COLUMN_KEY_ID_MOCHILA = "estadoVital"

    }

    // Crea la tabla en la base de datos cuando se crea por primera vez
    override fun onCreate(db: SQLiteDatabase) {
        val ARTICULOS = "CREATE TABLE $TABLA_ARTICULOS(" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE_ARTICULO TEXT, $COLUMN_PESO INTEGER, $COLUMN_PRECIO INTEGER," +
                "$COLUMN_TIPOARTICULO TEXT, $COLUMN_IMAGEN TEXT)"
        db.execSQL(ARTICULOS)

        val PERSONAJE = "CREATE TABLE $TABLA_PERSONAJE(" +
                "$KEY_ID_PERSONAJE INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE_PERSONAJE TEXT, $COLUMN_RAZA TEXT, $COLUMN_KEY_ID_MOCHILA INTEGER," +
                "$COLUMN_CLASE TEXT, $COLUMN_ESTADO_VITAL TEXT,"+
                "FOREIGN KEY($COLUMN_KEY_ID_MOCHILA) REFERENCES $TABLA_ARTICULOS($KEY_ID))"
        db.execSQL(PERSONAJE)
    }


    // Borra la tabla existente y la vuelve a crear si hay una actualización en la versión
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_ARTICULOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLA_PERSONAJE")
        onCreate(db)
    }


    // Inserta un nuevo artículo en la base de datos
    fun insertarArticulo(articulo: Articulo){
        val db =this.writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_NOMBRE_ARTICULO, articulo.getNombre().toString())
            put(COLUMN_PESO, articulo.getPeso())
            put(COLUMN_PRECIO, articulo.getPrecio())
            put(COLUMN_TIPOARTICULO,articulo.getTipoArticulo().toString())
            put(COLUMN_IMAGEN,articulo.getImagen())
        }
        db.insert(TABLA_ARTICULOS, null, values)
        db.close()
    }

    fun insertarPersonaje(personaje: Personaje){
        val db =this.writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_NOMBRE_PERSONAJE, personaje.getNombre())
            put(COLUMN_RAZA, personaje.getRaza().toString())
            put(COLUMN_CLASE,personaje.getClase().toString())
            put(COLUMN_ESTADO_VITAL,personaje.getEstadoVital().toString())
        }
        db.insert(TABLA_PERSONAJE, null, values)
        db.close()
    }



    @SuppressLint("Range")
    // Obtiene todos los artículos de la base de datos
    fun getArticulo(): ArrayList<Articulo> {
        val articulos = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_ARTICULOS"
        val db= this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){

            do{
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_ARTICULO))
                val peso = cursor.getInt(cursor.getColumnIndex(COLUMN_PESO))
                val precio = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO))
                val tipoArticulo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPOARTICULO))
                val imagen = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))
                articulos.add(Articulo(id,nombre, peso))
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return articulos
    }

    @SuppressLint("Range")
    fun getPersonaje(): ArrayList<Personaje> {
        val personajes = ArrayList<Personaje>()
        val selectQuery = "SELECT * FROM $TABLA_PERSONAJE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_PERSONAJE))
                val estadoVital = cursor.getString(cursor.getColumnIndex(COLUMN_ESTADO_VITAL))
                val clase = cursor.getString(cursor.getColumnIndex(COLUMN_CLASE))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                personajes.add(Personaje(nombre, estadoVital, clase, raza))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return personajes
    }

}