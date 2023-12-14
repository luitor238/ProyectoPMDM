package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ComenzarAventuraActivity : AppCompatActivity() {

    private lateinit var btnVolver: Button
    private lateinit var btnJugar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comenzar_aventura)

        btnVolver=findViewById(R.id.btnVolverCrearPersonaje)
        btnJugar=findViewById(R.id.btnJugar)

        btnVolver.setOnClickListener {
            val intent = Intent(this@ComenzarAventuraActivity, CrearPersonajeActivity::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            // val intent = Intent(this@ComenzarAventuraActivity, **nombre_menu**:class.java)
            // startActivity(intent)
        }
    }
}