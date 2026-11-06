package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("--- Centre de contrôle : Début de la séquence ---")
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.async {
        try{
            repeat(5){ i ->
                println("Étape ${i + 1} : vérification...")
                delay(1000)
            }
        }catch(e: CancellationException){
            println(">>> Capteur : Anomalie détectée ! Annulation du lancement !")
        } finally {
            println("--- Fin de mission ---")
        }
    }
    delay(2500)
    scope.cancel()
    job.join()

}