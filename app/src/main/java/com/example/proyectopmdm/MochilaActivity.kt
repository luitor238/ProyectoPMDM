package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class MochilaActivity : AppCompatActivity() {

    private lateinit var seleccionado: Articulo
    private lateinit var btnVolver: ImageButton
    private lateinit var articulos: ArrayList<Articulo>
    private lateinit var btnBorrar: Button
    private lateinit var btnVer: Button
    private lateinit var btnUsar: Button

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mochila)

        val dbHelper = DatabaseHelper(this)

        val personaje = GlobalVariables.personaje

        btnVolver = findViewById(R.id.btnVolver)
        btnBorrar = findViewById(R.id.btnBorrar)
        btnVer = findViewById(R.id.btnVer)
        btnUsar = findViewById(R.id.btnUsar)

        articulos = dbHelper.getArticulo().filter { it.getIdUser() == personaje?.getId() }.toMutableList() as ArrayList<Articulo>

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        if (articulos.isEmpty()) {
            Toast.makeText(this, "Mochila Vacia!", Toast.LENGTH_SHORT).show()
        } else {
            // Agregar los articulos al scroll
            for (articulo in articulos) {
                agregarArticulo(articulo)
            }
        }

        // Boton volver al menu
        btnVolver.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        // Borrar articulo
        btnBorrar.setOnClickListener {
            dbHelper.eliminarRegistro(seleccionado.id)
            actualizarMochila(linearLayout)
        }

        btnVer.setOnClickListener {
            val intent = Intent(this, VerArticuloActivity::class.java)
            intent.putExtra("articulo", seleccionado)
            startActivity(intent)
        }

        btnUsar.setOnClickListener {
            personaje?.usarObjeto(seleccionado, this)
            if(seleccionado.getTipoArticulo() != Articulo.TipoArticulo.ORO) {
                dbHelper.eliminarRegistro(seleccionado.id)
                actualizarMochila(linearLayout)
            }
            btnBorrar.visibility = View.INVISIBLE
            btnVer.visibility = View.INVISIBLE
            btnUsar.visibility = View.INVISIBLE
        }

        dbHelper.close()
    }

    private fun actualizarMochila(linearLayout: LinearLayout) {
        // Recargamos articulos en la mochila
        linearLayout.removeAllViews()
        articulos = DatabaseHelper(this).getArticulo() as ArrayList<Articulo>
        for (articulo in articulos) {
            if (articulo.getIdUser() == GlobalVariables.personaje?.getId()) {
                agregarArticulo(articulo)
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
            btnBorrar.visibility = View.VISIBLE
            btnVer.visibility = View.VISIBLE
            btnUsar.visibility = View.VISIBLE
        }

        // Añadir al linearLayout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(nuevoArticulo)
    }
}
