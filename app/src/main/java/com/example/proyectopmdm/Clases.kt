package com.example.proyectopmdm

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import java.io.Serializable


class Personaje :Serializable {

    private var id: Int = 0
    private var nombre: String = ""
    private var raza: Raza = Raza.Humano
    private var clase: Clase = Clase.Brujo
    private var estadoVital: EstadoVital = EstadoVital.Joven
    private var salud: Int = 0
    private var ataque: Int = 0
    private var experiencia: Int = 0
    private var nivel: Int = 0
    private var suerte: Int = 0
    private var defensa: Int = 0

    constructor(nombre: String, raza: Raza, clase: Clase, estadoVital: EstadoVital) {
        this.nombre = nombre
        this.raza = raza
        this.clase = clase
        this.estadoVital = estadoVital
    }

    constructor(id: Int, nombre: String, raza: Raza, clase: Clase, estadoVital: EstadoVital, salud: Int, ataque: Int, experiencia: Int, nivel: Int, suerte: Int, defensa: Int) {
        this.id = id
        this.nombre = nombre
        this.raza = raza
        this.clase = clase
        this.estadoVital = estadoVital
        this.salud = salud
        this.ataque = ataque
        this.experiencia = experiencia
        this.nivel = nivel
        this.suerte = suerte
        this.defensa = defensa
    }

    // Enumeración para el tipo de raza y clase
    enum class Raza { Humano, Elfo, Enano, Maldito }
    enum class Clase { Brujo, Mago, Guerrero }
    enum class EstadoVital{Anciano, Joven, Adulto}

    private val mochila = Mochila(10) // Ejemplo de icon_peso máximo de la mochila
    // Atributos para el equipo del personaje
    private var arma: Articulo? = null
    private var proteccion: Articulo? = null

    // Inicialización de los atributos tras la construcción del objeto Personaje
    init {
        if(experiencia==0 && nivel==0 && suerte==0) {
            calcularSalud()
            calcularAtaque()
            calcularDefensa()
            //calcularImagen()
            experiencia = 0
            nivel = 1
            suerte = (0..10).random() // Asigna un valor de suerte aleatorio entre 0 y 10
        }
    }

    fun getId(): Int{
        return id
    }
    fun getNombre(): String {
        return nombre
    }
    fun setNombre(nuevoNombre: String) {
        nombre = nuevoNombre
    }
    fun getRaza(): Raza {
        return raza
    }

    fun getSalud(): Int {
        return salud
    }
    fun setSalud(nuevaSalud: Int) {
        salud = nuevaSalud
    }
    fun getAtaque(): Int {
        return ataque
    }
    fun setAtaque(nuevoAtaque: Int) {
        ataque = nuevoAtaque
    }
    fun getClase(): Clase {
        return clase
    }

    fun setClase(nuevaClase: String) {

        when(nuevaClase){
            "Brujo" -> { clase = Clase.Brujo }

            "Mago" -> {clase = Clase.Mago }

            "Guerrero" -> {clase = Clase.Guerrero }

        }

    }

