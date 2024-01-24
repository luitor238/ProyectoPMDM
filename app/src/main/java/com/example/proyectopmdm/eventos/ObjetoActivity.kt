package com.example.proyectopmdm.eventos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.proyectopmdm.Articulo
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.R
import kotlin.random.Random

class ObjetoActivity : AppCompatActivity() {

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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        //TextView's
        nombre1 = findViewById(R.id.textViewNombre)
        tipoArticulo = findViewById(R.id.textViewTipoArticulo)
        peso1 = findViewById(R.id.textViewPeso)
        precio = findViewById(R.id.textViewPrecio)
        aumentoAtaque = findViewById(R.id.textViewAumentoAtaque)
        aumentoDefensa = findViewById(R.id.textViewAumentoDefensa)
        aumentoVida = findViewById(R.id.textViewAumentoVida)

        //Botones
        btnRecoger = findViewById(R.id.btnRecoger)
        btnContinuar = findViewById(R.id.btnContinuar)


        val dbHelper = DatabaseHelper(this)

        //Crear articulo aleatorio
        val nombre = Articulo.Nombre.values()[Random.nextInt(Articulo.Nombre.values().size)]
        val peso = Random.nextInt(1, 5)
        val articulo = Articulo(nombre,peso)

        //Asignar atributos del articulo a los textView
        nombre1.text = articulo.getNombre().toString()
        tipoArticulo.text = articulo.getTipoArticulo().toString()
        //imagen.setImageResource(articulo.getImagen().toInt())
        peso1.text = articulo.getPeso().toString()
        precio.text = articulo.getPrecio().toString()
        aumentoAtaque.text = articulo.getAumentoAtaque().toString()
        aumentoDefensa.text = articulo.getAumentoDefensa().toString()
        aumentoVida.text = articulo.getAumentoVida().toString()


        //Boton recoger articulo
        btnRecoger.setOnClickListener {
            dbHelper.insertarArticulo(articulo)
            Toast.makeText(this, "Articulo añadido!", Toast.LENGTH_SHORT).show()
            Thread.sleep(2000)
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        //Boton volver al DadoActivity
        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
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
        val createTable = "CREATE TABLE $TABLA_ARTICULOS ($KEY_ID INTEGER PRIMARY KEY," +
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