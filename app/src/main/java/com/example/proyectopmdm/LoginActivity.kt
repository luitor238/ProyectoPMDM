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
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Autenticacion del ususario Correcta")
                            //val user = auth.currentUser

                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            val nombrePersonaje = "nombreDelPersonaje" // Nombre del personaje que deseas recuperar

                            if (userId != null) {
                                obtenerPersonajeDeFirestore(userId, nombrePersonaje) { personaje ->
                                    if (personaje != null) {
                                        // Se ha recuperado el objeto Personaje correctamente
                                        // Puedes usar el objeto personaje aquí
                                        println("Se ha recuperado el personaje: $personaje")
                                    } else {
                                        // No se pudo encontrar el objeto Personaje
                                        println("No se encontró el personaje con el nombre $nombrePersonaje")
                                    }
                                }
                            } else {
                                // El usuario no está autenticado. Manejar el error apropiadamente
                            }





                            val intent = Intent(this, VerPersonajeActivity::class.java)
                            intent.putExtra("email", Email.text.toString())
                            startActivity(intent)


                        } else {
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

fun obtenerPersonajeDeFirestore(userId: String, nombrePersonaje: String, callback: (Personaje?) -> Unit) {
    val db = FirebaseFirestore.getInstance()

    // Obtén el documento del personaje en Firestore
    db.collection("usuarios").document(userId)
        .collection("personajes").document(nombrePersonaje)
        .get()
        .addOnSuccessListener { document ->
            if (document.exists()) {
                // El documento existe, convierte los datos a un objeto Personaje
                val nombre = document.getString("nombre") ?: ""
                val raza = Personaje.Raza.valueOf(document.getString("raza") ?: "Humano")
                val clase = Personaje.Clase.valueOf(document.getString("clase") ?: "Guerrero")
                val estadoVital = Personaje.EstadoVital.valueOf(document.getString("estadoVital") ?: "Joven")
                val salud = document.getLong("salud")?.toInt() ?: 0
                val ataque = document.getLong("ataque")?.toInt() ?: 0
                // Agrega los demás campos según tu objeto

                // Crea el objeto Personaje con los datos obtenidos




                val personaje = Personaje(nombre, raza, clase, estadoVital)
                personaje.setSalud(salud)
                personaje.setAtaque(ataque)

                val personajeFinal = variablesGlobales.getInstance().initPersonaje(personaje)
                // Setea los demás campos según corresponda

            } else {
                // El documento no existe, retorna null
                callback(null)
            }
        }
        .addOnFailureListener { e ->
            // Error al obtener el documento. Manejar el error apropiadamente
            callback(null)
        }
}
