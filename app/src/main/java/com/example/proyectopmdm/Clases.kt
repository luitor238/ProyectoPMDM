package com.example.proyectopmdm

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import java.io.Serializable


class Personaje (private var id: String, private var nombre: String, private var raza: Raza, private var clase: Clase, private var estadoVital: EstadoVital):Serializable {

    private var salud: Int = 0
    private var ataque: Int = 0
    private var experiencia: Int = 0
    private var nivel: Int = 0
    private var suerte: Int = 0
    private var defensa: Int = 0
    private var monedero: Int = 50

    // Enumeración para el tipo de raza y clase
    enum class Raza { Humano, Elfo, Enano, Maldito }
    enum class Clase { Brujo, Mago, Guerrero }
    enum class EstadoVital{Anciano, Joven, Adulto}

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

    fun getId(): String{
        return id
    }
    fun getNombre(): String {
        return nombre
    }
    fun getRaza(): Raza {
        return raza
    }
    fun getClase(): Clase {
        return clase
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
    fun setExperienciaN(experienciaN: Int) {
        experiencia = experienciaN
    }
    fun getNivel(): Int {
        return nivel
    }
    fun setNivel(nivelN: Int) {
        nivel = nivelN
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
    fun setDefensa(defensaN: Int) {
        defensa = defensaN
    }
    fun getSuerte(): Int {
        return suerte
    }
    fun setSuerte(suerteN: Int) {
        suerte = suerteN
    }
    fun getMonedero(): Int {
        return monedero
    }
    fun setMonedero(monederoN: Int) {
        monedero = monederoN
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

    fun pelea(monstruo: Monstruo, habilidad: String): String {
        var result="¡PERDISTE!"
        var vidaMonstruo = monstruo.getSalud()
        var expGanada =
            monstruo.getSalud() // La experiencia ganada es igual a la salud inicial del monstruo
        var vidaPersonaje = salud
        var contador = false

        while (vidaMonstruo > 0 && vidaPersonaje > 0) {
            // Preguntar al usuario si desea activar la habilidad
            println("¿Deseas activar la habilidad del personaje? (Sí/No)")
            val respuesta = habilidad

            if ((respuesta == "si") && !contador) {
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
                }

                // Monstruo ataca al personaje
                vidaPersonaje -= ataqueMonstruo
                println("${monstruo.getNombre()} ataca a ${nombre}. Salud de ${nombre}: ${vidaPersonaje}")
            }
        }
        if(vidaPersonaje<=0){
            salud=0
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

    fun entrenar(tiempoDeEntrenamiento: Int): String {
        val factorExperienciaPorHora = 5
        val experienciaGanada = tiempoDeEntrenamiento * factorExperienciaPorHora

        setExperiencia(experienciaGanada)

        return "$nombre ha entrenado durante $tiempoDeEntrenamiento segundos y ha ganado $experienciaGanada de experiencia."
    }

    fun realizarMision(tipoMision: String, dificultad: String): String{
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
            return "$nombre ha completado la misión de $tipoMision ($dificultad) con éxito y gana $experienciaFinal de experiencia."
        } else {
            return "$nombre ha fracasado en la misión de $tipoMision ($dificultad) y no recibe ninguna recompensa."
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

    fun comunicacion(mensaje:String): String{
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
                                if(mensaje == "¿Tienes algo equipado?"){
                                    if (arma != null || proteccion != null) {
                                        val equipamiento = mutableListOf<String>()
                                        if (arma != null) {
                                            equipamiento.add(arma!!.getNombre().name)
                                        }
                                        if (proteccion != null) {
                                            equipamiento.add(proteccion!!.getNombre().name)
                                        }
                                        respuesta="Tengo equipado: ${equipamiento.joinToString(", ")}"
                                    } else {
                                        respuesta="Ligero como una pluma."
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
                                if(mensaje == "¿Tienes algo equipado?"){
                                    if (arma != null || proteccion != null) {
                                        respuesta="No llevo nada, agente, se lo juro."
                                    } else {
                                        respuesta="Regístrame si quieres."
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
                                if(mensaje == "¿Tienes algo equipado?"){
                                    respuesta="A ti que te importa nini!"
                                }
                                else
                                    respuesta="En mis tiempos esto no pasaba"
            }
        }
        when(raza){
            Raza.Elfo-> return(cifrado(respuesta, 1))
            Raza.Enano-> return(respuesta.uppercase())
            Raza.Maldito-> return(cifrado(respuesta, 1))
            else -> return(respuesta)
        }
    }

   /* fun equipar(articulo: Articulo) {
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
    }*/

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
            Articulo.TipoArticulo.ORO -> {
                monedero += articulo.getPrecio()
                Toast.makeText(context, "Has cargado tu monedero, ahora tienes $monedero.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Este articulo no tiene uso.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun toString(): String {
        return "Personaje: Nombre: $nombre, Nivel: $nivel, Salud: $salud, Ataque: $ataque, Defensa: $defensa, Suerte: $suerte, Raza: $raza, Clase: $clase, Estado Vital: $estadoVital "
    }
}

class Articulo( var id: Int, private var nombre: Nombre, private var peso: Int) :Serializable {

    private var idUser: String =""
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

    fun getIdUser(): String {
        return idUser
    }
    fun setIdUser(idUserNuevo: String) {
        idUser = idUserNuevo
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


object GlobalVariables {
    var id: String = ""
    var personaje: Personaje? = null
}


