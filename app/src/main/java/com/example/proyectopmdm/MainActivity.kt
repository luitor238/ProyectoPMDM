package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
    }

   }