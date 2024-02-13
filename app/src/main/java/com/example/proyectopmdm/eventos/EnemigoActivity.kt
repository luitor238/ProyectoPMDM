package com.example.proyectopmdm.eventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.Monstruo
import com.example.proyectopmdm.PeleaActivity
import com.example.proyectopmdm.R
import kotlin.random.Random

class EnemigoActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var btnHuir: Button
    private lateinit var btnLuchar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)

        val monstruo = Monstruo("Pedro",Random.nextInt(1,9))

        imagen = findViewById(R.id.imageViewEnemigo)
        btnHuir = findViewById(R.id.btnHuir)
        btnLuchar = findViewById(R.id.btnLuchar)

        btnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnLuchar.setOnClickListener {
            val intent = Intent(this, PeleaActivity::class.java)
            intent.putExtra("monstruo",monstruo)
            startActivity(intent)
        }
    }
}