    fun getImagen(): String{
        when(this.clase){
            Clase.Brujo -> {
                when(this.raza){
                    Raza.Humano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "humano_brujo_joven"
                            EstadoVital.Adulto -> return "humano_brujo_adulto"
                            EstadoVital.Anciano -> return "R.drawable.humano_brujo_viejojfif"
                            }
                        }
                    Raza.Elfo -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "elfo_brujo_joven"
                            EstadoVital.Adulto -> return "elfo_brujo_adulto"
                            EstadoVital.Anciano -> return "elfo_brujo_viejo"
                        }
                    }
                    Raza.Enano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "enano_brujo_joven"
                            EstadoVital.Adulto -> return "enano_brujo_adulto"
                            EstadoVital.Anciano -> return "enano_brujo_viejo"
                        }
                    }
                    Raza.Maldito -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "maldito_brujo_joven"
                            EstadoVital.Adulto -> return "maldito_brujo_adulto"
                            EstadoVital.Anciano -> return "maldito_brujo_viejo"
                        }
                    }
                }
            }
            Clase.Mago -> {
                when(raza){
                    Raza.Humano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "humano_mago_joven"
                            EstadoVital.Adulto -> return "humano_mago_adulto"
                            EstadoVital.Anciano -> return "humano_mago_viejo"
                        }
                    }
                    Raza.Elfo -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "elfo_mago_joven"
                            EstadoVital.Adulto -> return "elfo_mago_adulto"
                            EstadoVital.Anciano -> return "elfo_mago_viejo"
                        }
                    }
                    Raza.Enano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "enano_mago_joven"
                            EstadoVital.Adulto -> return "enano_mago_adulto"
                            EstadoVital.Anciano -> return "enano_mago_viejo"
                        }
                    }
                    Raza.Maldito -> {
                        when(this.estadoVital){
                            EstadoVital.Joven -> return "maldito_mago_joven"
                            EstadoVital.Adulto -> return "maldito_mago_adulto"
                            EstadoVital.Anciano -> return "maldito_mago_viejo"
                        }
                    }
                }
            }
            Clase.Guerrero -> {
                when(raza){
                    Raza.Humano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven ->  return "humano_guerrero_joven"
                            EstadoVital.Adulto ->  return "humano_guerrero_adulto"
                            EstadoVital.Anciano ->  return "humano_guerrero_viejo"
                        }
                    }
                    Raza.Elfo -> {
                        when(this.estadoVital){
                            EstadoVital.Joven ->  return "elfo_guerrero_joven"
                            EstadoVital.Adulto ->  return "elfo_guerrero_adulto"
                            EstadoVital.Anciano ->  return "elfo_guerrero_viejo"
                        }
                    }
                    Raza.Enano -> {
                        when(this.estadoVital){
                            EstadoVital.Joven ->  return "enano_guerrero_joven"
                            EstadoVital.Adulto ->  return "enano_guerrero_adulto"
                            EstadoVital.Anciano ->  return "enano_guerrero_viejo"
                        }
                    }
                    Raza.Maldito -> {
                        when(this.estadoVital){
                            EstadoVital.Joven ->  return "maldito_guerrero_joven"
                            EstadoVital.Adulto ->  return "maldito_guerrero_adulto"
                            EstadoVital.Anciano ->  return "maldito_guerrero_viejo"
                        }
                    }
                }
            }
        }

    }

    fun getEstadoVital(): EstadoVital {
        return estadoVital
    }
    fun setEstadoVital(stringEstadoVital: String) {
        when(stringEstadoVital){
            "Joven" -> estadoVital = EstadoVital.Joven

            "Adulto" -> estadoVital = EstadoVital.Adulto
            "Anciano" -> estadoVital = EstadoVital.Anciano
        }
    }
    fun getExperiencia(): Int {
        return experiencia
    }
    fun setExperiencia(experienciaGanada: Int) {
        experiencia += experienciaGanada
        while (experiencia >= 1000) {
            subirNivel()
            experiencia -= 1000 // Reducir la experiencia en 1000 al subir de nivel
        }
    }
    fun getNivel(): Int {
        return nivel
    }
    fun subirNivel() {
        if (nivel < 10) { // Limitar el nivel a 10
            nivel++
            calcularSalud() // Calcular el nuevo valor de salud al subir de nivel
            calcularAtaque() // Calcular el nuevo valor de ataque al subir de nivel
            calcularDefensa()
        }
    }
    fun getDefensa(): Int {
        return defensa
    }
    fun getSuerte(): Int {
        return suerte
    }
    private fun calcularSalud() {
        salud = when (nivel) {
            1 -> 100
            2 -> 200
            3 -> 300
            4 -> 450
            5 -> 600
            6 -> 800
            7 -> 1000
            8 -> 1250
            9 -> 1500
            10 -> 2000
            else -> 100 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }

    private fun calcularAtaque() {
        ataque = when (nivel) {
            1 -> 10
            2 -> 20
            3 -> 25
            4 -> 30
            5 -> 40
            6 -> 100
            7 -> 200
            8 -> 350
            9 -> 400
            10 -> 450
            else -> 10 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }
    private fun calcularDefensa() {
        defensa = when (nivel) {
            1 -> 4
            2 -> 9
            3 -> 14
            4 -> 19
            5 -> 49
            6 -> 59
            7 -> 119
            8 -> 199
            9 -> 349
            10 -> 399
            else -> 4 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }
    fun pelea(monstruo: Monstruo): String {
        var result=""
        var vidaMonstruo = monstruo.getSalud()
        var expGanada =
            monstruo.getSalud() // La experiencia ganada es igual a la salud inicial del monstruo
        var vidaPersonaje = salud
        var contador = false
        println("¡Un ${monstruo.getNombre()} se acerca!")



        while (vidaMonstruo > 0 && vidaPersonaje > 0) {
            // Preguntar al usuario si desea activar la habilidad
            println("¿Deseas activar la habilidad del personaje? (Sí/No)")
            val respuesta = readLine()?.toLowerCase()

            if ((respuesta == "si" || respuesta == "sí") && contador == false) {
                habilidad() // Activa la habilidad del personaje
                contador = true
            }
            val evasion = suerte >= 10
            val ataqueMonstruo = if (evasion) 0 else monstruo.getAtaque()

            // Aplicar la defensa del personaje
            val defensaPersonaje = defensa * suerte / 100
            val danoMonstruo = if (evasion) 0 else ataqueMonstruo - defensaPersonaje

            if (!evasion) {
                vidaPersonaje -= danoMonstruo
            }

            println("${nombre} tiene una suerte de ${suerte}% y una defensa de ${defensaPersonaje}.")
            println("${nombre} ha recibido ${danoMonstruo} de daño. Salud de ${nombre}: ${vidaPersonaje}")

            if (vidaMonstruo > 0) {
                // Personaje ataca al monstruo
                vidaMonstruo -= ataque
                println("${nombre} ataca al ${monstruo.getNombre()}. Salud del ${monstruo.getNombre()}: ${vidaMonstruo}")
                if (vidaMonstruo <= 0) {
                    setExperiencia(expGanada)  // El personaje gana experiencia igual a la salud inicial del monstruo
                    println("${nombre} ha derrotado al ${monstruo.getNombre()} y gana ${expGanada} de experiencia.")
                    result = "¡GANASTE!"
                    break
                } else {
                    result = "¡PERDISTE!"
                }

                // Monstruo ataca al personaje
                vidaPersonaje -= ataqueMonstruo
                println("${monstruo.getNombre()} ataca a ${nombre}. Salud de ${nombre}: ${vidaPersonaje}")
            }
        }
        return result
    }

    fun habilidad() {
        when (clase) {
            Clase.Mago -> {
                calcularSalud() // Subir la salud al límite del nivel
                println("$nombre utiliza su habilidad de Mago para restaurar su salud.")
            }
            Clase.Brujo -> {
                ataque *= 2 // Duplicar el ataque
                println("$nombre utiliza su habilidad de Brujo para duplicar su ataque.")
            }
            Clase.Guerrero -> {
                suerte *= 2 // Duplicar la suerte
                println("$nombre utiliza su habilidad de Guerrero para duplicar su suerte.")
            }
        }
    }
    fun entrenar(tiempoDeEntrenamiento: Int) {
        val factorExperienciaPorHora = 5
        val experienciaGanada = tiempoDeEntrenamiento * factorExperienciaPorHora

        setExperiencia(experienciaGanada)

        println("$nombre ha entrenado durante $tiempoDeEntrenamiento horas y ha ganado $experienciaGanada de experiencia.")
    }
    fun realizarMision(tipoMision: String, dificultad: String) {
        val probabilidadExito = when (dificultad) {
            "Fácil" -> if (nivel >= 5) 8 else 6
            "Normal" -> if (nivel >= 3) 6 else 4
            "Difícil" -> if (nivel >= 7) 4 else 2
            else -> 0 // En caso de dificultad no reconocida
        }

        val resultado = (1..10).random() // Valor aleatorio entre 1 y 10

        if (resultado <= probabilidadExito) {
            val experienciaGanada = when (tipoMision) {
                "Caza" -> 1000
                "Búsqueda" -> 500
                "Asedio" -> 2000
                "Destrucción" -> 5000
                else -> 0 // En caso de tipo de misión no reconocido
            }

            val multiplicadorExperiencia = when (dificultad) {
                "Fácil" -> 0.5
                "Normal" -> 1.0
                "Difícil" -> 2.0
                else -> 0.0 // En caso de dificultad no reconocida
            }

            val experienciaFinal = (experienciaGanada * multiplicadorExperiencia).toInt()
            setExperiencia(experienciaFinal)
            println("$nombre ha completado la misión de $tipoMision ($dificultad) con éxito y gana $experienciaFinal de experiencia.")
        } else {
            println("$nombre ha fracasado en la misión de $tipoMision ($dificultad) y no recibe ninguna recompensa.")
        }
    }
    fun cifrado(mensaje : String, ROT : Int) : String{
        val abecedario : ArrayList<Char> = "abcdefghijklmnñopqrstuvwxyz".toList() as ArrayList<Char>
        var stringInv = ""
        var indice = 0

        for(i in mensaje.lowercase().toList() as ArrayList<Char>){
            if(!i.isLetter() || i.isWhitespace()){
                stringInv += i
            } else{
                indice = abecedario.indexOf(i) + ROT
                if (indice >= abecedario.size) {
                    indice -= abecedario.size
                    stringInv += abecedario[indice]
                } else
                    stringInv += abecedario[indice]
            }
        }
        return stringInv.filter { !it.isWhitespace() && it.isLetter() }
    }
    fun comunicacion(mensaje:String){
        var respuesta=""
        when(estadoVital){
            EstadoVital.Adulto->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="En la flor de la vida, pero me empieza a doler la espalda"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Estoy buscando la mejor solución"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="No me levantes la voz mequetrefe"
                        else
                            if (mensaje == nombre)
                                respuesta="¿Necesitas algo?"
                            else
                                if(mensaje == "Tienes algo equipado?"){
                                    if (arma != null || proteccion != null) {
                                        val equipamiento = mutableListOf<String>()
                                        if (arma != null) {
                                            equipamiento.add(arma!!.getNombre().name)
                                        }
                                        if (proteccion != null) {
                                            equipamiento.add(proteccion!!.getNombre().name)
                                        }
                                        println("Tengo equipado: ${equipamiento.joinToString(", ")}")
                                    } else {
                                        println("Ligero como una pluma.")
                                    }
                                }
                                else
                                    respuesta="No sé de qué me estás hablando"
            }
            EstadoVital.Joven->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="De lujo"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Tranqui se lo que hago"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="Eh relájate"
                        else
                            if (mensaje == nombre)
                                respuesta="Qué pasa?"
                            else
                                if(mensaje == "Tienes algo equipado?"){
                                    if (arma != null || proteccion != null) {
                                        println("No llevo nada, agente, se lo juro.")
                                    } else {
                                        println("Regístrame si quieres.")
                                    }
                                }
                                else
                                    respuesta="Yo que se"

            }
            EstadoVital.Anciano->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="No me puedo mover"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Que no te escucho!"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="Háblame más alto que no te escucho"
                        else
                            if (mensaje == nombre)
                                respuesta="Las 5 de la tarde"
                            else
                                if(mensaje == "Tienes algo equipado?"){
                                    println("A ti que te importa nini!")
                                }
                                else
                                    respuesta="En mis tiempos esto no pasaba"
            }
        }
        when(raza){
            Raza.Elfo-> println(cifrado(respuesta, 1))
            Raza.Enano-> println(respuesta.uppercase())
            Raza.Maldito-> println(cifrado(respuesta, 1))
            else -> println(respuesta)
        }
    }
    fun equipar(articulo: Articulo) {
        when (articulo.getTipoArticulo()) {
            Articulo.TipoArticulo.ARMA -> {
                if (articulo.getNombre() in Articulo.Nombre.BASTON..Articulo.Nombre.GARRAS) {
                    arma = articulo
                    // Aumentar el ataque del personaje según el nombre del arma
                    ataque += articulo.getAumentoAtaque()
                    println("Has equipado el arma: $articulo")
                    mochila.getContenido().remove(articulo)
                } else {
                    println("No se puede equipar el artículo. Tipo de arma no válido.")
                }
            }
            Articulo.TipoArticulo.PROTECCION -> {
                when (articulo.getNombre()) {
                    Articulo.Nombre.ESCUDO, Articulo.Nombre.ARMADURA -> {
                        proteccion = articulo
                        // Aumentar la defensa del personaje solo si la protección es un escudo o una armadura
                        defensa += articulo.getAumentoDefensa()
                        println("Has equipado la protección: $articulo")
                        mochila.getContenido().remove(articulo)
                    }
                    else -> {
                        println("No se puede equipar el artículo. Tipo de protección no válido.")
                    }
                }
            }
            else -> {
                println("No se puede equipar el artículo. Tipo de artículo no válido.")
            }
        }
    }
    fun usarObjeto(articulo: Articulo, context: Context) {
        when (articulo.getTipoArticulo()) {
            Articulo.TipoArticulo.OBJETO -> {
                when (articulo.getNombre()) {
                    Articulo.Nombre.POCION -> {
                        // Aumentar la vida del personaje al usar una poción
                        salud += articulo.getAumentoVida()
                        Toast.makeText(context, "Has usado la poción y aumentado tu vida. Vida actual: $salud", Toast.LENGTH_SHORT).show()
                    }
                    Articulo.Nombre.IRA -> {
                        // Aumentar el ataque del personaje al usar un objeto de ira
                        ataque += articulo.getAumentoAtaque()
                        Toast.makeText(context, "Has canalizado tu ira y aumentado tu ataque. Ataque actual: $ataque", Toast.LENGTH_SHORT).show()
                    }

                    else -> {Toast.makeText(context, "Este articulo no tiene uso.", Toast.LENGTH_SHORT).show()}
                }
            }
            Articulo.TipoArticulo.ARMA -> {
                ataque += articulo.getAumentoAtaque()
                Toast.makeText(context, "Has usado un objeto tipo arma y aumentado tu ataque a $ataque.", Toast.LENGTH_SHORT).show()
            }
            Articulo.TipoArticulo.PROTECCION -> {
                salud += articulo.getAumentoDefensa()
                Toast.makeText(context, "Has usado un objeto de proteccion y aumentado tu defensa a $defensa.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Este articulo no tiene uso.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getMochila(): Mochila {
        return this.mochila
    }

    fun getImage(): ImageView {
        lateinit var imagen :ImageView
        when(clase){
            Clase.Brujo -> {
                when(getRaza().toString()){
                    "Humano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.humano_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.humano_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_brujo_viejojfif)
                        }
                    }
                    "Elfo" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.elfo_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_brujo_viejo)
                        }
                    }
                    "Enano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.enano_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_brujo_viejo)
                        }
                    }
                    "Maldito" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.maldito_brujo_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_brujo_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_brujo_viejo)
                        }
                    }
                }
            }
            Clase.Mago -> {
                when(getRaza().toString()){
                    "Humano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.humano_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.humano_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_mago_viejo)
                        }
                    }
                    "Elfo" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.elfo_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_mago_viejo)
                        }
                    }
                    "Enano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.enano_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_mago_viejo)
                        }
                    }
                    "Maldito" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.maldito_mago_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_mago_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_mago_viejo)
                        }
                    }
                }
            }
            Clase.Guerrero -> {
                when(getRaza().toString()){
                    "Humano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.humano_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.humano_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.humano_guerrero_viejo)
                        }
                    }
                    "Elfo" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.elfo_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.elfo_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.elfo_guerrero_viejo)
                        }
                    }
                    "Enano" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.enano_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.enano_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.enano_guerrero_viejo)
                        }
                    }
                    "Maldito" -> {
                        when(getEstadoVital().toString()){
                            "Joven" -> imagen.setImageResource(R.drawable.maldito_guerrero_joven)
                            "Adulto" -> imagen.setImageResource(R.drawable.maldito_guerrero_adulto)
                            "Anciano" -> imagen.setImageResource(R.drawable.maldito_guerrero_viejo)
                        }
                    }
                }
            }
        }
        return imagen
    }

    override fun toString(): String {
        return "Personaje: Nombre: $nombre, Nivel: $nivel, Salud: $salud, Ataque: $ataque, Defensa: $defensa, Suerte: $suerte, Raza: $raza, Clase: $clase, Estado Vital: $estadoVital Mochila: $mochila"
    }

}

