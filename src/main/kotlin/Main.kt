import kotlin.random.Random

class JuegoAhorcado(palabraSecreta: String, private val intentosMaximos: Int) {
  var palabraSecreta = palabraSecreta.toLowerCase()
  var palabraDescubierta = CharArray(palabraSecreta.length) { '_' }
  val letrasIncorrectas = mutableListOf<Char>()

 fun jugar(letra: Char): Boolean {
  var acierto = false

  if (letra in palabraSecreta) {
   acierto = true
   actualizarPalabraDescubierta(letra)
  } else {
   letrasIncorrectas.add(letra)
  }

  return acierto
 }

 fun obtenerPalabraDescubierta(): String {
  return String(palabraDescubierta)
 }

 fun obtenerLetrasIncorrectas(): String {
  return letrasIncorrectas.joinToString(" ")
 }

 fun obtenerIntentosRestantes(): Int {
  return intentosMaximos - letrasIncorrectas.size
 }

 fun juegoTerminado(): Boolean {
  return palabraSecreta == obtenerPalabraDescubierta() || obtenerIntentosRestantes() <= 0
 }

  fun actualizarPalabraDescubierta(letra: Char) {
  for (i in palabraSecreta.indices) {
   if (palabraSecreta[i] == letra) {
    palabraDescubierta[i] = letra
   }
  }
 }

 fun obtenerPalabraSecreta(): String {
  return palabraSecreta
 }
}



fun main() {
 val rm = ReproductorMidi("pugnodollari.mid")
 println("Cargando juego....")
 Thread.sleep(20000)
 DibujoAhorcado.dibujar(1)
 val frutas = arrayOf("Sandía", "Melón", "Naranja", "Uva", "Plátano", "Fresa", "Papaya", "Tomate")
 val palabra = Random.nextInt(frutas.size)
 val juegoAhorcado = JuegoAhorcado(frutas[palabra].toLowerCase(), 7)

 Thread.sleep(20000)


 println("Cargando juego...:")
 println("Adivina el nombre de la fruta!")


 while (!juegoAhorcado.juegoTerminado()) {
  println("Palabra: ${juegoAhorcado.obtenerPalabraDescubierta()}")
  println("Letras incorrectas: ${juegoAhorcado.obtenerLetrasIncorrectas()}")
  println("Intentos restantes: ${juegoAhorcado.obtenerIntentosRestantes()}")

  val letra = solicitarLetra()

  if (juegoAhorcado.jugar(letra)) {
   println("¡Correcto!")
  } else {
   DibujoAhorcado.dibujar(7 - juegoAhorcado.obtenerIntentosRestantes() + 1)
   println("Incorrecto. ¡Inténtalo de nuevo!")
  }
 }

 if (juegoAhorcado.obtenerIntentosRestantes() > 0) {
  println("¡Ganaste! La palabra es: ${juegoAhorcado.obtenerPalabraSecreta()}")
 } else {
  println("¡Perdiste! La palabra era: ${juegoAhorcado.obtenerPalabraSecreta()}")
  DibujoAhorcado.dibujar(7)
 }


 rm.cerrar()
}

fun solicitarLetra(): Char {
 var letra: Char = ' '
 do {
  print("Ingresa una letra: ")
  val input = readLine()
  letra = input?.firstOrNull()?.toLowerCase() ?: continue
 } while (!letra.isLetter())

 return letra
}




