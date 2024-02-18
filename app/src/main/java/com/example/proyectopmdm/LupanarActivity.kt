package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.eventos.MercaderActivity
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList

class LupanarActivity : AppCompatActivity() {
    private lateinit var btnSi: ImageButton
    private lateinit var btnNo: ImageButton
    private lateinit var adapter: CustomAdapter
    private lateinit var lupanar: LinearLayout
    private lateinit var habitacion: LinearLayout
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private var currentImageIndex = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupanar)

        btnSi = findViewById(R.id.btnSi)
        btnNo = findViewById(R.id.btnNo)
        recyclerView = findViewById(R.id.recyclerView)
        lupanar = findViewById(R.id.lupanar)
        habitacion = findViewById(R.id.habitacion)


        val images: MutableList<Int> = ArrayList()
        images.add(R.drawable.lupana1)
        images.add(R.drawable.lupana2_jfif)
        images.add(R.drawable.lupana3_jfif)
        images.add(R.drawable.lupana4_jfif)

        Log.d(ContentValues.TAG, "Crea una instancia de tu adaptador y configúralo en el RecyclerView principal")
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(this, images)
        recyclerView.setAdapter(adapter)


        btnSi.setOnClickListener {
            val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition) as CustomAdapter.ViewHolder?

            viewHolder?.imageView1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.caida_izquierda))

            val animationDuration = 600 // Duración de la animación en milisegundos

            // Retrasar el desplazamiento del RecyclerView hasta que la animación haya finalizado
            Handler().postDelayed({
                currentImageIndex = if (currentImageIndex > 0) currentImageIndex - 1 else images.size - 1
                recyclerView.smoothScrollToPosition(currentImageIndex)
            }, animationDuration.toLong())

            }


        btnNo.setOnClickListener {
            if (lupanar.visibility == View.VISIBLE) {
                lupanar.visibility = View.GONE
                habitacion.visibility = View.VISIBLE
            }
            GlobalVariables.personaje!!.setSalud( GlobalVariables.personaje!!.getSalud()*2)
            Toast.makeText(this, "Salud Subida!", Toast.LENGTH_SHORT).show()

        }





    }

}
