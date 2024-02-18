package com.example.proyectopmdm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.GlobalVariables.personaje

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val messages: MutableList<Pair<String, String>> = mutableListOf()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val (sender, message) = messages[position]
        holder.senderTextView.text = sender
        holder.messageTextView.text = message
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun sendMessage(message: String) {
        // Añadir el mensaje enviado por el usuario al chat
        addMessage("Tú", message)

        // Enviar el mensaje al personaje y obtener la respuesta
        val response = personaje!!.comunicacion(message)

        // Añadir la respuesta del personaje al chat
        addMessage(personaje!!.getNombre(), response)
    }

    fun addMessage(sender: String, message: String) {
        messages.add(Pair(sender, message))
        notifyItemInserted(messages.size - 1)
    }
}
