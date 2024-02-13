package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PeleaActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var result: TextView
    private lateinit var btnSeguir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)

        // Obtener la instancia única de variablesGlobales
        val variablesGlobales = variablesGlobales.getInstance()

        // Acceder a la variable global globalPersonaje
        val personaje = variablesGlobales.globalPersonaje



        imagen = findViewById(R.id.imageViewMonstruo)
        result = findViewById(R.id.textViewResultado)
        btnSeguir = findViewById(R.id.btnSeguir)



        Thread.sleep(3000)
        if() {
            result.text = personaje.pelea(monstruo)
        }

        btnSeguir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}