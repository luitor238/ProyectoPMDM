package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale


class CrearPersonajeActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner

    private lateinit var nickname: EditText
    private lateinit var imagen: ImageView
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
        // CREACION DE LA VISTA Y ASIGNACION DEL LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)

        //ASIGNACION DE LA FOTO POR DEFECTO ( EL CALVO)

        imagen = findViewById(R.id.imageView)
        imagen.setImageResource(R.drawable.personaje_en_blanco)

        // ASIGNACION DEL NICKNAME A LA VARIABLE NOMBRE
        nickname = findViewById<EditText>(R.id.editTextNombre)
        nombre = nickname.text.toString()


        // SPINNERS PARA LA SELECCION DE CLASE, RAZA Y ESTADO VITAL Y ASIGNACION DE SUS RESPECTIVAS VARIABLES
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

    @SuppressLint("SetTextI18n")
    private fun clickBotonAplicar() {
        val texto = nickname.text.toString().trim()

        if (texto.isNotEmpty()) {
            // Si el nombre está relleno, oculta el botón "Aplicar" muestra el botón "Crear"
            btnAplicar.visibility = View.GONE
            btnCrear.visibility = View.VISIBLE
            btnVolver.visibility = View.VISIBLE
            textViewError.text = ""

            // Seleccionamos la imagen que corresponda

            imagen = findViewById(R.id.imageView)

            when(clase){
                "Brujo" -> {
                    when(raza){
                        "Humano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.humano_brujo_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.humano_brujo_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.humano_brujo_viejojfif)
                            }
                        }
                        "Elfo" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.elfo_brujo_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.elfo_brujo_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.elfo_brujo_viejo)
                            }
                        }
                        "Enano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.enano_brujo_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.enano_brujo_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.enano_brujo_viejo)
                            }
                        }
                        "Maldito" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.maldito_brujo_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.maldito_brujo_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.maldito_brujo_viejo)
                            }
                        }
                    }
                }
                "Mago" -> {
                    when(raza){
                        "Humano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.humano_mago_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.humano_mago_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.humano_mago_viejo)
                            }
                        }
                        "Elfo" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.elfo_mago_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.elfo_mago_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.elfo_mago_viejo)
                            }
                        }
                        "Enano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.enano_mago_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.enano_mago_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.enano_mago_viejo)
                            }
                        }
                        "Maldito" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.maldito_mago_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.maldito_mago_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.maldito_mago_viejo)
                            }
                        }
                    }
                }
                "Guerrero" -> {
                    when(raza){
                        "Humano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.humano_guerrero_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.humano_guerrero_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.humano_guerrero_viejo)
                            }
                        }
                        "Elfo" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.elfo_guerrero_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.elfo_guerrero_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.elfo_guerrero_viejo)
                            }
                        }
                        "Enano" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.enano_guerrero_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.enano_guerrero_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.enano_guerrero_viejo)
                            }
                        }
                        "Maldito" -> {
                            when(estadoVital){
                                "Joven" -> imagen.setImageResource(R.drawable.maldito_guerrero_joven)
                                "Adulto" -> imagen.setImageResource(R.drawable.maldito_guerrero_adulto)
                                "Anciano" -> imagen.setImageResource(R.drawable.maldito_guerrero_viejo)
                            }
                        }
                    }
                }
            }

            // Funcion del boton crear

            btnCrear.setOnClickListener {
                personaje = Personaje(nombre,Personaje.Raza.valueOf(raza),Personaje.Clase.valueOf(clase),Personaje.EstadoVital.valueOf(estadoVital))
                val intent = Intent(this@CrearPersonajeActivity, VerPersonajeActivity::class.java)

                intent.putExtra("Personaje",personaje)

                intent.putExtra("Imagen", R.drawable.maldito_brujo_adulto)
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
