package org.example
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

data class EtatDrone (val altitude : Int, val statuts : String)
fun main() = runBlocking {

    val monitorage = MutableStateFlow<EtatDrone>(EtatDrone(0, "Au sol"))
    val job = launch {
        delay(1000)
        println("--- Pilote : Décollage effectué ---")
        val new = EtatDrone(50, "En vol")
        monitorage.value = new
        delay(1000)
        val new2  = EtatDrone(0, "Livrée")
        monitorage.value = new2
        println("--- Pilote : Colis déposé ---")
    }

    val controle = launch {
        monitorage.collect { x -> println(">>> Moniteur : Altitude= ${x.altitude} m | Statut= ${x.statuts}") }
    }
    delay(10000)
    job.cancel()
    controle.cancel()

    println("--- Mission terminée ---")
}