class Mochila(private var pesoMochila: Int):Serializable {
    private var contenido=ArrayList<Articulo>()

    fun getPesoMochila():Int{
        return pesoMochila
    }
    fun addArticulo(articulo: Articulo) {
        if (articulo.getPeso() <= pesoMochila) {
            when (articulo.getTipoArticulo()) {
                Articulo.TipoArticulo.ARMA -> {
                    when (articulo.getNombre()) {
                        Articulo.Nombre.BASTON, Articulo.Nombre.ESPADA, Articulo.Nombre.DAGA,
                        Articulo.Nombre.MARTILLO, Articulo.Nombre.GARRAS -> {
                            contenido.add(articulo)
                            this.pesoMochila -= articulo.getPeso()
                            println("${articulo.getNombre()} ha sido añadido a la mochila.")
                        }
                        else -> println("Nombre del artículo no válido para el tipo ARMA.")
                    }
                }
                Articulo.TipoArticulo.OBJETO -> {
                    when (articulo.getNombre()) {
                        Articulo.Nombre.POCION, Articulo.Nombre.IRA -> {
                            contenido.add(articulo)
                            this.pesoMochila -= articulo.getPeso()
                            println("${articulo.getNombre()} ha sido añadido a la mochila.")
                        }
                        else -> println("Nombre del artículo no válido para el tipo OBJETO.")
                    }
                }
                Articulo.TipoArticulo.PROTECCION -> {
                    when (articulo.getNombre()) {
                        Articulo.Nombre.ESCUDO, Articulo.Nombre.ARMADURA -> {
                            contenido.add(articulo)
                            this.pesoMochila -= articulo.getPeso()
                            println("${articulo.getNombre()} ha sido añadido a la mochila.")
                        }
                        else -> println("Nombre del artículo no válido para el tipo PROTECCION.")
                    }
                }
                else-> println("Nada")
            }
        } else {
            println("El icon_peso del artículo excede el límite de la mochila.")
        }
    }
    fun getContenido(): ArrayList<Articulo> {
        return contenido
    }
    fun findObjeto(nombre: Articulo.Nombre): Int {
        return contenido.indexOfFirst { it.getNombre() == nombre }
    }
    override fun toString(): String {
        return if (contenido.isEmpty()) {
            "Mochila vacía"
        } else {
            "Artículos en la mochila: ${contenido.joinToString("\n")}"
        }
    }
}


