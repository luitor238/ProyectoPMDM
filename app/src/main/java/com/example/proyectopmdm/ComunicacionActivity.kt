package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.GlobalVariables.personaje
import com.example.proyectopmdm.databinding.ActivityComunicacionBinding


class ComunicacionActivity : AppCompatActivity() {
        private lateinit var binding: ActivityComunicacionBinding

        private lateinit var msg: EditText
        private lateinit var btnEnviar: Button

        private val messageList = mutableListOf<Mensajes>()
        private lateinit var messageAdapter: MessageAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityComunicacionBinding.inflate(layoutInflater)
            setContentView(binding.root)

            msg = binding.msg
            btnEnviar = binding.btnEnviar



            btnEnviar.setOnClickListener {
                if (!msg.text.isNullOrEmpty()) {
                    messageList.add(Mensajes(msg.text.toString(), "Usuario"))
                    messageList.add(Mensajes(personaje!!.comunicacion(msg.text.toString()) , personaje?.getNombre() ?: "Default"))
                    messageAdapter.notifyDataSetChanged()
                    binding.recyclerViewMessages.smoothScrollToPosition(messageList.size - 1)
                }
            }

            // Configurar RecyclerView
            messageAdapter = MessageAdapter(messageList)
            binding.recyclerViewMessages.adapter = messageAdapter
            binding.recyclerViewMessages.layoutManager = LinearLayoutManager(this)

            messageList.add(Mensajes("Hola", "Usuario"))
            messageList.add(Mensajes("Hola, ¿cómo estás?", "ChatBot"))

            messageAdapter.notifyDataSetChanged()
        }
    }

    class MessageAdapter(private val messageList: List<Mensajes>) :
        RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
            return MessageViewHolder(view)
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
            val message = messageList[position]
            holder.bind(message)
        }

        override fun getItemCount(): Int {
            return messageList.size
        }

        class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textContent: TextView = itemView.findViewById(R.id.textMessageContent)
            private val textSender: TextView = itemView.findViewById(R.id.textMessageSender)

            fun bind(mensaje: Mensajes) {
                textContent.text = mensaje.content
                textSender.text = mensaje.sender
            }
        }
    }