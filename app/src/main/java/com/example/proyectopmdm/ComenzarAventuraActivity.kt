package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ComenzarAventuraActivity : AppCompatActivity() {

    private lateinit var btnVolver: Button
    private lateinit var btnJugar: Button

    private lateinit var personaje: Personaje


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comenzar_aventura)

        personaje = intent.getSerializableExtra("Personaje") as Personaje

        val nombre = findViewById<TextView>(R.id.varNombre)
        val expNivel = findViewById<TextView>(R.id.textExpNivel)
        val clase = findViewById<TextView>(R.id.varClase)
        val raza = findViewById<TextView>(R.id.varRaza)
        val estadoVital = findViewById<TextView>(R.id.varEstadoVital)
        val salud = findViewById<TextView>(R.id.varSalud)
        val ataque = findViewById<TextView>(R.id.varAtaque)
        val defensa = findViewById<TextView>(R.id.varDefensa)
        val suerte = findViewById<TextView>(R.id.varSuerte)

        nombre.text = personaje.getNombre()
        expNivel.text = "${personaje.getExperiencia()}/${personaje.getNivel()}"
        clase.text = personaje.getClase().toString()
        raza.text = personaje.getRaza().toString()
        estadoVital.text = personaje.getEstadoVital().toString()
        salud.text = personaje.getSalud().toString()
        ataque.text = personaje.getAtaque().toString()
        defensa.text = personaje.getDefensa().toString()
        suerte.text = personaje.getSuerte().toString()


        btnVolver=findViewById(R.id.btnVolverCrearPersonaje)
        btnJugar=findViewById(R.id.btnJugar)

        btnVolver.setOnClickListener {
            val intent = Intent(this@ComenzarAventuraActivity, MainActivity::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            // val intent = Intent(this@ComenzarAventuraActivity, **nombre_menu**:class.java)
            // startActivity(intent)
        }
    }
}