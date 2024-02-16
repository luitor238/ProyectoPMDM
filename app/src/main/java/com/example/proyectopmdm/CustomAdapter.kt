package com.example.proyectopmdm

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val context: Context, private val images: List<Int>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //INFLAMOS LO QUE SERA EL ELEMENTO DEL RECYCLEVIEW
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.lupana, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = images[position]
        holder.imageView1.setImageResource(currentImage)

        // Aplicar animación de cambio de color al ImageView
        holder.imageView1.setOnClickListener {
            val cambioColorAnimation = AnimationUtils.loadAnimation(context, R.anim.cambio_de_color)
            holder.imageView1.startAnimation(cambioColorAnimation)
        }
    }



    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var linearLayout: LinearLayout = itemView.findViewById(R.id.Linnear01)
        var imageView1: ImageView = itemView.findViewById(R.id.imageView)



    }

}