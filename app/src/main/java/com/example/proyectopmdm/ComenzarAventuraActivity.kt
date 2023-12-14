package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ComenzarAventuraActivity : AppCompatActivity() {

    private lateinit var btnVolver: Button
    private lateinit var btnJugar: Button

    private lateinit var personaje: Personaje
    private lateinit var imagen: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comenzar_aventura)

        // Asignamos valores a los textView

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

        // Asignamos imagen

        imagen = findViewById<ImageView>(R.id.imageView2)

        when(personaje.getClase().toString()){
            "BRUJO" -> {
                when(personaje.getRaza().toString()){
                    "HUMANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.humano_brujo_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.humano_brujo_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.humano_brujo_viejojfif)
                        }
                    }
                    "ELFO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.elfo_brujo_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.elfo_brujo_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.elfo_brujo_viejo)
                        }
                    }
                    "ENANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.enano_brujo_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.enano_brujo_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.enano_brujo_viejo)
                        }
                    }
                    "MALDITO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.maldito_brujo_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.maldito_brujo_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.maldito_brujo_viejo)
                        }
                    }
                }
            }
            "MAGO" -> {
                when(personaje.getRaza().toString()){
                    "HUMANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.humano_mago_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.humano_mago_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.humano_mago_viejo)
                        }
                    }
                    "ELFO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.elfo_mago_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.elfo_mago_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.elfo_mago_viejo)
                        }
                    }
                    "ENANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.enano_mago_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.enano_mago_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.enano_mago_viejo)
                        }
                    }
                    "MALDITO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.maldito_mago_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.maldito_mago_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.maldito_mago_viejo)
                        }
                    }
                }
            }
            "GUERRERO" -> {
                when(personaje.getRaza().toString()){
                    "HUMANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.humano_guerrero_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.humano_guerrero_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.humano_guerrero_viejo)
                        }
                    }
                    "ELFO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.elfo_guerrero_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.elfo_guerrero_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.elfo_guerrero_viejo)
                        }
                    }
                    "ENANO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.enano_guerrero_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.enano_guerrero_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.enano_guerrero_viejo)
                        }
                    }
                    "MALDITO" -> {
                        when(personaje.getEstadoVital().toString()){
                            "JOVEN" -> imagen.setImageResource(R.drawable.maldito_guerrero_joven)
                            "ADULTO" -> imagen.setImageResource(R.drawable.maldito_guerrero_adulto)
                            "ANCIANO" -> imagen.setImageResource(R.drawable.maldito_guerrero_viejo)
                        }
                    }
                }
            }
        }

        // Funcion de los botones

        btnVolver=findViewById(R.id.btnVolverCrearPersonaje)
        btnJugar=findViewById(R.id.btnJugar)

        btnVolver.setOnClickListener {
            val intent = Intent(this@ComenzarAventuraActivity, MainActivity::class.java)
            startActivity(intent)
        }
        btnJugar.setOnClickListener {
            val intent = Intent(this@ComenzarAventuraActivity, MenuOpcionesActivity::class.java)
            startActivity(intent)
        }
    }
}