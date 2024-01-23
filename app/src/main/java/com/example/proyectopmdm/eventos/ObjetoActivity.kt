package com.example.proyectopmdm.eventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.R
import kotlin.random.Random

class ObjetoActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var btnRecoger: Button
    private lateinit var btnContinuar: Button

    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        btnRecoger = findViewById(R.id.btnRecoger)
        btnContinuar = findViewById(R.id.btnContinuar)

        //BASTON, ESPADA, DAGA, MARTILLO, GARRAS, POCION, IRA, ESCUDO, ARMADURA
        val objeto = Random.nextInt(1,9)

        when (objeto){
            1-> {
                imagen.setImageResource(R.drawable.baston)
            }
            2-> {
                imagen.setImageResource(R.drawable.espada)
            }
            3-> {
                imagen.setImageResource(R.drawable.daga)
            }
            4-> {
                imagen.setImageResource(R.drawable.martillo)
            }
            5-> {
                imagen.setImageResource(R.drawable.garras)
            }
            6-> {
                imagen.setImageResource(R.drawable.pocion)
            }
            7-> {
                imagen.setImageResource(R.drawable.ira)
            }
            8-> {
                imagen.setImageResource(R.drawable.escudo)
            }
            9-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            else -> Log.d(TAG, "Error al cambiar de Actividad")
        }

        btnRecoger.setOnClickListener {
            //Añadir funcion recoger----------------------------------------------------------------
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}