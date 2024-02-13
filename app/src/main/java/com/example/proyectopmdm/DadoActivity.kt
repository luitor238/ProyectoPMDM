package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectopmdm.eventos.CiudadActivity
import com.example.proyectopmdm.eventos.EnemigoActivity
import com.example.proyectopmdm.eventos.MercaderActivity
import com.example.proyectopmdm.eventos.ObjetoActivity
import kotlin.random.Random


class DadoActivity : AppCompatActivity() {

    private lateinit var textViewExp: TextView
    private lateinit var textViewMonedas: TextView
    private lateinit var textViewNombre: TextView
    private lateinit var btnTirarDado: ImageButton
    private lateinit var btnMenu: Button
    private lateinit var btnMapa: ImageButton
    private lateinit var btnverPersonaje: ImageButton
    private lateinit var btnMochila: ImageButton


    private val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dado)

        // Obtener la instancia única de variablesGlobales
        val variablesGlobales = variablesGlobales.getInstance()

        // Acceder a la variable global globalPersonaje
        val personaje = variablesGlobales.globalPersonaje

        textViewExp = findViewById(R.id.textViewExperiencia)
        textViewMonedas = findViewById(R.id.textViewMonedas)
        textViewNombre = findViewById(R.id.textViewNombre)
        btnTirarDado = findViewById(R.id.btnTirarDado)
        btnMenu = findViewById(R.id.btnMenu)
        btnMapa = findViewById(R.id.btnMapa)
        btnverPersonaje = findViewById(R.id.btnPerfil)
        btnMochila = findViewById(R.id.btnMochila)


        textViewExp.text = personaje?.getExperiencia().toString()
        //textViewMonedas.text =
        textViewNombre.text = personaje?.getNombre()


        btnTirarDado.setOnClickListener {

            var dado = Random.nextInt(1,4)

            when (dado){
                1-> {btnTirarDado.setImageResource(R.drawable.dado1)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, ObjetoActivity::class.java)
                    startActivity(intent)}
                2-> {btnTirarDado.setImageResource(R.drawable.dado2)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, CiudadActivity::class.java)
                    startActivity(intent)}
                3-> {btnTirarDado.setImageResource(R.drawable.dado3)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MercaderActivity::class.java)
                    startActivity(intent)}
                4-> {btnTirarDado.setImageResource(R.drawable.dado4)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, EnemigoActivity::class.java)
                    startActivity(intent)}
                5-> {btnTirarDado.setImageResource(R.drawable.dado5)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MenuOpcionesActivity::class.java)
                    startActivity(intent)}
                6-> {btnTirarDado.setImageResource(R.drawable.dado6)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MenuOpcionesActivity::class.java)
                    startActivity(intent)}
                else -> Log.d(TAG, "Error al cambiar de Actividad")
            }
        }

        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuOpcionesActivity::class.java)
            startActivity(intent)
        }
        btnMapa.setOnClickListener {
            //val intent = Intent(this, MapaActivity::class.java)
            //startActivity(intent)
        }
        btnverPersonaje.setOnClickListener {
            val intent = Intent(this, VerPersonajeActivity::class.java)
            startActivity(intent)
        }
        btnMochila.setOnClickListener {
            val intent = Intent(this, MochilaActivity::class.java)
            startActivity(intent)
        }

    }
}