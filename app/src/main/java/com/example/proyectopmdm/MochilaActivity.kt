package com.example.proyectopmdm

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mochila)

        // Obtener la instancia única de variablesGlobales
        val variablesGlobales = variablesGlobales.getInstance()

        // Acceder a la variable global globalPersonaje
        val personaje = variablesGlobales.globalPersonaje
        val dbHelper = DatabaseHelper(this)

        btnVolver = findViewById(R.id.btnVolver)
        btnBorrar = findViewById(R.id.btnBorrar)
        btnVer = findViewById(R.id.btnVer)
        btnUsar = findViewById(R.id.btnUsar)

        articulos = dbHelper.getArticulo()

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)


        if(!articulos.isEmpty()){
            //Agregar los articulos al scroll
            for(i in 0..(articulos.size-1)){
                agregarArticulo(articulos[i])
            }
        }
        else{
            Toast.makeText(this,"Mochila Vacia!",Toast.LENGTH_SHORT).show()
        }

        //Boton volver al menu
        btnVolver.setOnClickListener(){
            val intent = Intent(this,DadoActivity::class.java)
            startActivity(intent)
        }

        //Borrar articulo
        btnBorrar.setOnClickListener(){
            articulos = dbHelper.getArticulo()

            //Elimina registro en BBDD
            dbHelper.eliminarRegistro(seleccionado.id)

            //Ocultamos botones
            btnBorrar.visibility= View.INVISIBLE
            btnVer.visibility= View.INVISIBLE
            btnUsar.visibility= View.INVISIBLE

            //Recargamos articulos en la mochila
            var i = 0
            while (i < linearLayout.childCount) {
                val view = linearLayout.getChildAt(i)

                // Verifica si el hijo es un ImageButton y elimínalo
                if (view is ImageButton) {
                    linearLayout.removeViewAt(i)
                } else {
                    i++
                }
            }

            if(!articulos.isEmpty()){
                //Agregar los articulos al scroll
                for(i in 0..(articulos.size-1)){
                    agregarArticulo(articulos[i])
                }
            }
            else{
                Toast.makeText(this,"Mochila Vacia!",Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this,MochilaActivity::class.java)
            startActivity(intent)
        }

        btnVer.setOnClickListener(){
            val intent = Intent(this,VerArticuloActivity::class.java)
            intent.putExtra("articulo",seleccionado)
            startActivity(intent)
        }

        btnUsar.setOnClickListener(){
            personaje?.usarObjeto(seleccionado, this)
            dbHelper.eliminarRegistro(seleccionado.id)

            //Ocultamos botones
            btnBorrar.visibility= View.INVISIBLE
            btnVer.visibility= View.INVISIBLE
            btnUsar.visibility= View.INVISIBLE

            //Recargamos articulos en la mochila
            var i = 0
            while (i < linearLayout.childCount) {
                val view = linearLayout.getChildAt(i)

                // Verifica si el hijo es un ImageButton y elimínalo
                if (view is ImageButton) {
                    linearLayout.removeViewAt(i)
                } else {
                    i++
                }
            }

            val intent = Intent(this,MochilaActivity::class.java)
            startActivity(intent)
        }

        dbHelper.close()

    }


    private fun agregarArticulo(articulo: Articulo) {
        val nuevoArticulo = ImageButton(this)
        val alturaEnPx = resources.getDimensionPixelSize(
            R.dimen.tu_altura_imagebutton
        )

        //ATRIBUTOS del imageButton
        nuevoArticulo.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            alturaEnPx
        )
        nuevoArticulo.setImageResource(resources.getIdentifier(articulo.getImagen(), "drawable", packageName))
        nuevoArticulo.scaleType = ImageView.ScaleType.FIT_CENTER
        nuevoArticulo.setBackgroundColor(Color.TRANSPARENT)


        //Funcion del boton articulo
        nuevoArticulo.setOnClickListener {
            seleccionado = articulo
            if(btnBorrar.visibility==View.INVISIBLE || btnVer.visibility==View.INVISIBLE || btnUsar.visibility==View.INVISIBLE) {
                btnBorrar.visibility= View.VISIBLE
                btnVer.visibility= View.VISIBLE
                btnUsar.visibility= View.VISIBLE
            }
            nuevoArticulo.tag = articulo.id//-----------------------------
        }

        //Añadir al linearLayout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(nuevoArticulo)
    }
}