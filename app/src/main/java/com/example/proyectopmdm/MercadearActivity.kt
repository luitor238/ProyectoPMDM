package com.example.proyectopmdm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MercadearActivity : AppCompatActivity(){
    private lateinit var imagen: ImageView
    private lateinit var btnContinuar: Button
    private lateinit var btnComerciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mercader)


        btnContinuar = findViewById(R.id.btnContinuar)
        btnComerciar = findViewById(R.id.btnComerciar)


        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}