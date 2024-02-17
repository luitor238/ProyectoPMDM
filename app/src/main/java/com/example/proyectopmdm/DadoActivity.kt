package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.proyectopmdm.eventos.CiudadActivity
import com.example.proyectopmdm.eventos.EnemigoActivity
import com.example.proyectopmdm.eventos.MercaderActivity
import com.example.proyectopmdm.eventos.ObjetoActivity
import kotlin.random.Random


class DadoActivity : AppCompatActivity() {

    private lateinit var textViewExp: TextView
    private lateinit var textViewMonedas: TextView
    private lateinit var textViewNombre: TextView
    private lateinit var btnTirarDado: ImageButton
    private lateinit var btnMenu: Button
    private lateinit var btnMapa: ImageButton
    private lateinit var btnComunicacion: ImageButton
    private lateinit var btnverPersonaje: ImageButton
    private lateinit var btnMochila: ImageButton


    private val TAG = "LoginActivity"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dado)


        textViewExp = findViewById(R.id.textViewExperiencia)
        textViewMonedas = findViewById(R.id.textViewMonedas)
        textViewNombre = findViewById(R.id.textViewNombre)
        btnTirarDado = findViewById(R.id.btnTirarDado)
        btnMenu = findViewById(R.id.btnMenu)
        btnComunicacion = findViewById(R.id.btnComunicacion)
        btnMapa = findViewById(R.id.btnMapa)
        btnverPersonaje = findViewById(R.id.btnPerfil)
        btnMochila = findViewById(R.id.btnMochila)


        textViewExp.text = GlobalVariables.personaje?.getExperiencia().toString()
        textViewMonedas.text = GlobalVariables.personaje?.getMonedero().toString()
        textViewNombre.text = GlobalVariables.personaje?.getNombre()


        btnTirarDado.setOnClickListener {

            var dado = Random.nextInt(1, 4)

            when (dado) {
                1 -> {
                    btnTirarDado.setImageResource(R.drawable.dado1)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, ObjetoActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    btnTirarDado.setImageResource(R.drawable.dado2)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, CiudadActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    btnTirarDado.setImageResource(R.drawable.dado3)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MercaderActivity::class.java)
                    startActivity(intent)
                }

                4 -> {
                    btnTirarDado.setImageResource(R.drawable.dado4)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, EnemigoActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    btnTirarDado.setImageResource(R.drawable.dado5)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MenuOpcionesActivity::class.java)
                    startActivity(intent)
                }

                6 -> {
                    btnTirarDado.setImageResource(R.drawable.dado6)
                    // Esperar 2 segundos
                    Thread.sleep(2000)
                    val intent = Intent(this, MenuOpcionesActivity::class.java)
                    startActivity(intent)
                }

                else -> Log.d(TAG, "Error al cambiar de Actividad")
            }
        }

        if (GlobalVariables.personaje != null) {
            btnverPersonaje.setImageResource(
                resources.getIdentifier(
                    GlobalVariables.personaje!!.getImagen(),
                    "drawable",
                    packageName
                )
            )
        }

        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuOpcionesActivity::class.java)
            startActivity(intent)
        }
        btnComunicacion.setOnClickListener {
            //val intent = Intent(this, ComunicacionActivity::class.java)
            //startActivity(intent)
        }
        btnMapa.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            startActivity(intent)
        }
        btnverPersonaje.setOnClickListener {
            val intent = Intent(this, VerPersonajeActivity::class.java)
            startActivity(intent)
        }
        btnMochila.setOnClickListener {
            val intent = Intent(this, MochilaActivity::class.java)
            startActivity(intent)
        }

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            showExitConfirmationDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onUserLeaveHint() {
        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm_exit, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = builder.create()

        dialogView.findViewById<Button>(R.id.btnGuardar)?.setOnClickListener {
            saveToDatabase()
            alertDialog.dismiss()
            finish()
        }

        dialogView.findViewById<Button>(R.id.btnNoGuardar)?.setOnClickListener {
            alertDialog.dismiss()
            finish()
        }

        alertDialog.show()
    }

    private fun saveToDatabase() {
        val dbHelper = DatabaseHelper(this)
        dbHelper.insertarPersonaje(GlobalVariables.personaje!!)
    }
}
