package com.example.proyectopmdm.eventos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.proyectopmdm.Articulo
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.DatabaseHelper
import com.example.proyectopmdm.GlobalVariables
import com.example.proyectopmdm.R
import kotlin.random.Random


class ObjetoActivity : AppCompatActivity() {

    private lateinit var nombre1: TextView
    private lateinit var tipoArticulo: TextView
    private lateinit var imagen: ImageView
    private lateinit var peso1: TextView
    private lateinit var precio: TextView
    private lateinit var aumentoAtaque: TextView
    private lateinit var aumentoDefensa: TextView
    private lateinit var aumentoVida: TextView
    private lateinit var btnRecoger: Button
    private lateinit var btnContinuar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        val personaje = GlobalVariables.personaje

        //TextView's
        nombre1 = findViewById(R.id.textViewNombre)
        tipoArticulo = findViewById(R.id.textViewTipoArticulo)
        peso1 = findViewById(R.id.textViewPeso)
        precio = findViewById(R.id.textViewPrecio)
        imagen = findViewById(R.id.imageViewEnemigo)
        aumentoAtaque = findViewById(R.id.textViewAumentoAtaque)
        aumentoDefensa = findViewById(R.id.textViewAumentoDefensa)
        aumentoVida = findViewById(R.id.textViewAumentoVida)

        //Botones
        btnRecoger = findViewById(R.id.btnRecoger)
        btnContinuar = findViewById(R.id.btnContinuar)

        val dbHelper = DatabaseHelper(this)

        //Crear articulo aleatorio
        val nombre = Articulo.Nombre.values()[Random.nextInt(Articulo.Nombre.values().size)]
        val peso = Random.nextInt(1, 5)
        val articulo = Articulo(0,nombre,peso)

        //Asignar atributos del articulo a los textView
        nombre1.text = articulo.getNombre().toString()
        tipoArticulo.text = articulo.getTipoArticulo().toString()

        imagen.setImageResource(resources.getIdentifier(articulo.getImagen(), "drawable", packageName))

        peso1.text = articulo.getPeso().toString()
        precio.text = articulo.getPrecio().toString()
        aumentoAtaque.text = articulo.getAumentoAtaque().toString()
        aumentoDefensa.text = articulo.getAumentoDefensa().toString()
        aumentoVida.text = articulo.getAumentoVida().toString()

        //Boton recoger articulo
        btnRecoger.setOnClickListener {
            dbHelper.insertarArticulo(articulo, personaje!!.getId())
            Toast.makeText(this, "Articulo añadido!", Toast.LENGTH_SHORT).show()
            Thread.sleep(1000)
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        //Boton volver al DadoActivity
        btnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}

