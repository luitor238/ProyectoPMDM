package com.example.proyectopmdm

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
    private val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {


        Log.d(TAG, "Cambio a Actividad {VerPersonajeactivity}")
        // CREACION DE LA VISTA Y ASIGNACION DEL LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_personaje)

        var personaje: Personaje  = intent.getSerializableExtra("Personaje") as Personaje
        // Asignamos valores a los textView

        Log.d(TAG, "Recogida del Personaje en el Intent")



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

        nombre.text = personaje.getNombre()
        Log.d(TAG, "nombre: ${personaje.getNombre()}")

        expNivel.text = "${personaje.getExperiencia()}/${personaje.getNivel()}"
        clase.text = personaje.getClase().toString()
        raza.text = personaje.getRaza().toString()
        estadoVital.text = personaje.getEstadoVital().toString()
        salud.text = personaje.getSalud().toString()
        ataque.text = personaje.getAtaque().toString()
        defensa.text = personaje.getDefensa().toString()
        suerte.text = personaje.getSuerte().toString()


        // Asignamos imagen
        imagen = findViewById<ImageView>(R.id.imageView2)

        Log.d(TAG, "Asignacion de la imagen del Personaje a la Actividad")


        imagen.setImageResource(resources.getIdentifier(personaje.getImagen(), "drawable", packageName))


        btnVolver=findViewById(R.id.btnVolverCrearPersonaje)
        btnJugar=findViewById(R.id.btnJugar)

        btnVolver.setOnClickListener {
            val intent = Intent(this@VerPersonajeActivity, CrearPersonajeActivity::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            val intent = Intent(this@VerPersonajeActivity, MenuOpcionesActivity::class.java)
            startActivity(intent)
        }
    }
}