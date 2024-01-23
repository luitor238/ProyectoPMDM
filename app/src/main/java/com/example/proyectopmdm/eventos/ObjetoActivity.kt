package com.example.proyectopmdm.eventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.text.parseAsHtml
import com.example.proyectopmdm.Articulo
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


        val nombre = Articulo.Nombre.values()[Random.nextInt(Articulo.Nombre.values().size)]
        val peso = Random.nextInt(1, 5)

        var articulo = Articulo(nombre,peso)

        imagen.setImageResource(articulo.getImagen().toInt())



        //BOTONES
        btnRecoger.setOnClickListener {
            recogerArticulo(articulo)
            Toast.makeText(this, "Articulo añadido!", Toast.LENGTH_SHORT).show()
            Thread.sleep(2000)
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }

    fun recogerArticulo(articulo: Articulo){
        //Añadir funcion recoger--------------------------------------------------------------------
    }
}