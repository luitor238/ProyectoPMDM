package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proyectopmdm.Articulo
import com.example.proyectopmdm.Personaje
import com.example.proyectopmdm.Personaje.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE = "BBDD.db"

        // Constantes de la tabla ARTICULOS
        private const val TABLA_ARTICULOS = "articulos"
        private const val KEY_ID = "id"
        private const val COLUMN_NOMBRE_ARTICULO = "nombre"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_TIPO_ARTICULO = "tipoArticulo"
        private const val COLUMN_IMAGEN = "imagen"
        private const val ID_USUARIO = "id_usuario"

        // Constantes de la tabla PERSONAJE
        private const val TABLA_PERSONAJE = "personaje"
        private const val KEY_ID_USUARIO = "id_usuario"
        private const val COLUMN_NOMBRE_PERSONAJE = "nombre"
        private const val COLUMN_RAZA = "raza"
        private const val COLUMN_CLASE = "clase"
        private const val COLUMN_ESTADO_VITAL = "estadoVital"
        private const val COLUMN_IMAGEN_PERSONAJE = "imagen"
        private const val COLUMN_SALUD = "salud"
        private const val COLUMN_ATAQUE = "ataque"
        private const val COLUMN_EXPERIENCIA = "experiencia"
        private const val COLUMN_NIVEL = "nivel"
        private const val COLUMN_SUERTE = "suerte"
        private const val COLUMN_DEFENSA = "defensa"
        private const val COLUMN_MONEDERO = "monedero"
    }

    override fun onCreate(db: SQLiteDatabase) {

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
                "$COLUMN_DEFENSA INTEGER," +
                "$COLUMN_MONEDERO INTEGER)"
        db.execSQL(PERSONAJE)

        val ARTICULOS = "CREATE TABLE $TABLA_ARTICULOS(" +
                "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOMBRE_ARTICULO TEXT, $COLUMN_PESO INTEGER, $COLUMN_PRECIO INTEGER," +
                "$COLUMN_TIPO_ARTICULO TEXT, $COLUMN_IMAGEN TEXT, $ID_USUARIO TEXT," +
                "FOREIGN KEY($ID_USUARIO) REFERENCES $TABLA_PERSONAJE($KEY_ID_USUARIO))"
        db.execSQL(ARTICULOS)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_ARTICULOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLA_PERSONAJE")
        onCreate(db)
    }

    fun insertarArticulo(articulo: Articulo, idUser: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(ID_USUARIO, idUser)
            put(COLUMN_NOMBRE_ARTICULO, articulo.getNombre().toString())
            put(COLUMN_PESO, articulo.getPeso())
        }
        db.insert(TABLA_ARTICULOS, null, values)
        db.close()
    }

    fun insertarPersonaje(personaje: Personaje) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_ID_USUARIO, personaje.getId())
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
            put(COLUMN_MONEDERO, personaje.getMonedero())
        }

        // Inserta el personaje con posible reemplazo si ya existe
        val resultado = db.insertWithOnConflict(TABLA_PERSONAJE, null, values, SQLiteDatabase.CONFLICT_REPLACE)

        if (resultado == -1L) {
            Log.e(TAG, "Error al insertar o reemplazar el personaje en la base de datos")
        } else {
            Log.d(TAG, "Personaje insertado o reemplazado exitosamente")
        }

        db.close()
    }

    @SuppressLint("Range")
    fun getArticulo(): ArrayList<Articulo> {
        val articulos = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_ARTICULOS"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex(KEY_ID))
                    val nombre = it.getString(it.getColumnIndex(COLUMN_NOMBRE_ARTICULO)).toNombre()!!
                    val peso = it.getInt(it.getColumnIndex(COLUMN_PESO))
                    val idUser = it.getString(it.getColumnIndex(ID_USUARIO))
                    val articulo = Articulo(id, nombre, peso)
                    articulo.apply {
                        setIdUser(idUser)
                    }
                    articulos.add(articulo)
                } while (it.moveToNext())
            }
        }
        return articulos
    }

    @SuppressLint("Range")
    fun getPersonaje(): ArrayList<Personaje> {
        val personajes = ArrayList<Personaje>()
        val selectQuery = "SELECT * FROM $TABLA_PERSONAJE"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getString(it.getColumnIndex(KEY_ID_USUARIO)) ?: ""
                    val nombre = it.getString(it.getColumnIndex(COLUMN_NOMBRE_PERSONAJE)) ?: ""
                    val razaString = it.getString(it.getColumnIndex(COLUMN_RAZA))
                    val raza = razaString.toRaza()!! //?: Raza.Elfo
                    val claseString = it.getString(it.getColumnIndex(COLUMN_CLASE))
                    val clase = claseString.toClase()!! //?: Clase.Brujo
                    val estadoVitalString = it.getString(it.getColumnIndex(COLUMN_ESTADO_VITAL))
                    val estadoVital = estadoVitalString.toEstadoVital()!! //?: EstadoVital.Joven
                    val salud = it.getInt(it.getColumnIndex(COLUMN_SALUD))
                    val ataque = it.getInt(it.getColumnIndex(COLUMN_ATAQUE))
                    val experiencia = it.getInt(it.getColumnIndex(COLUMN_EXPERIENCIA))
                    val nivel = it.getInt(it.getColumnIndex(COLUMN_NIVEL))
                    val suerte = it.getInt(it.getColumnIndex(COLUMN_SUERTE))
                    val defensa = it.getInt(it.getColumnIndex(COLUMN_DEFENSA))
                    val monedero = it.getInt(it.getColumnIndex(COLUMN_MONEDERO))
                    val personaje = Personaje(id, nombre, raza, clase, estadoVital)
                    personaje.apply {
                        setExperienciaN(experiencia)
                        setSalud(salud)
                        setAtaque(ataque)
                        setNivel(nivel)
                        setSuerte(suerte)
                        setDefensa(defensa)
                        setMonedero(monedero)
                    }
                    personajes.add(personaje)
                } while (it.moveToNext())
            }
        }
        return personajes
    }


    fun eliminarRegistro(id: Int): Boolean {
        val db = writableDatabase
        val whereClause = "$KEY_ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db.delete(TABLA_ARTICULOS, whereClause, whereArgs) > 0
    }
}

fun String.toNombre(): Articulo.Nombre? {
    return try {
        return Articulo.Nombre.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        null
    }
}
fun String.toRaza(): Personaje.Raza? {
    return try {
        return Personaje.Raza.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        null
    }
}
fun String.toClase(): Personaje.Clase? {
    return try {
        return Personaje.Clase.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        null
    }
}
fun String.toEstadoVital(): Personaje.EstadoVital? {
    return try {
        return Personaje.EstadoVital.valueOf(this.toUpperCase())
    } catch (e: IllegalArgumentException) {
        null
    }
}
