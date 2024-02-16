package com.example.proyectopmdm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PeleaActivity : AppCompatActivity() {

    private lateinit var imagen: ImageView
    private lateinit var result: TextView
    private lateinit var btnSeguir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)

        val dbHelper = DatabaseHelper(this)

        var personaje: Personaje? = null
        val personajes = dbHelper.getPersonaje()
        for (e in personajes) {
            if (e.getId() == GlobalVariables.id) {
                personaje = e
                break
            }
        }

        val monstruo = intent.getSerializableExtra("monstruo") as? Monstruo
        val drawableId = intent.getIntExtra("imagen", 0)

        imagen = findViewById(R.id.imageViewMonstruo)
        result = findViewById(R.id.textViewResultado)
        btnSeguir = findViewById(R.id.btnSeguir)

        imagen.setImageResource(drawableId)

        val shakeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        imagen.startAnimation(shakeAnimation)

        // Vibrar el dispositivo durante 3 segundos
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(3000)
        }

        result.text = "Luchando..."
        Thread {
            Thread.sleep(3000)
            val resultado = personaje!!.pelea(monstruo!!)
            runOnUiThread {
                result.text = resultado
                btnSeguir.visibility = View.VISIBLE
                if (resultado == "¡GANASTE!") {
                    imagen.visibility = View.INVISIBLE
                }
            }
        }.start()

        btnSeguir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}
