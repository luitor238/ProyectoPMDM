package com.example.proyectopmdm.eventos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.Monstruo
import com.example.proyectopmdm.PeleaActivity
import com.example.proyectopmdm.R
import kotlin.random.Random

class EnemigoActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var Nombre: TextView
    private lateinit var Nivel: TextView
    private lateinit var btnHuir: Button
    private lateinit var btnLuchar: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)

        val monstruo = Monstruo("Pedro",Random.nextInt(1,9))

        imagen = findViewById(R.id.imageViewEnemigo)
        Nombre = findViewById(R.id.textViewNombre)
        Nivel = findViewById(R.id.textViewNivel)
        btnHuir = findViewById(R.id.btnHuir)
        btnLuchar = findViewById(R.id.btnLuchar)

        // Acceder al array de monstruos y elegir uno aleatorio
        val drawablesArray = resources.obtainTypedArray(R.array.monstruos)
        val drawableId = drawablesArray.getResourceId(Random.nextInt(0, drawablesArray.length()), -1)

        imagen.setImageResource(drawableId)
        Nombre.text = monstruo.getNombre()
        Nivel.text = monstruo.getNivel().toString()

        drawablesArray.recycle()

        btnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnLuchar.setOnClickListener {
            val intent = Intent(this, PeleaActivity::class.java)
            intent.putExtra("monstruo",monstruo)
            intent.putExtra("imagen",drawableId)
            startActivity(intent)
        }
    }
}