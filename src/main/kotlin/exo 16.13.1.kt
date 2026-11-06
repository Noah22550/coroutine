package org.example
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val batterie = MutableStateFlow<Double>(100.0)

    val sonde =  launch {
        val valeurs = listOf(100.0, 80.0, 50.0, 20.0, 5.0)
        for (x in valeurs) {
            delay(1000)
            batterie.value = x
            println(" --- Sonde [Ares-1] : Nouveau niveau -> $x %")
        }

    }

   val consoleControle = launch {

       batterie.collect { x ->  println(">>> Console : État reçu -> $x %")}
   }
    delay(10000)
    if (batterie.value < 20) {
        println("!!! ALERTE CRITIQUE : Énergie faible sur Ares-1 !!!")
    }
    sonde.cancel()
    consoleControle.cancel()
    println("--- Système arrêté ---")
}



