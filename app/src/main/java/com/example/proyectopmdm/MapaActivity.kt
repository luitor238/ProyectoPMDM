package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
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

        var texto = ""

        binding.scroll.post {
            // Desplaza el ScrollView hasta el final
            binding.scroll.fullScroll(ScrollView.FOCUS_DOWN)
        }

        binding.level1.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Búsqueda","Fácil")
            quitarImagen()
            binding.level1.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level2.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level2.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Caza","Fácil")
            quitarImagen()
            binding.level2.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level3.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level3.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Asedio","Fácil")
            quitarImagen()
            binding.level3.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level4.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level4.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Destrucción","Fácil")
            quitarImagen()
            binding.level4.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level5.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level5.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Caza","Normal")
            quitarImagen()
            binding.level5.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level6.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level6.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Asedio","Normal")
            quitarImagen()
            binding.level6.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level7.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level7.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Destrucción","Normal")
            quitarImagen()
            binding.level7.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level8.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level8.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Búsqueda","Difícil")
            quitarImagen()
            binding.level8.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level9.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level9.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Caza","Difícil")
            quitarImagen()
            binding.level9.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level10.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level10.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Asedio","Difícil")
            quitarImagen()
            binding.level10.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            binding.level11.visibility = View.VISIBLE
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.level11.setOnClickListener{
            texto = GlobalVariables.personaje!!.realizarMision("Destrucción","Difícil")
            quitarImagen()
            binding.level11.setImageResource(resources.getIdentifier(GlobalVariables.personaje!!.getImagen(),"drawable",packageName))
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar1.setOnClickListener{
            texto = GlobalVariables.personaje!!.entrenar(5)
            Thread.sleep(5000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar2.setOnClickListener{
            texto = GlobalVariables.personaje!!.entrenar(7)
            Thread.sleep(7000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar3.setOnClickListener{
            texto = GlobalVariables.personaje!!.entrenar(10)
            Thread.sleep(10000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.entrenar4.setOnClickListener{
            texto = GlobalVariables.personaje!!.entrenar(20)
            Thread.sleep(20000)
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
        }
        binding.pelea.setOnClickListener{
            val intent = Intent(this, EnemigoActivity::class.java)
            startActivity(intent)
        }
        binding.lupanar.setOnClickListener{
            val intent = Intent(this, LupanarActivity::class.java)
            startActivity(intent)
        }
        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }

    fun quitarImagen() {
        binding.level1.setImageResource(R.drawable.icon_transparent)
        binding.level2.setImageResource(R.drawable.icon_transparent)
        binding.level3.setImageResource(R.drawable.icon_transparent)
        binding.level4.setImageResource(R.drawable.icon_transparent)
        binding.level5.setImageResource(R.drawable.icon_transparent)
        binding.level6.setImageResource(R.drawable.icon_transparent)
        binding.level7.setImageResource(R.drawable.icon_transparent)
        binding.level8.setImageResource(R.drawable.icon_transparent)
        binding.level9.setImageResource(R.drawable.icon_transparent)
        binding.level10.setImageResource(R.drawable.icon_transparent)
        binding.level11.setImageResource(R.drawable.icon_transparent)
    }
}