package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout

class MochilaActivity : AppCompatActivity() {

    private lateinit var btnVolver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mochila)

        val dbHelper = DatabaseHelper(this)

        var articulos = dbHelper.getArticulo()

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        //Agregar los articulos al scroll
        for(i in 0..articulos.size){
            agregarArticulo(articulos[i].getImagen().toInt(), articulos[i].getNombre().toString())
        }

        //Boton volver al menu
        btnVolver.setOnClickListener(){
            val intent = Intent(this,MenuOpcionesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun agregarArticulo(url: Int, nombre: String) {
        val nuevoArticulo = ImageButton(this)

        // ATRIBUTOS
        val layoutParams = nuevoArticulo.layoutParams
        layoutParams.height = 220
        nuevoArticulo.layoutParams = layoutParams
        nuevoArticulo.setImageResource(url)
        nuevoArticulo.contentDescription = nombre
        nuevoArticulo.setBackgroundResource(R.color.transparent)
        nuevoArticulo.scaleType = ImageView.ScaleType.FIT_CENTER

        nuevoArticulo.setOnClickListener {
            val intent = Intent(this, VerArticuloActivity::class.java)
            intent.putExtra("nombre",nombre)
            startActivity(intent)
        }

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(nuevoArticulo)

    }
}