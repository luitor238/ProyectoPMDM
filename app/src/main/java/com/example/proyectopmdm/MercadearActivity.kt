package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MercadearActivity : AppCompatActivity(){
    /*

    private lateinit var imagenes: Array<ImageView>
    private lateinit var btnVolver: Array<Button>
    private lateinit var btnComprar: Array<Button>
    private lateinit var btnVender: Array<Button>
    private lateinit var textos: Array<TextView>
    private lateinit var btnComerciar: Button
    private lateinit var vistas:  Array<View>*/
    private val TAG = "LoginActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Comienzo Actividad")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        /*
        try {

            imagenes = Array(10) { index -> findViewById<ImageView>(resources.getIdentifier("imagen${index + 1}", "id", packageName)) }
            btnVolver = Array(3) { index -> findViewById<Button>(resources.getIdentifier("btnVolver${index + 1}", "id", packageName)) }
            textos = Array(3) { index -> findViewById<TextView>(resources.getIdentifier("texto${index + 1}", "id", packageName)) }
            btnComprar = Array(3) { index -> findViewById<Button>(resources.getIdentifier("btnComprar${index + 1}", "id", packageName)) }
            btnVender = Array(3) { index -> findViewById<Button>(resources.getIdentifier("btnVender${index + 1}", "id", packageName)) }
            vistas = Array(3) { index -> findViewById<View>(resources.getIdentifier("vista${index + 1}", "id", packageName)) }
            btnComerciar = findViewById(R.id.btnComerciar)
            Log.d(TAG, "Asignacion Ids Correcta")
        }catch(e: Exception){
           // Log.d(TAG, "Asignacion Ids Incorrecta")
        }*/





        /*
        btnComerciar.setOnClickListener {
            vistas[0].setOnClickListener {
                // Cambiar la visibilidad de las vistas
                if (vistas[0].visibility == View.VISIBLE) { vistas[0].visibility = View.GONE }
        }*/




    }
}