package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class VerArticuloActivity : AppCompatActivity() {

    private lateinit var articulo: Articulo

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
        imagen = findViewById(R.id.imageViewEnemigo)
        //Botones
        btnVolver = findViewById(R.id.btnVolver)

        val dbHelper = DatabaseHelper(this)

        articulo = intent.getSerializableExtra("articulo") as Articulo

        nombre1.text = articulo.getNombre().toString()
        tipoArticulo.text = articulo.getTipoArticulo().toString()
        imagen.setImageResource(resources.getIdentifier(articulo.getImagen(), "drawable", packageName))
        peso1.text = articulo.getPeso().toString()
        precio.text = articulo.getPrecio().toString()
        aumentoAtaque.text = articulo.getAumentoAtaque().toString()
        aumentoDefensa.text = articulo.getAumentoDefensa().toString()
        aumentoVida.text = articulo.getAumentoVida().toString()


        btnVolver.setOnClickListener {
            val intent = Intent(this, MochilaActivity::class.java)
            startActivity(intent)
        }
    }
}
















