package com.example.proyectopmdm.eventos

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.proyectopmdm.Articulo
import com.example.proyectopmdm.DadoActivity
import com.example.proyectopmdm.DatabaseHelper
import com.example.proyectopmdm.GlobalVariables
import com.example.proyectopmdm.MenuOpcionesActivity
import com.example.proyectopmdm.Personaje
import com.example.proyectopmdm.R
import com.example.proyectopmdm.VerArticuloActivity
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
    private lateinit var seleccionado: Articulo
    private lateinit var articulos: ArrayList<Articulo>
    private lateinit var btnVer: Button
    private var cont: Int= 0
    private val TAG = "LoginActivity"

    @SuppressLint("ResourceAsColor", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        val dbHelper = DatabaseHelper(this)


        imagenes = Array(10) { index -> val imageButton = findViewById<ImageButton>(
            resources.getIdentifier("imagen${index + 1}", "id", packageName))
            Pair(imageButton, 0)
        }

        btnVolver = Array(4) { index -> findViewById<ImageButton>(resources.getIdentifier("btnVolver${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de los volver")
        views = Array(10) { index -> findViewById<View>(resources.getIdentifier("my_view${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de las Views")
        textos = Array(3) { index -> findViewById<TextView>(resources.getIdentifier("texto${index + 1}", "id", packageName)) }
        btnComprar = Array(2) { index -> findViewById<Button>(resources.getIdentifier("btnComprar${index + 1}", "id", packageName)) }
        btnVender = Array(2) { index -> findViewById<Button>(resources.getIdentifier("btnVender${index + 1}", "id", packageName)) }
        vistas = Array(4) { index -> findViewById<View>(resources.getIdentifier("vista${index + 1}", "id", packageName)) }
        Log.d(TAG, "Inicializacion de las Vistas")
        btnComerciar = findViewById(R.id.btnComerciar)


        btnVer = findViewById(R.id.btnVer)



        val personaje = GlobalVariables.personaje

        articulos = dbHelper.getArticulo().filter { it.getIdUser() == personaje?.getId() }.toMutableList() as ArrayList<Articulo>

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)


        Log.d(TAG, "Inicializacion de los elementos")

        textos[0].text = GlobalVariables.personaje?.getMonedero().toString()
        textos[1].text = "0"


        btnComerciar.setOnClickListener {
            Log.d(TAG, "Boton comerciar")
            // Cambiar la visibilidad de las vistas
                if (vistas[0].visibility == View.VISIBLE) {
                    vistas[0].visibility = View.GONE
                    vistas[1].visibility = View.VISIBLE
                }
        }
        btnVolver[0].setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
        btnVolver[1].setOnClickListener {
            if (vistas[1].visibility == View.VISIBLE) {
                vistas[1].visibility = View.GONE
                vistas[0].visibility = View.VISIBLE
            }
        }
        btnVolver[2].setOnClickListener {
            if (vistas[2].visibility == View.VISIBLE) {
                vistas[2].visibility = View.GONE
                vistas[1].visibility = View.VISIBLE
            }
        }
        btnVolver[3].setOnClickListener {
            if (vistas[3].visibility == View.VISIBLE) {
                vistas[3].visibility = View.GONE
                vistas[1].visibility = View.VISIBLE
            }
        }

        btnComprar[0].setOnClickListener {
            // Cambiar la visibilidad de las vistas
            if (vistas[1].visibility == View.VISIBLE) {
                vistas[1].visibility = View.GONE
                vistas[2].visibility = View.VISIBLE
            }
        }

        btnVender[0].setOnClickListener {
            if (articulos.isEmpty()) {
                Toast.makeText(this, "Mochila Vacia!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "No hy Mochila")
            } else {
                // Agregar los articulos al scroll
                for (articulo in articulos) {
                    agregarArticulo(articulo)
                    Log.d(TAG, "Hay mochila")
                }
            }
            // Cambiar la visibilidad de las vistas
            if (vistas[1].visibility == View.VISIBLE) {
                vistas[1].visibility = View.GONE
                vistas[3].visibility = View.VISIBLE
            }
        }

        btnVender[1].setOnClickListener {
            Log.d(TAG, "Mochilon")
            dbHelper.eliminarRegistro(seleccionado.id)
            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()+seleccionado.getPrecio())
            linearLayout.removeAllViews()
            articulos = DatabaseHelper(this).getArticulo() as ArrayList<Articulo>
            for (articulo in articulos) {
                if (articulo.getIdUser() == GlobalVariables.personaje?.getId()) {
                    agregarArticulo(articulo)
                }
            }
        }

        btnVer.setOnClickListener {
            val intent = Intent(this, VerArticuloActivity::class.java)
            intent.putExtra("articulo", seleccionado)
            startActivity(intent)
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
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }

                        ContextCompat.getDrawable(this, R.drawable.articulo_pocion2)?.constantState -> {
                            val nombre = Articulo.Nombre.POCION
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_garras2)?.constantState -> {
                            val nombre = Articulo.Nombre.GARRAS
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.navaja)?.constantState -> {
                            val nombre = Articulo.Nombre.DAGA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_escudo)?.constantState -> {
                            val nombre = Articulo.Nombre.ESCUDO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_espada)?.constantState -> {
                            val nombre = Articulo.Nombre.ESPADA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_armadura)?.constantState ->{
                            val nombre = Articulo.Nombre.ARMADURA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_martillo)?.constantState ->{
                            val nombre = Articulo.Nombre.MARTILLO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_baston)?.constantState ->{
                            val nombre = Articulo.Nombre.BASTON
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())


                        }
                    }
                    Toast.makeText(this, "Articulos añadidos!", Toast.LENGTH_SHORT).show()

                }
            }
        }




        imagenes.forEachIndexed { index, (imageButton, value) ->
            imageButton.setOnClickListener {
                if (imagenes[index].second == 0) {

                    when (imagenes[index].first.drawable.constantState) {
                        ContextCompat.getDrawable(this, R.drawable.articulo_ira2)?.constantState -> {

                            Log.d(TAG, "IRA")
                            val nombre = Articulo.Nombre.IRA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }

                        ContextCompat.getDrawable(this, R.drawable.articulo_pocion2)?.constantState -> {
                            val nombre = Articulo.Nombre.POCION
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_garras2)?.constantState -> {
                            val nombre = Articulo.Nombre.GARRAS
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.navaja)?.constantState -> {
                            val nombre = Articulo.Nombre.DAGA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_escudo)?.constantState -> {
                            val nombre = Articulo.Nombre.ESCUDO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_espada)?.constantState -> {
                            val nombre = Articulo.Nombre.ESPADA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_armadura)?.constantState ->{
                            val nombre = Articulo.Nombre.ARMADURA
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_martillo)?.constantState ->{
                            val nombre = Articulo.Nombre.MARTILLO
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                        ContextCompat.getDrawable(this, R.drawable.articulo_baston)?.constantState ->{
                            val nombre = Articulo.Nombre.BASTON
                            val peso = Random.nextInt(1, 5)
                            val articulo = Articulo(0,nombre,peso)
                            dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                            GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                            val nuevoValor = imagenes[index].second + 1
                            imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                            views[index].setBackgroundColor( getResources().getColor(R.color.primaryColor));
                            cont = cont+1
                            textos[2].text = "${cont}"
                            Log.d(TAG, "Valor Sumado")
                            textos[1].text =   ((textos[1].text.toString().toInt())+(articulo.getPrecio())).toString()


                        }
                    }



                }else {
                    if (imagenes[index].second == 1) {

                        when (imagenes[index].first.drawable.constantState) {
                            ContextCompat.getDrawable(this, R.drawable.articulo_ira2)?.constantState -> {

                                Log.d(TAG, "IRA")
                                val nombre = Articulo.Nombre.IRA
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }

                            ContextCompat.getDrawable(this, R.drawable.articulo_pocion2)?.constantState -> {
                                val nombre = Articulo.Nombre.POCION
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_garras2)?.constantState -> {
                                val nombre = Articulo.Nombre.GARRAS
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.navaja)?.constantState -> {
                                val nombre = Articulo.Nombre.DAGA
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_escudo)?.constantState -> {
                                val nombre = Articulo.Nombre.ESCUDO
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_espada)?.constantState -> {
                                val nombre = Articulo.Nombre.ESPADA
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_armadura)?.constantState ->{
                                val nombre = Articulo.Nombre.ARMADURA
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_martillo)?.constantState ->{
                                val nombre = Articulo.Nombre.MARTILLO
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                            ContextCompat.getDrawable(this, R.drawable.articulo_baston)?.constantState ->{
                                val nombre = Articulo.Nombre.BASTON
                                val peso = Random.nextInt(1, 5)
                                val articulo = Articulo(0,nombre,peso)
                                dbHelper.insertarArticulo(articulo, GlobalVariables.personaje!!.getId())
                                GlobalVariables.personaje!!.setMonedero(GlobalVariables.personaje!!.getMonedero()-articulo.getPrecio())
                                val nuevoValor = imagenes[index].second - 1
                                imagenes[index] = Pair(imagenes[index].first, nuevoValor)
                                views[index].setBackgroundColor( getResources().getColor(R.color.white));
                                cont = cont-1
                                textos[2].text = "${cont}"
                                Log.d(TAG, "Valor Sumado")
                                textos[1].text =   ((textos[1].text.toString().toInt())-(articulo.getPrecio())).toString()


                            }
                        }
                    }
                }

                // Ahora muestra el nuevo valor actualizado
                Log.d(TAG, "ImageButton ${index + 1}: $imageButton")
                Log.d(TAG, "Valor asociado: ${imagenes[index].second}")
            }
        }


    }
    private fun agregarArticulo(articulo: Articulo) {
        val nuevoArticulo = ImageButton(this)
        val alturaEnPx = resources.getDimensionPixelSize(R.dimen.tu_altura_imagebutton)

        // Atributos del imageButton
        nuevoArticulo.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, alturaEnPx)
        nuevoArticulo.setImageResource(resources.getIdentifier(articulo.getImagen(), "drawable", packageName))
        nuevoArticulo.scaleType = ImageView.ScaleType.FIT_CENTER
        nuevoArticulo.setBackgroundColor(Color.TRANSPARENT)

        // Funcion del boton articulo
        nuevoArticulo.setOnClickListener {
            seleccionado = articulo
            btnVer.visibility = View.VISIBLE
            btnVender[1].visibility = View.VISIBLE

        }

        // Añadir al linearLayout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(nuevoArticulo)
    }
}