package org.example
import kotlinx.coroutines.*


fun main() = runBlocking {
    println("--- Application Météo ---")
    println("Action : L'utilisateur clique sur le bouton 'Actualiser'")
   val res =  launch {
       println("Mise à jour de l'écran avec : "+ apiMeteo())
        }
    println("L'interface reste réactive : l'utilisateur peut toujours cliquer ailleurs.")
    println("... Chargement des données météo en cours ...")
    res.join()
    println("--- Fin de l'application ---")

}
suspend fun apiMeteo(): String {
    delay(30000L)
   return "37 ° CANICULLEEEE !!!"
}