package com.example.proyectopmdm

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    // VARIABLES
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnCrearCuenta: Button
    private lateinit var textViewWarning: TextView
    private lateinit var auth: FirebaseAuth
    // VARIABLE PARA LOG
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d(TAG, "onCreate: La actividad está siendo creada")

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val dbHelper = DatabaseHelper(this)

        GlobalVariables.personaje = Personaje(GlobalVariables.id, "Luisito",Personaje.Raza.Elfo, Personaje.Clase.Guerrero, Personaje.EstadoVital.Joven)

        // Asignar variables a los elementos del layout
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta)
        textViewWarning = findViewById(R.id.textViewWarning)

        // Configurar el clic del botón de inicio de sesión
        btnIniciarSesion.setOnClickListener {
            val emailStr: String = email.text.toString()
            val passwordStr: String = password.text.toString()

            if (emailStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                auth.signInWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Autenticación del usuario correcta")

                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            Log.d(TAG, "Id1: " + userId)

                            if (userId != null) {
                                GlobalVariables.id = userId
                                Log.d(TAG, "Id2: ${GlobalVariables.id}")

                                val personajes = dbHelper.getPersonaje()
                                Log.d(TAG, "prueba")

                                if (personajes.isNotEmpty()) {
                                    Log.d(TAG, "La lista de personajes no está vacía y contiene elementos.")
                                    /*for (e in personajes) {
                                        Log.d(TAG, "Id3: ${e.getId()}")
                                        if (e.getId() == userId) {
                                            GlobalVariables.personaje = e
                                        }
                                    }*/
                                } else {
                                    Log.d(TAG, "La lista de personajes está vacía o es nula.")
                                }

                                val intent = Intent(this, VerPersonajeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.w(TAG, "Error en la autenticación", task.exception)
                                AlertDialog.Builder(this)
                                    .setMessage("Error en la autenticación")
                                    .setPositiveButton("Aceptar", null)
                                    .create()
                                    .show()
                            }
                        } else {
                            Log.w(TAG, "Error en la autenticación", task.exception)
                            AlertDialog.Builder(this)
                                .setMessage("Error en la autenticación")
                                .setPositiveButton("Aceptar", null)
                                .create()
                                .show()
                        }
                    }
            } else {
                Log.d(TAG, "Debes rellenar los campos")
            }
        }

        // Configurar el clic del botón de crear cuenta
        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
        }
    }
}
