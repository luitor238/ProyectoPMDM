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
                imagen.setImageResource(R.drawable.dado1)
            }
            2-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            3-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            4-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            5-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            6-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            7-> {
                imagen.setImageResource(R.drawable.dado1)
            }
            8-> {
                imagen.setImageResource(R.drawable.dado1)
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