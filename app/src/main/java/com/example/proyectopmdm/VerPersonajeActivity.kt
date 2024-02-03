package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerPersonajeActivity : AppCompatActivity() {

    //DECLARACION DE VARIABLES
    private lateinit var btnVolver: Button
    private lateinit var btnJugar: Button
    private lateinit var imagen: ImageView
    private lateinit var personajeFinal: variablesGlobales
    private val TAG = "LoginActivity"


    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {


        Log.d(TAG, "Cambio a Actividad {VerPersonajeactivity}")
        // CREACION DE LA VISTA Y ASIGNACION DEL LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_personaje)


        // Obtener la instancia única de variablesGlobales
        val variablesGlobales = variablesGlobales.getInstance()

        // Acceder a la variable global globalPersonaje
        val personaje = variablesGlobales.globalPersonaje


        val nombre = findViewById<TextView>(R.id.varNombre)
        val expNivel = findViewById<TextView>(R.id.textExpNivel)
        val clase = findViewById<TextView>(R.id.varClase)
        val raza = findViewById<TextView>(R.id.varRaza)
        val estadoVital = findViewById<TextView>(R.id.varEstadoVital)
        val salud = findViewById<TextView>(R.id.varSalud)
        val ataque = findViewById<TextView>(R.id.varAtaque)
        val defensa = findViewById<TextView>(R.id.varDefensa)
        val suerte = findViewById<TextView>(R.id.varSuerte)

        Log.d(TAG, "Asignacion de los parametros del Personaje a la Actividad")

        if (personaje != null) {
            Log.d(TAG, "Datos Personaje")
            Log.d(TAG,"NOMBRE DEL PERSONAJE ${personaje.getNombre()}")
            nombre.text = personaje.getNombre()
            expNivel.text = "${personaje.getExperiencia()}/${personaje.getNivel()}"
            clase.text = personaje.getClase().toString()
            raza.text = personaje.getRaza().toString()
            estadoVital.text = personaje.getEstadoVital().toString()
            salud.text = personaje.getSalud().toString()
            ataque.text = personaje.getAtaque().toString()
            defensa.text = personaje.getDefensa().toString()
            suerte.text = personaje.getSuerte().toString()

        }



        // Asignamos imagen
        imagen = findViewById<ImageView>(R.id.imageView2)

        Log.d(TAG, "Asignacion de la imagen del Personaje a la Actividad")

        if (personaje != null) {
            imagen.setImageResource(
                resources.getIdentifier(
                    personaje.getImagen(),
                    "drawable",
                    packageName
                )
            )
        }

        btnVolver=findViewById(R.id.btnVolverCrearPersonaje)
        btnJugar=findViewById(R.id.btnJugar)

        btnVolver.setOnClickListener {
            val intent = Intent(this@VerPersonajeActivity, DadoActivity::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            val intent = Intent(this@VerPersonajeActivity, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}