class Articulo( var id: Int, private var nombre: Nombre, private var peso: Int) :Serializable {

    enum class TipoArticulo { ARMA, OBJETO, PROTECCION, ORO }
    enum class Nombre { BASTON, ESPADA, DAGA, MARTILLO, GARRAS, POCION, IRA, ESCUDO, ARMADURA, MONEDA }

    private var tipoArticulo: TipoArticulo = when (nombre) {
        Nombre.BASTON, Nombre.ESPADA, Nombre.DAGA, Nombre.MARTILLO, Nombre.GARRAS, Nombre.IRA -> TipoArticulo.ARMA
        Nombre.POCION -> TipoArticulo.OBJETO
        Nombre.ESCUDO, Nombre.ARMADURA -> TipoArticulo.PROTECCION
        Nombre.MONEDA -> TipoArticulo.ORO
    }

    private var precio: Int = when (nombre) {
        Nombre.MONEDA -> 15
        Nombre.BASTON -> 5
        Nombre.ESPADA -> 12
        Nombre.DAGA -> 7
        Nombre.MARTILLO -> 3
        Nombre.POCION -> 15
        Nombre.IRA -> 10
        Nombre.ARMADURA -> 20
        Nombre.ESCUDO -> 9
        else -> 0
    }

    private var imagen: String = when (nombre) {
        Nombre.BASTON -> "articulo_baston"
        Nombre.ESPADA -> "articulo_espada"
        Nombre.DAGA -> "articulo_daga"
        Nombre.MARTILLO -> "articulo_martillo"
        Nombre.GARRAS -> "articulo_garras2"
        Nombre.POCION -> "articulo_pocion2"
        Nombre.IRA -> "articulo_ira2"
        Nombre.ESCUDO -> "articulo_escudo"
        Nombre.ARMADURA -> "articulo_armadura"
        Nombre.MONEDA -> "articulo_moneda"
    }


