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
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.R
import com.example.proyectopmdm.VerPersonajeActivity

class MercaderActivity : AppCompatActivity() {
    private lateinit var imagenes: Array<Pair<ImageButton, Int>>
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
        btnVolver = Array(3) { index -> findViewById<ImageButton>(resources.getIdentifier("btnVolver${index + 1}", "id", packageName)) }
        textos = Array(3) { index -> findViewById<TextView>(resources.getIdentifier("texto${index + 1}", "id", packageName)) }
        btnComprar = Array(2) { index -> findViewById<Button>(resources.getIdentifier("btnComprar${index + 1}", "id", packageName)) }
        btnVender = Array(1) { index -> findViewById<Button>(resources.getIdentifier("btnVender${index + 1}", "id", packageName)) }
        vistas = Array(3) { index -> findViewById<View>(resources.getIdentifier("vista${index + 1}", "id", packageName)) }
        btnComerciar = findViewById(R.id.btnComerciar)
        Log.d(TAG, "Inicializacion de los elementos")



        btnComerciar.setOnClickListener {
            Log.d(TAG, "Boton comerciar")
            // Cambiar la visibilidad de las vistas
                if (vistas[0].visibility == View.VISIBLE) {
                    vistas[0].visibility = View.GONE
                    vistas[1].visibility = View.VISIBLE
                }
        }
        btnVolver[0].setOnClickListener {
            val intent = Intent(this, MenuOpcionesActivity::class.java)

            startActivity(intent)
        }

        btnComprar[0].setOnClickListener {
            Log.d(TAG, "Boton comerciar")
            // Cambiar la visibilidad de las vistas
            if (vistas[1].visibility == View.VISIBLE) {
                vistas[1].visibility = View.GONE
                vistas[2].visibility = View.VISIBLE
            }
        }
    }
}