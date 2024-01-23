package com.example.proyectopmdm.eventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.R

class CiudadActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var btnContinuar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)

        btnContinuar = findViewById(R.id.btnContinuar)

        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}