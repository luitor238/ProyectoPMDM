package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectopmdm.GlobalVariables.personaje
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComunicacionActivity : AppCompatActivity() {

    private lateinit var EditTextInput: EditText
    private lateinit var btnEnviar: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicacion)


        EditTextInput = findViewById(R.id.EditTextInput)
        btnEnviar = findViewById(R.id.btnEnviar)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val instancia = MessageAdapter()

        btnEnviar.setOnClickListener {
            val message = EditTextInput.text.toString().trim()
            if (message.isNotEmpty()) {
                instancia.sendMessage(message)
                EditTextInput.text.clear()
            }
        }
    }

}



