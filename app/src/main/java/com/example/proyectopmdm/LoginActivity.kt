package com.example.proyectopmdm


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    //VARIABLES
    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnCrearCuenta: Button
    private lateinit var textViewWarning: TextView
    private lateinit var auth: FirebaseAuth
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate: La actividad está siendo creada")
        //CREACION DE LA VISTA

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //ASIGNACION DE VARIABLES CON ELEMENTOS LAYOUT
        Log.d(TAG, "ASIGNACION DE VARIABLES CON ELEMENTOS LAYOUT")


        Email = findViewById(R.id.editTextEmail)
        Password = findViewById(R.id.editTextPassword)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta)
        textViewWarning = findViewById(R.id.textViewWarning)



        try {
            btnIniciarSesion.setOnClickListener{

                if (Email.text.isNotEmpty() && Password.text.isNotEmpty()){
                    auth.signInWithEmailAndPassword(Email.text.toString(), Password.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            Log.d(TAG, "Autenticacion del ususario Correcta")


                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            if (userId != null) {


                                val globalInstance = variableGlobal.getInstance()





                            } else {
                                Log.d(TAG, "El usuario no está autenticado. Manejar el error apropiadamente")
                            }




                        }
                        else {
                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("Error")
                            builder.setMessage("Se ha producido un error en la autenticacion del ususario")
                            builder.setPositiveButton("Aceptar",null)
                            val dialog: AlertDialog = builder.create()
                            dialog.show()
                        }
                    }

                }else{ Log.d(TAG, "Debes rellenar los campos") }

            }
        } catch (e: Exception) {
            Log.d(TAG, "Error en la autentificacion del usuario")
        }


        try {
            btnCrearCuenta.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, SingInActivity::class.java)
                startActivity(intent)
            })

        } catch (e: Exception) {
            Log.d(TAG, "Usuario No Creado Correctamente")
        }
    }
}






