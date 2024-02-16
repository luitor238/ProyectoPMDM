package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectopmdm.eventos.CiudadActivity
import com.example.proyectopmdm.eventos.EnemigoActivity
import com.example.proyectopmdm.eventos.MercaderActivity
import com.example.proyectopmdm.eventos.ObjetoActivity

private lateinit var btnLupanar: Button
private lateinit var btnenemigo: Button
private lateinit var btnciudad: Button
private lateinit var btnmercader: Button
private lateinit var btnobjeto: Button
private lateinit var btnMochila: Button

class MenuOpcionesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        // CREACION DE LA VISTA Y ASIGNACION DEL LAYOUT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        btnLupanar = findViewById(R.id.btnLupanar)
        btnenemigo = findViewById(R.id.btnEnemigo)
        btnciudad = findViewById(R.id.btnCiudad)
        btnmercader = findViewById(R.id.btnMercader)
        btnobjeto = findViewById(R.id.btnObjeto)
        btnMochila = findViewById(R.id.btnMochila)

        btnLupanar.setOnClickListener{
            val intent = Intent(this,LupanarActivity::class.java)
            startActivity(intent)
        }
        btnenemigo.setOnClickListener{
            val intent = Intent(this,EnemigoActivity::class.java)
            startActivity(intent)
        }
        btnciudad.setOnClickListener{
            val intent = Intent(this,CiudadActivity::class.java)
            startActivity(intent)
        }
        btnmercader.setOnClickListener{
            val intent = Intent(this,MercaderActivity::class.java)
            startActivity(intent)
        }
        btnobjeto.setOnClickListener{
            val intent = Intent(this,ObjetoActivity::class.java)
            startActivity(intent)
        }
        btnMochila.setOnClickListener{
            val intent = Intent(this,MochilaActivity::class.java)
            startActivity(intent)
        }

    }
}