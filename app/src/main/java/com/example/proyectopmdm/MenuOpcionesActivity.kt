package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MenuOpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        val personaje = intent.getSerializableExtra("Personaje") as Personaje

    }
}