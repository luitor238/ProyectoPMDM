package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class  CrearPersonajeActivity : AppCompatActivity() {

    private lateinit var spnClase: Spinner
    private lateinit var spnRaza: Spinner
    private lateinit var spnEstVital: Spinner
    private lateinit var nickname: EditText
    private lateinit var imagen: ImageView
    private lateinit var btnCrear: Button
    private lateinit var btnVolver: ImageButton
    private lateinit var textViewError: TextView
    private lateinit var personaje: Personaje
    private lateinit var nombre: String
    private  var clase: String=""
    private  var raza: String=""
    private  var estadoVital: String=""
    private val TAG = "LoginActivity"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "Comienzo Actividad")
        // CREACION DE LA VISTA Y ASIGNACION DEL LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)

        //ASIGNACION DE LA FOTO POR DEFECTO ( EL CALVO)
        Log.d(TAG, "ASIGNACION DE LA FOTO POR DEFECTO ( EL CALVO)")
        imagen = findViewById(R.id.imageViewEnemigo)
        imagen.setImageResource(R.drawable.personaje_en_blanco)

        // ASIGNACION DEL NICKNAME A LA VARIABLE NOMBRE
        Log.d(TAG, "ASIGNACION DEL NICKNAME A LA VARIABLE NOMBRE")
        nickname = findViewById<EditText>(R.id.editTextNombre)

        // SPINNERS PARA LA SELECCION DE CLASE, RAZA Y ESTADO VITAL Y ASIGNACION DE SUS RESPECTIVAS VARIABLES
        Log.d(TAG, "SPINNERS PARA LA SELECCION DE CLASE, RAZA Y ESTADO VITAL Y ASIGNACION DE SUS RESPECTIVAS VARIABLES")
        spnClase = findViewById(R.id.spnClase)
        ArrayAdapter.createFromResource(this, R.array.Clase, android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnClase.adapter = adapter
        }
        spnClase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    clase = resources.getStringArray(R.array.Clase)[position].toString()
                    selectAspect()
                }

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
                if (position != 0) {
                    estadoVital = resources.getStringArray(R.array.EstadoVital)[position].toString()
                    selectAspect()
                }
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
                if (position != 0) {
                    raza = resources.getStringArray(R.array.Raza)[position].toString()
                    selectAspect()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        // Botones de aplicacion y creacion
        btnCrear = findViewById(R.id.btnCrear)
        btnVolver = findViewById(R.id.imageBtnGoBack)


        Log.d(TAG, "btnCrear")
        btnCrear.setOnClickListener {
            val intent = Intent(this,VerPersonajeActivity::class.java)


            try{
                nombre = nickname.text.toString()
                val razaElegida = if (raza.equals("")) Personaje.Raza.valueOf(Personaje.Raza.Humano.toString()) else Personaje.Raza.valueOf(raza)
                val claseElegida = if (clase.equals("")) Personaje.Clase.valueOf(Personaje.Clase.Brujo.toString()) else Personaje.Clase.valueOf(clase)
                val estadoVitalElegido = if (estadoVital.equals("")) Personaje.EstadoVital.valueOf(Personaje.EstadoVital.Joven.toString()) else Personaje.EstadoVital.valueOf(estadoVital)

                Log.d(TAG," Nombre: ${nombre}")
                Log.d(TAG, "Raza Intent: $razaElegida")
                Log.d(TAG, "Clase Intent: $claseElegida")
                Log.d(TAG, "Estado Vital Intent: $estadoVitalElegido")


                personaje = Personaje(
                    nombre,
                    razaElegida,
                    claseElegida,
                    estadoVitalElegido
                )


                val dbHelper = DatabaseHelper(this)
                val globalInstance = variableGlobal.getInstance()
                dbHelper.insertarPersonaje(globalInstance.toString(),personaje)

                Log.d(TAG, "Usuario Creado")

            }catch(e: Exception){
                Log.d(TAG, "Error al Crear el Personaje")
            }

            try{
                startActivity(intent)
                Log.d(TAG, "Actividad Cambiada")
            }catch (e: Exception) {
                Log.d(TAG, "Error al cambiar de Actividad")
            }
        }

        Log.d(TAG, "btnVolver")
        btnVolver.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun selectAspect() {
        when (clase) {
            "Brujo", "" -> {
                when (raza) {
                    "Humano", "" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.humano_brujo_joven)

                            "Adulto" -> imagen.setImageResource(R.drawable.humano_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_brujo_viejojfif)
                        }
                    }

                    "Elfo" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.elfo_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_brujo_viejo)
                        }
                    }

                    "Enano" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.enano_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_brujo_viejo)
                        }
                    }

                    "Maldito" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.maldito_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_brujo_viejo)
                        }
                    }
                }
            }

            "Mago" -> {
                when (raza) {
                    "Humano", "" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.humano_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.humano_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_mago_viejo)
                        }
                    }

                    "Elfo" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.elfo_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_mago_viejo)
                        }
                    }

                    "Enano" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.enano_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_mago_viejo)
                        }
                    }

                    "Maldito" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.maldito_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_mago_viejo)
                        }
                    }
                }
            }

            "Guerrero" -> {
                when (raza) {
                    "Humano", "" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.humano_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.humano_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_guerrero_viejo)
                        }
                    }

                    "Elfo" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.elfo_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_guerrero_viejo)
                        }
                    }

                    "Enano" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.enano_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_guerrero_viejo)
                        }
                    }

                    "Maldito" -> {
                        when (estadoVital) {
                            "Joven", "" -> imagen.setImageResource(R.drawable.maldito_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_guerrero_viejo)
                        }
                    }
                }
            }
        }
    }
}


