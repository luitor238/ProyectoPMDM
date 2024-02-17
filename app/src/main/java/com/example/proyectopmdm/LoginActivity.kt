package com.example.proyectopmdm

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

        // Asignar variables a los elementos del layout
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta)
        textViewWarning = findViewById(R.id.textViewWarning)


        // Configurar el clic del botón de inicio de sesión
        btnIniciarSesion.setOnClickListener {
            var emailStr: String = email.text.toString()
            var passwordStr: String = password.text.toString()

            if (emailStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                auth.signInWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Autenticación del usuario correcta")

                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            if (userId != null) {
                                GlobalVariables.id = userId
                                val personajes = dbHelper.getPersonaje()
                                for (e in personajes){
                                    if(e.getId()==userId){
                                        GlobalVariables.personaje = e
                                    }
                                }

                                val intent = Intent(this, VerPersonajeActivity::class.java)
                                startActivity(intent)
                                // Aquí puedes realizar acciones adicionales después de iniciar sesión correctamente
                            } else {
                                Log.w(TAG, "Error en la autenticación", task.exception)
                                val builder = AlertDialog.Builder(this)
                                builder.setPositiveButton("Aceptar", null)
                                val dialog: AlertDialog = builder.create()
                                dialog.show()

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
    }
}
