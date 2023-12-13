package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner

    private lateinit var nombre: EditText
    private lateinit var btnAplicar: Button
    private lateinit var btnCrear: Button
    lateinit var textViewError: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Espiner desplegables

        spnClase = findViewById(R.id.spnClase)
        ArrayAdapter.createFromResource(this, R.array.Clase, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnClase.adapter = adapter
        }
        spnRaza = findViewById(R.id.spnRaza)
        ArrayAdapter.createFromResource(this, R.array.Raza, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnRaza.adapter = adapter
        }
        spnEstVital = findViewById(R.id.spnEstadoVital)
        ArrayAdapter.createFromResource(this, R.array.EstadoVital, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnEstVital.adapter = adapter
        }

        // Boton de aplicacion y creacion

        nombre = findViewById(R.id.editTextNombre)
        btnAplicar = findViewById(R.id.btnAplicar)
        btnCrear = findViewById(R.id.btnCrear)
        textViewError = findViewById(R.id.textViewError)

        btnAplicar.setOnClickListener {
            clickBotonAplicar()
        }
    }

    private fun clickBotonAplicar() {
        val texto = nombre.text.toString().trim()

        if (texto.isNotEmpty()) {
            // Si el nombre está relleno, oculta el botón "Aplicar" muestra el botón "Crear"
            btnAplicar.visibility = View.GONE
            btnCrear.visibility = View.VISIBLE
            textViewError.text = ""

            // Crear personaje

            // Funcion del boton crear
            btnCrear.setOnClickListener {
                // Falta poner codigo para crear personaje y cambiar de activity
            }
        } else {
            textViewError.text = "Error: No puede haber campos vacíos."
        }
    }

   }