package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(10){i ->
                println("Réparation panneau solaire ${i + 1}/10")
                delay(1000)
            }
        } catch (e: CancellationException) {
            println(">>> ALERTE : Annulation d'urgence !" + e.localizedMessage)
        } finally {
            println("Nettoyage des ressources...")
        }
    }
    delay(2500)
    job.cancel()
    job.join()
    println("--- Mission terminée ---")
}