    init{
        if(nombre==Nombre.MONEDA)
            peso=0
    }


    fun getPeso(): Int {
        return peso
    }
    fun getNombre(): Nombre {
        return nombre
    }
    fun getTipoArticulo(): TipoArticulo {
        return tipoArticulo
    }
    fun getPrecio(): Int {
        return precio
    }
    fun getImagen(): String {
        return imagen
    }
    fun getAumentoAtaque(): Int {
        return when (nombre) {
            Nombre.BASTON -> 10
            Nombre.ESPADA -> 20
            Nombre.DAGA -> 15
            Nombre.MARTILLO -> 25
            Nombre.GARRAS -> 30
            Nombre.IRA -> 80
            else -> 0 // Para otros tipos de armas no especificados
        }
    }
    fun getAumentoDefensa(): Int {
        return when (nombre) {
            Nombre.ESCUDO -> 10
            Nombre.ARMADURA -> 20
            else -> 0 // Para otros tipos de protecciones no especificados
        }
    }
    fun getAumentoVida(): Int {
        return when (nombre) {
            Nombre.POCION -> 100
            else -> 0 // Para otros tipos de objetos no especificados
        }
    }
    override fun toString(): String {
        return "[Tipo Artículo:$tipoArticulo, Nombre:$nombre, Peso:$peso]"
    }
}

class variableGlobal private constructor() {
    // Variable global
    var  idPersonaje:  String? = null

    companion object {
        // Referencia a la única instancia de la clase
        @Volatile
        private var instance: variableGlobal? = null

        // Función para obtener la instancia de la clase
        fun getInstance(): variableGlobal {
            return instance ?: synchronized(this) { instance ?: variableGlobal().also { instance = it }
            }
        }
    }

    // Función para inicializar la variable global del personaje
    fun initPersonaje(id: String?) {
        idPersonaje = id
    }

}

object GlobalVariables {
    var  id: String = ""
}


