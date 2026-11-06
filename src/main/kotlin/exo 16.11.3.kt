package org.example
import kotlinx.coroutines.*

suspend fun recupererDonneesCamera(): String{
    println("Début caméra")
    delay(2000L)
    println("fin caméra (après 2s)")
    return "Image Infrarouge "
}
suspend fun recupererDonneesSpectro(): String{
    println("Début spectro")
    delay(2000L)
    println("fin spectro (après 2s)")
    return " Analyse Spectrale"
}

fun main() = runBlocking{
    println("main : Début de la mission ---")
    val res2 = async{
        recupererDonneesSpectro()
    }
    val res = async {
        recupererDonneesCamera()
    }
    println("main : Récupération des données en cours...")
    res.join()
    res2.join()
    println("Résultat : " + "${res.await()} + ${res2.await()}" )
    println("main : Mission terminée")
}