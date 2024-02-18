package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.proyectopmdm.eventos.CiudadActivity
import com.example.proyectopmdm.eventos.EnemigoActivity
import com.example.proyectopmdm.eventos.MercaderActivity
import com.example.proyectopmdm.eventos.ObjetoActivity
import pl.droidsonroids.gif.GifAnimationMetaData
import pl.droidsonroids.gif.GifImageView
import kotlin.random.Random


class DadoActivity : AppCompatActivity() {

    private lateinit var textViewExp: TextView
    private lateinit var textViewMonedas: TextView
    private lateinit var textViewNombre: TextView
    private lateinit var btnTirarDado: ImageButton
    private lateinit var dadogif: GifImageView
    private lateinit var btnMenu: Button
    private lateinit var btnMapa: ImageButton
    private lateinit var btnComunicacion: ImageButton
    private lateinit var btnverPersonaje: ImageButton
    private lateinit var btnMochila: ImageButton
    private lateinit var btnGuardar: ImageButton


    private val TAG = "LoginActivity"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dado)


        textViewExp = findViewById(R.id.textViewExperiencia)
        textViewMonedas = findViewById(R.id.textViewMonedas)
        textViewNombre = findViewById(R.id.textViewNombre)
        btnTirarDado = findViewById(R.id.btnTirarDado)
        dadogif = findViewById(R.id.dadogif)
        btnMenu = findViewById(R.id.btnMenu)
        btnComunicacion = findViewById(R.id.btnComunicacion)
        btnMapa = findViewById(R.id.btnMapa)
        btnverPersonaje = findViewById(R.id.btnPerfil)
        btnMochila = findViewById(R.id.btnMochila)
        btnGuardar = findViewById(R.id.btnGuardar)


        textViewExp.text = GlobalVariables.personaje?.getExperiencia().toString()
        textViewMonedas.text = GlobalVariables.personaje?.getMonedero().toString()
        textViewNombre.text = GlobalVariables.personaje?.getNombre()


        btnTirarDado.setOnClickListener {

            val shakeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
            dadogif.startAnimation(shakeAnimation)

            // Vibrar el dispositivo durante 2 segundos
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(2000)
            }

            var dado = Random.nextInt(1, 5)

            when (dado) {
                1 -> {
                    val intent = Intent(this, ObjetoActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(this, CiudadActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(this, MercaderActivity::class.java)
                    startActivity(intent)
                }

                4 -> {
                    val intent = Intent(this, EnemigoActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(this, LupanarActivity::class.java)
                    startActivity(intent)
                }

                6 -> {
                    val intent = Intent(this, MapaActivity::class.java)
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
            val intent = Intent(this, ComunicacionActivity::class.java)
            startActivity(intent)
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
        btnGuardar.setOnClickListener{
            val dbHelper = DatabaseHelper(this)
            dbHelper.insertarPersonaje(GlobalVariables.personaje!!)
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
