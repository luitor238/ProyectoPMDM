package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class CrearPersonajeActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner

    private lateinit var nombre: EditText
    private lateinit var btnAplicar: Button
    private lateinit var btnCrear: Button
    private lateinit var btnVolver: Button
    lateinit var textViewError: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)

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
        btnVolver = findViewById(R.id.btnVolver)
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
            btnVolver.visibility = View.VISIBLE
            textViewError.text = ""

            // Crear personaje

            // Funcion del boton crear
            btnCrear.setOnClickListener {
                val intent = Intent(this@CrearPersonajeActivity, ComenzarAventuraActivity::class.java)
                startActivity(intent)

            }
            btnVolver.setOnClickListener{
                btnAplicar.visibility = View.VISIBLE
                btnCrear.visibility = View.GONE
                btnVolver.visibility = View.GONE
                textViewError.text = ""
            }
        } else {
            textViewError.text = "Error: No puede haber campos vacíos."
        }
    }

   }