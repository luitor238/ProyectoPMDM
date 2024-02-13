package com.example.proyectopmdm.eventos

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.proyectopmdm.Articulo
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.DatabaseHelper
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.R
import com.example.proyectopmdm.VerPersonajeActivity
import kotlin.random.Random

class MercaderActivity : AppCompatActivity() {
    private lateinit var imagenes: Array<Pair<ImageButton, Int>>
    private lateinit var views: Array<View>
    private lateinit var btnVolver: Array<ImageButton>
    private lateinit var btnComprar: Array<Button>
    private lateinit var btnVender: Array<Button>
    private lateinit var textos: Array<TextView>
    private lateinit var btnComerciar: Button
    private lateinit var vistas:  Array<View>
    private var cont: Int= 0
    val variablesGlobales = com.example.proyectopmdm.variablesGlobales.getInstance()
    val personaje = variablesGlobales.globalPersonaje
    private val TAG = "LoginActivity"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        imagenes = Array(10) { index -> val imageButton = findViewById<ImageButton>(
            resources.getIdentifier("imagen${index + 1}", "id", packageName))
            Pair(imageButton, 0)
        }
        btnVolver = Array(3) { index -> findViewById<ImageButton>(resources.getIdentifier("btnVolver${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de los volver")
        views = Array(10) { index -> findViewById<View>(resources.getIdentifier("my_view${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de las Views")
        textos = Array(3) { index -> findViewById<TextView>(resources.getIdentifier("texto${index + 1}", "id", packageName)) }
        btnComprar = Array(2) { index -> findViewById<Button>(resources.getIdentifier("btnComprar${index + 1}", "id", packageName)) }
        btnVender = Array(1) { index -> findViewById<Button>(resources.getIdentifier("btnVender${index + 1}", "id", packageName)) }
        vistas = Array(3) { index -> findViewById<View>(resources.getIdentifier("vista${index + 1}", "id", packageName)) }
        btnComerciar = findViewById(R.id.btnComerciar)
        val dbHelper = DatabaseHelper(this)
        Log.d(TAG, "Inicializacion de los elementos")

        textos[0].text = personaje?.getDinero().toString()



        btnComerciar.setOnClickListener {
            Log.d(TAG, "Boton comerciar")
            // Cambiar la visibilidad de las vistas
                if (vistas[0].visibility == View.VISIBLE) {
                    vistas[0].visibility = View.GONE
                    vistas[1].visibility = View.VISIBLE
                }
        }
        btnVolver[0].setOnClickListener {
            val intent = Intent(this, MenuOpcionesActivity::class.java)

            startActivity(intent)
        }

        btnComprar[0].setOnClickListener {
            // Cambiar la visibilidad de las vistas
            if (vistas[1].visibility == View.VISIBLE) {
                vistas[1].visibility = View.GONE
                vistas[2].visibility = View.VISIBLE
            }
        }


        btnComprar[1].setOnClickListener {
            imagenes.forEachIndexed { index, (imageButton, value) ->
                if (imagenes[index].second == 1) {



                    when (imagenes[index].first.drawable.constantState) {
                        ContextCompat.getDrawable(this, R.drawable.articulo_ira2)?.constantState -> {
                            Log.d(TAG, "IRA")
                            val nombre = Articulo.Nombre.IRA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }

                        ContextCompat.getDrawable(this, R.drawable.articulo_pocion2)?.constantState -> {
                            val nombre = Articulo.Nombre.POCION
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_garras2)?.constantState -> {
                            val nombre = Articulo.Nombre.GARRAS
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.navaja)?.constantState -> {
                            val nombre = Articulo.Nombre.DAGA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_escudo)?.constantState -> {
                            val nombre = Articulo.Nombre.ESCUDO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_espada)?.constantState -> {
                            val nombre = Articulo.Nombre.ESPADA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_armadura)?.constantState ->{
                            val nombre = Articulo.Nombre.ARMADURA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_martillo)?.constantState ->{
                            val nombre = Articulo.Nombre.MARTILLO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_baston)?.constantState ->{
                            val nombre = Articulo.Nombre.BASTON
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo)
                        }
                    }
                    Toast.makeText(this, "Articulos añadidos!", Toast.LENGTH_SHORT).show()

                }
            }
        }




        imagenes.forEachIndexed { index, (imageButton, value) ->
            // Asignar OnClickListener a cada ImageButton
            imageButton.setOnClickListener {
                if (imagenes[index].second == 0) {
                    val nuevoValor = imagenes[index].second + 1
                    imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                    views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                    cont = cont+1
                    textos[2].text = "${cont}"
                    Log.d(TAG, "Valor Sumado")

                }else {
                    if (imagenes[index].second == 1) {
                        val nuevoValor = imagenes[index].second - 1
                        imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                        views[index].setBackgroundColor(Color.WHITE);
                        cont = cont-1
                        textos[2].text = "${cont}"
                        Log.d(TAG, "Valor Restado")
                    }
                }


                // Ahora muestra el nuevo valor actualizado
                Log.d(TAG, "ImageButton ${index + 1}: $imageButton")
                Log.d(TAG, "Valor asociado: ${imagenes[index].second}") // Utiliza el nuevo valor actualizado
            }
        }


    }
}