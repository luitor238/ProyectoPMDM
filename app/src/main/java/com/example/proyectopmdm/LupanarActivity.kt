package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupanar)

        btnSi = findViewById(R.id.btnSi)
        btnNo = findViewById(R.id.btnNo)
        recyclerView = findViewById(R.id.recyclerView)


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
            // Obtener la primera vista visible en el RecyclerView
            val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition) as CustomAdapter.ViewHolder?

            // Aplicar animación de caída hacia la derecha al ImageView
            viewHolder?.imageView1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.caida_derecha))
        }

        btnNo.setOnClickListener {
            // Obtener la primera vista visible en el RecyclerView
            val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition) as CustomAdapter.ViewHolder?

            // Aplicar animación de caída hacia la izquierda al ImageView
            viewHolder?.imageView1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.caida_izquierda))
        }




    }

}