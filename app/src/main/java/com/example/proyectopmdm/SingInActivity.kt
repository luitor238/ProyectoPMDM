package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SingInActivity : AppCompatActivity() {
    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var RepeatPassword : EditText
    private lateinit var btnCrearCuenta: Button
    private lateinit var btnVolver: ImageButton
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
        btnVolver = findViewById(R.id.imageBtnGoBack)
        auth= Firebase.auth

        try {
            btnCrearCuenta.setOnClickListener{

                if (Email.text.isNotEmpty() && Password.text.isNotEmpty() && RepeatPassword.text.isNotEmpty()){

                    if(Email.text.toString().contains('@') && Email.text.toString().contains('.')){
                        if(Password.text.toString()==RepeatPassword.text.toString()) {
                            if(Password.length()>=8) {
                                crearUsuario()
                            } else {
                                textViewWarning.text = "La contraseña debe tener minimo 8 caracteres."
                                textViewWarning.visibility = View.VISIBLE
                                Log.d(TAG, "Contraseña muy corta")
                            }
                        }else{
                            textViewWarning.text = "La contraseña no es igual"
                            textViewWarning.visibility = View.VISIBLE
                            Log.d(TAG, "La contraseña no es igual")
                        }
                    }
                    else{
                        textViewWarning.text = "Formato email incorrecto."
                        textViewWarning.visibility = View.VISIBLE
                        Log.d(TAG, "Formato email incorrecto.")
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

        btnVolver.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })

    }


    //Funcion CREAR USUARIO
    fun crearUsuario(){
        auth.createUserWithEmailAndPassword(Email.text.toString(), Password.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                val userId = FirebaseAuth.getInstance().currentUser?.uid
                if (userId != null) {

                    val globalInstance = variableGlobal.getInstance()
                    globalInstance.initPersonaje(userId)
                    Log.d(TAG, "El usuario creado correctamente")

                } else {
                    Log.d(TAG, "El usuario no está autenticado. Manejar el error apropiadamente")
                }


                val intent = Intent(this, CrearPersonajeActivity::class.java)
                intent.putExtra("email", Email.text.toString())
                intent.putExtra("password", Password.text.toString())
                startActivity(intent)
            } else {
                textViewWarning.text = "Usuario No Encontrado"
                textViewWarning.visibility = View.VISIBLE
                Log.d(TAG, "Usuario No Creado Correctamente")
            }
        }
    }
}