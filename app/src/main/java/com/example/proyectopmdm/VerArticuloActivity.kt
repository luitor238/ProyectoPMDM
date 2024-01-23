package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectopmdm.eventos.DatabaseHelper

class VerArticuloActivity : AppCompatActivity() {

    private lateinit var nombre1: TextView
    private lateinit var tipoArticulo: TextView
    private lateinit var imagen: ImageView
    private lateinit var peso1: TextView
    private lateinit var precio: TextView
    private lateinit var aumentoAtaque: TextView
    private lateinit var aumentoDefensa: TextView
    private lateinit var aumentoVida: TextView

    private lateinit var btnVolver: Button

    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_articulo)

        //TextView's
        nombre1 = findViewById(R.id.textViewNombre)
        tipoArticulo = findViewById(R.id.textViewTipoArticulo)
        peso1 = findViewById(R.id.textViewPeso)
        precio = findViewById(R.id.textViewPrecio)
        aumentoAtaque = findViewById(R.id.textViewAumentoAtaque)
        aumentoDefensa = findViewById(R.id.textViewAumentoDefensa)
        aumentoVida = findViewById(R.id.textViewAumentoVida)
        //Botones
        btnVolver = findViewById(R.id.btnVolver)


        val dbHelper = DatabaseHelper(this)

        val selectorArticulo = 2    //Crear metodo para que aparezca el articulo seleccionado
        val articulos = dbHelper.getArticulo()

        //Muestra atributos del articulo
        nombre1.text = articulos[selectorArticulo].getNombre().toString()
        tipoArticulo.text = articulos[selectorArticulo].getTipoArticulo().toString()
        imagen.setImageResource(articulos[selectorArticulo].getImagen().toInt())
        peso1.text = articulos[selectorArticulo].getPeso().toString()
        precio.text = articulos[selectorArticulo].getPrecio().toString()
        aumentoAtaque.text = articulos[selectorArticulo].getAumentoAtaque().toString()
        aumentoDefensa.text = articulos[selectorArticulo].getAumentoDefensa().toString()
        aumentoVida.text = articulos[selectorArticulo].getAumentoVida().toString()


        btnVolver.setOnClickListener {
            val intent = Intent(this, MochilaActivity::class.java)
            startActivity(intent)
        }
    }
}
















