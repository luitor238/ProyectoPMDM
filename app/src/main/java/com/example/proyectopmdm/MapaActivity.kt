package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectopmdm.databinding.ActivityMapaBinding
import com.example.proyectopmdm.eventos.EnemigoActivity

class MapaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        GlobalVariables.personaje

        binding = ActivityMapaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.level1.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Búsqueda","Fácil")
        }
        binding.level2.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Caza","Fácil")
        }
        binding.level3.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Asedio","Fácil")
        }
        binding.level4.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Destruccón","Fácil")
        }
        binding.level5.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Caza","Normal")
        }
        binding.level6.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Asedio","Normal")
        }
        binding.level7.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Destruccón","Normal")
        }
        binding.level8.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Búsqueda","Difícil")
        }
        binding.level9.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Caza","Difícil")
        }
        binding.level10.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Asedio","Difícil")
        }
        binding.level11.setOnClickListener{
            GlobalVariables.personaje!!.realizarMision("Destruccón","Difícil")
        }
        binding.entrenar1.setOnClickListener{
            val texto = GlobalVariables.personaje!!.entrenar(5)
            Thread.sleep(5000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar2.setOnClickListener{
            val texto = GlobalVariables.personaje!!.entrenar(7)
            Thread.sleep(7000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar3.setOnClickListener{
            val texto = GlobalVariables.personaje!!.entrenar(10)
            Thread.sleep(10000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar4.setOnClickListener{
            val texto = GlobalVariables.personaje!!.entrenar(20)
            Thread.sleep(20000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.pelea.setOnClickListener{
            val intent = Intent(this, EnemigoActivity::class.java)
            startActivity(intent)
        }
        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}