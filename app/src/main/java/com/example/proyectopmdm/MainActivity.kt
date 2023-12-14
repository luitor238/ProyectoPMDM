package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner

    private lateinit var nickname: EditText
    private lateinit var btnAplicar: Button
    private lateinit var btnCrear: Button
    private lateinit var btnVolver: Button
    private lateinit var textViewError: TextView

    private lateinit var personaje: Personaje
    private lateinit var nombre: String
    private lateinit var clase: String
    private lateinit var raza: String
    private lateinit var estadoVital: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignacion del nickname a la variable nombre

        nickname = findViewById<EditText>(R.id.editTextNombre)
        nombre = nickname.text.toString()

        // Spinners de seleccion de clase, raza y estado vital y asignacion a sus respectivas variables

        spnClase = findViewById(R.id.spnClase)
        ArrayAdapter.createFromResource(this, R.array.Clase, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnClase.adapter = adapter
        }
        spnClase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                clase = resources.getStringArray(R.array.Clase)[position].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spnRaza = findViewById(R.id.spnRaza)
        ArrayAdapter.createFromResource(this, R.array.Raza, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnRaza.adapter = adapter
        }
        spnRaza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                raza = resources.getStringArray(R.array.Raza)[position].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spnEstVital = findViewById(R.id.spnEstadoVital)
        ArrayAdapter.createFromResource(this, R.array.EstadoVital, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnEstVital.adapter = adapter
        }
        spnEstVital.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                estadoVital = resources.getStringArray(R.array.EstadoVital)[position].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        // Botones de aplicacion y creacion

        btnAplicar = findViewById(R.id.btnAplicar)
        btnCrear = findViewById(R.id.btnCrear)
        btnVolver = findViewById(R.id.btnVolver)
        textViewError = findViewById(R.id.textViewError)

        btnAplicar.setOnClickListener {
            clickBotonAplicar()
        }
    }

    private fun clickBotonAplicar() {
        val texto = nickname.text.toString().trim()

        if (texto.isNotEmpty()) {
            // Si el nombre está relleno, oculta el botón "Aplicar" muestra el botón "Crear"
            btnAplicar.visibility = View.GONE
            btnCrear.visibility = View.VISIBLE
            btnVolver.visibility = View.VISIBLE
            textViewError.text = ""

            // Crear personaje

            // Funcion del boton crear
            btnCrear.setOnClickListener {
                personaje = Personaje(nombre,Personaje.Raza.valueOf(raza.toUpperCase()),Personaje.Clase.valueOf(clase.toUpperCase()),Personaje.EstadoVital.valueOf(estadoVital.toUpperCase()))
                val intent = Intent(this@MainActivity, ComenzarAventuraActivity::class.java)
                intent.putExtra("Personaje",personaje)
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
