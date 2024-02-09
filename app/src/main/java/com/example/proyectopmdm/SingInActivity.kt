package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SingInActivity : AppCompatActivity() {

    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var RepeatPassword : EditText
    private lateinit var btnCrearCuenta: Button
    private lateinit var textViewWarning: TextView
    private lateinit var auth: FirebaseAuth
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        Email = findViewById(R.id.editTextEmail)
        Password = findViewById(R.id.editTextPassword)
        RepeatPassword = findViewById(R.id.editTextRepeatPassword)
        btnCrearCuenta = findViewById(R.id.btnCrear)
        textViewWarning = findViewById(R.id.textViewWarning)
        auth= Firebase.auth

        try {
            btnCrearCuenta.setOnClickListener{

                if (Email.text.isNotEmpty() && Password.text.isNotEmpty() && RepeatPassword.text.isNotEmpty()){

                    if(Password.text==RepeatPassword.text) {
                        crearUsuario()
                    }else{
                        textViewWarning.text = "La contraseña no es igual"
                        textViewWarning.visibility = View.VISIBLE
                        Log.d(TAG, "La contraseña no es igual")
                    }

                } else{
                    textViewWarning.text = "Debes rellenar todos los campos"
                    textViewWarning.visibility = View.VISIBLE
                    Log.d(TAG, "Debes rellenar todos los campos")
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "Error no esperado")
        }
    }


    //Funcion CREAR USUARIO
    fun crearUsuario(){
        auth.createUserWithEmailAndPassword(Email.text.toString(), Password.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Usuario Creado Correctamente")

                val intent = Intent(this, VerPersonajeActivity::class.java)
                startActivity(intent)
            } else {
                textViewWarning.text = "Usuario No Encontrado"
                textViewWarning.visibility = View.VISIBLE
                Log.d(TAG, "Usuario No Creado Correctamente")
            }
        }
    }
}