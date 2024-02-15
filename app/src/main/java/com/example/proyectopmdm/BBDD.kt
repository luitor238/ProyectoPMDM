package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyectopmdm.Articulo.Nombre
import com.example.proyectopmdm.Personaje.Raza
import com.example.proyectopmdm.Personaje.Clase
import com.example.proyectopmdm.Personaje.EstadoVital




class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION){

    // Constantes para la base de datos y sus columnas
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "BBDD.db"

        //CONSTANTES DE LA TABLA ARTICULOS
        private const val TABLA_ARTICULOS = "articulos"
        private const val KEY_ID = "id"
        private const val ID_USUARIO = "id_usuario"
        private const val COLUMN_NOMBRE_ARTICULO = "nombre"
        private const val COLUMN_PESO = "icon_peso"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_TIPOARTICULO = "tipoArticulo"
        private const val COLUMN_IMAGEN = "imagen"

        //CONSTANTES DE LA TABLA PERSONAJE
        private const val TABLA_PERSONAJE = "personaje"
        private const val KEY_ID_USUARIO = "id_usuario"
        private const val COLUMN_NOMBRE_PERSONAJE = "nombre"
        private const val COLUMN_RAZA = "icon_peso"
        private const val COLUMN_CLASE = "precio"
        private const val COLUMN_ESTADO_VITAL = "tipoArticulo"
        private const val COLUMN_IMAGEN_PERSONAJE = "imagen"
        private const val COLUMN_SALUD = "salud"
        private const val COLUMN_ATAQUE = "ataque"
        private const val COLUMN_EXPERIENCIA = "experiencia"
        private const val COLUMN_NIVEL = "nivel"
        private const val COLUMN_SUERTE = "suerte"
        private const val COLUMN_DEFENSA = "defensa"


    }

    // Crea la tabla en la base de datos cuando se crea por primera vez
    override fun onCreate(db: SQLiteDatabase) {
        val ARTICULOS = "CREATE TABLE $TABLA_ARTICULOS(" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE_ARTICULO TEXT, $COLUMN_PESO INTEGER, $COLUMN_PRECIO INTEGER," +
                "$COLUMN_TIPOARTICULO TEXT, $COLUMN_IMAGEN TEXT, $ID_USUARIO TEXT)"
        db.execSQL(ARTICULOS)

        val sql = "CREATE UNIQUE INDEX KEY_ID_USUARIO ON $TABLA_ARTICULOS($ID_USUARIO)"
        db.execSQL(sql)


        val PERSONAJE = "CREATE TABLE $TABLA_PERSONAJE(" +
                "$KEY_ID_USUARIO TEXT PRIMARY KEY," +
                "$COLUMN_NOMBRE_PERSONAJE TEXT," +
                "$COLUMN_RAZA TEXT," +
                "$COLUMN_CLASE TEXT," +
                "$COLUMN_ESTADO_VITAL TEXT," +
                "$COLUMN_IMAGEN_PERSONAJE TEXT," +
                "$COLUMN_SALUD INTEGER," +
                "$COLUMN_ATAQUE INTEGER," +
                "$COLUMN_EXPERIENCIA INTEGER," +
                "$COLUMN_NIVEL INTEGER," +
                "$COLUMN_SUERTE INTEGER," +
                "$COLUMN_DEFENSA INTEGER)"

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

        }
        db.insert(TABLA_ARTICULOS, null, values)
        db.close()
    }
    fun insertarPersonaje(id: String, personaje: Personaje){
        val db =this.writableDatabase
        val values = ContentValues().apply{

            put(KEY_ID_USUARIO, id)
            put(COLUMN_NOMBRE_PERSONAJE, personaje.getNombre().toString())
            put(COLUMN_RAZA, personaje.getRaza().toString())
            put(COLUMN_CLASE, personaje.getClase().toString())
            put(COLUMN_ESTADO_VITAL, personaje.getEstadoVital().toString())
            put(COLUMN_SALUD, personaje.getSalud())
            put(COLUMN_ATAQUE, personaje.getAtaque())
            put(COLUMN_EXPERIENCIA, personaje.getExperiencia())
            put(COLUMN_NIVEL, personaje.getNivel())
            put(COLUMN_SUERTE, personaje.getSuerte())
            put(COLUMN_DEFENSA, personaje.getDefensa())

        }
        db.insert(TABLA_PERSONAJE, null, values)
        db.close()
    }



    @SuppressLint("Range")
    // Obtiene todos los artículos de la base de datos
    fun getArticulo(): ArrayList<Articulo> {
        val articulos = ArrayList<Articulo>()
        val globalInstance = variableGlobal.getInstance()
        val selectQuery = "SELECT * FROM $TABLA_ARTICULOS WHERE KEY_ID_USUARIO = 'globalInstance' "
        val db= this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            //class Articulo(private var id: Int, private var nombre: com.example.proyectopmdm.Articulo.Nombre, private var peso: Int) :

            do{
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_ARTICULO))
                val peso = cursor.getInt(cursor.getColumnIndex(COLUMN_PESO))
                articulos.add(Articulo(id,nombre.toNombre()!!, peso))
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return articulos
    }

    @SuppressLint("Range")
    // Obtiene todos los personajes de la base de datos
    fun getPersonaje(): ArrayList<Personaje> {
        val personajes = ArrayList<Personaje>()
        val globalInstance = variableGlobal.getInstance()
        val selectQuery = "SELECT * FROM $TABLA_PERSONAJE WHERE KEY_ID_USUARIO = 'globalInstance' "
        val db= this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){

            do{
                val id = cursor.getString(cursor.getColumnIndex(KEY_ID_USUARIO))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_PERSONAJE))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val clase = cursor.getString(cursor.getColumnIndex(COLUMN_CLASE))
                val estadoVital = cursor.getString(cursor.getColumnIndex(COLUMN_ESTADO_VITAL))
                val salud = cursor.getInt(cursor.getColumnIndex(COLUMN_SALUD))
                val ataque = cursor.getInt(cursor.getColumnIndex(COLUMN_ATAQUE))
                val experiencia = cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCIA))
                val nivel = cursor.getInt(cursor.getColumnIndex(COLUMN_NIVEL))
                val suerte = cursor.getInt(cursor.getColumnIndex(COLUMN_SUERTE))
                val defensa = cursor.getInt(cursor.getColumnIndex(COLUMN_DEFENSA))
                val personaje: Personaje  = Personaje(id, nombre, raza.toRaza()!!, clase.toClase()!!, estadoVital.toEstadoVital()!!)
                personaje.setExperienciaN(experiencia)
                personaje.setSalud(salud)
                personaje.setAtaque(ataque)
                personaje.SetNivel(nivel)
                personaje.SetSuerte(suerte)
                personaje.SetDefensa(defensa)
                personajes.add(personaje)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return personajes
    }


    fun eliminarRegistro(id: Int): Boolean {
        val db = this.writableDatabase
        val whereClause = "$KEY_ID = ?"
        val whereArgs = arrayOf(id.toString())

        return db.delete(TABLA_ARTICULOS, whereClause, whereArgs) > 0
    }


}

fun String.toNombre(): Nombre? {
    return try {
        return Nombre.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        // Manejar la excepción si el valor no coincide con ninguno del enum
        null
    }
}
fun String.toRaza(): Raza? {
    return try {
        return Raza.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        // Manejar la excepción si el valor no coincide con ninguno del enum
        null
    }
}
fun String.toClase(): Clase? {
    return try {
        return Clase.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        // Manejar la excepción si el valor no coincide con ninguno del enum
        null
    }
}
fun String.toEstadoVital(): EstadoVital? {
    return try {
        return EstadoVital.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        // Manejar la excepción si el valor no coincide con ninguno del enum
        null
    }
}
