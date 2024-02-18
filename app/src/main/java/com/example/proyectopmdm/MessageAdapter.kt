package com.example.proyectopmdm
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.GlobalVariables.personaje
import java.util.concurrent.CopyOnWriteArrayList

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val messages: CopyOnWriteArrayList<Pair<String, String>> = CopyOnWriteArrayList()

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

        // Verificar si personaje es nulo antes de usarlo
        personaje?.let {
            // Enviar el mensaje al personaje y obtener la respuesta
            val response = it.comunicacion(message)

            // Añadir la respuesta del personaje al chat
            addMessage(it.getNombre(), response)
        }
    }

    // Método sincronizado para evitar problemas de seguridad de hilos
    @Synchronized
    fun addMessage(sender: String, message: String) {
        messages.add(Pair(sender, message))
        notifyItemInserted(messages.size - 1)
    }
}
