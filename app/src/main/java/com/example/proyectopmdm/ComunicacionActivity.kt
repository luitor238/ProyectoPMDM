package com.example.proyectopmdm
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComunicacionActivity : AppCompatActivity() {

    private lateinit var EditTextInput: EditText
    private lateinit var btnEnviar: Button
    private lateinit var opcion1: Button
    private lateinit var opcion2: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var btnVolver: ImageButton


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicacion)

        EditTextInput = findViewById(R.id.EditTextInput)
        btnEnviar = findViewById(R.id.btnEnviar)
        opcion1 = findViewById(R.id.opcion1)
        opcion2 = findViewById(R.id.opcion2)
        recyclerView = findViewById(R.id.recyclerView)
        btnVolver = findViewById(R.id.btnVolver)

        adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        // Boton volver al menu
        btnVolver.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }

        btnEnviar.setOnClickListener {
            val message = EditTextInput.text.toString().trim()
            if (message.isNotEmpty()) {
                adapter.sendMessage(message) // Cambia 'instancia' por 'adapter'
                EditTextInput.text.clear()
            }
        }

        opcion1.setOnClickListener {
            val message = "¿Como estas?"
            if (message.isNotEmpty()) {
                adapter.sendMessage(message) // Cambia 'instancia' por 'adapter'
                EditTextInput.text.clear()
            }
        }

        opcion2.setOnClickListener {
            val message = "¿Tienes algo equipado?"
            if (message.isNotEmpty()) {
                adapter.sendMessage(message) // Cambia 'instancia' por 'adapter'
                EditTextInput.text.clear()
            }
        }
    }
}
