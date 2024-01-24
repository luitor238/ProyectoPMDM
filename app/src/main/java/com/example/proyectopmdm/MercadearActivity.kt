package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MercadearActivity : AppCompatActivity(){
    private lateinit var imagen: ImageView
    private lateinit var btnContinuar: Button
    private lateinit var btnComerciar: Button

    private lateinit var nombre1: TextView
    private lateinit var tipoArticulo: TextView
    private lateinit var imagen: ImageView
    private lateinit var peso1: TextView
    private lateinit var precio: TextView
    private lateinit var aumentoAtaque: TextView
    private lateinit var aumentoDefensa: TextView
    private lateinit var aumentoVida: TextView
    private lateinit var btnRecoger: Button
    private lateinit var btnContinuar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mercader)


        btnContinuar = findViewById(R.id.btnContinuar)
        btnComerciar = findViewById(R.id.btnComerciar)


        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            setContentView(R.layout.activity_objeto)

            nombre1 = findViewById(R.id.textViewNombre)
            tipoArticulo = findViewById(R.id.textViewTipoArticulo)
            peso1 = findViewById(R.id.textViewPeso)
            precio = findViewById(R.id.textViewPrecio)
            aumentoAtaque = findViewById(R.id.textViewAumentoAtaque)
            aumentoDefensa = findViewById(R.id.textViewAumentoDefensa)
            aumentoVida = findViewById(R.id.textViewAumentoVida)

        }


    }
}

//CLASE PARA LA BASE DE DATOS
class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "Articulo.db"
        private const val TABLA_ARTICULOS = "articulos"
        private const val KEY_ID = "_id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_TIPOARTICULO = "tipoArticulo"
        private const val COLUMN_IMAGEN = "imagen"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_ARTICULOS(" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE TEXT, $COLUMN_PESO INTEGER, $COLUMN_PRECIO INTEGER," +
                "$COLUMN_TIPOARTICULO TEXT, $COLUMN_IMAGEN TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_ARTICULOS")
        onCreate(db)
    }

    fun insertarArticulo(articulo: Articulo){
        val db =this.writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_NOMBRE, articulo.getNombre().toString())
            put(COLUMN_PESO, articulo.getPeso())
            put(COLUMN_PRECIO, articulo.getPrecio())
            put(COLUMN_TIPOARTICULO,articulo.getTipoArticulo().toString())
            put(COLUMN_IMAGEN,articulo.getImagen())
        }
        db.insert(TABLA_ARTICULOS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getArticulo(): ArrayList<Articulo> {
        val articulos = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_ARTICULOS"
        val db= this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val peso = cursor.getInt(cursor.getColumnIndex(COLUMN_PESO))
                val precio = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO))
                val tipoArticulo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPOARTICULO))
                val imagen = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return articulos
    }

}