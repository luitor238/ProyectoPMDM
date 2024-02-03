package com.example.proyectopmdm.eventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.R

class MercaderActivity : AppCompatActivity() {
    private lateinit var imagenes: Array<ImageButton>
    private lateinit var btnVolver: Array<ImageButton>
    private lateinit var btnComprar: Array<Button>
    private lateinit var btnVender: Array<Button>
    private lateinit var textos: Array<TextView>
    private lateinit var btnComerciar: Button
    private lateinit var vistas:  Array<View>
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)



        imagenes = Array(10) { index -> findViewById<ImageButton>(resources.getIdentifier("imagen${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de las imagenes")


        btnVolver = Array(3) { index -> findViewById<ImageButton>(resources.getIdentifier("btnVolver${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de los volver")

        textos = Array(3) { index -> findViewById<TextView>(resources.getIdentifier("texto${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de los textos")
/*
        btnComprar = Array(2) { index -> findViewById<ImageButton>(resources.getIdentifier("btnComprar${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de los comprar")

        btnVender = Array(1) { index -> findViewById<ImageButton>(resources.getIdentifier("btnVender${index + 1}", "id", packageName)) }
        Log.d(TAG, "Comienzo Actividad")*/

        vistas = Array(3) { index -> findViewById<View>(resources.getIdentifier("vista${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de las vistas")

        btnComerciar = findViewById(R.id.btnComerciar)
        Log.d(TAG, "Inicializacion boton comerciar")



        btnComerciar.setOnClickListener {
                // Cambiar la visibilidad de las vistas
                if (vistas[1].visibility == View.VISIBLE) {
                    vistas[1].visibility = View.GONE

            }
        }
    }
}