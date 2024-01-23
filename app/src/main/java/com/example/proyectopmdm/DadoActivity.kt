package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.proyectopmdm.eventos.CiudadActivity
import com.example.proyectopmdm.eventos.EnemigoActivity
import com.example.proyectopmdm.eventos.MercaderActivity
import com.example.proyectopmdm.eventos.ObjetoActivity
import kotlin.random.Random


class DadoActivity : AppCompatActivity() {


    private lateinit var imagen: ImageView
    private lateinit var btnTirarDado: Button
    private lateinit var btnMenu: Button


    private val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dado)

        imagen = findViewById(R.id.imageView)
        imagen.setImageResource(R.drawable.dadoblanco)

        btnTirarDado = findViewById(R.id.btnTirarDado)
        btnMenu = findViewById(R.id.btnMenu)

        btnTirarDado.setOnClickListener {

            var dado = Random.nextInt(1,4)


            when (dado){
                1-> {imagen.setImageResource(R.drawable.dado1jpg)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, ObjetoActivity::class.java)
                    startActivity(intent)}
                2-> {imagen.setImageResource(R.drawable.dado2jpg)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, CiudadActivity::class.java)
                    startActivity(intent)}
                3-> {imagen.setImageResource(R.drawable.dado3jpg)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MercaderActivity::class.java)
                    startActivity(intent)}
                4-> {imagen.setImageResource(R.drawable.dado4jpg)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, EnemigoActivity::class.java)
                    startActivity(intent)}
                5-> {imagen.setImageResource(R.drawable.dado5jpg)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MenuOpcionesActivity::class.java)
                    startActivity(intent)}
                6-> {imagen.setImageResource(R.drawable.dado6jpg)
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

    }
}