package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var verPersonaje: Button
private lateinit var crearMonstruo: Button
private lateinit var pelear: Button
private lateinit var comunicacion: Button

class MenuOpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        verPersonaje = findViewById(R.id.btnVerPersonaje)
        crearMonstruo = findViewById(R.id.btnCrearMonstruo)
        pelear = findViewById(R.id.btnPelear)
        comunicacion = findViewById(R.id.btnComunicacion)

        verPersonaje.setOnClickListener{
            val intent = Intent(this,VerPersonajeActivity::class.java)
            startActivity(intent)
        }
        /*
        crearMonstruo.setOnClickListener {
            val intent 0 Intent(this,CrearMonstruoActivity::class.java)
            startActivity(intent)
        }
        pelear.setOnClickListener {
            val intent = Intent(this,PelearActivity::class.java)
            startActivity(intent)
        }
        comunicacion.setOnClickListener {
            val intent= Intent(this,ComunicacionActivity::class.java)
            startActivity(intent)
        }
         */


    }
}