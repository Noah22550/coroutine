package org.example

import kotlinx.coroutines.delay

interface SourceDonnees {
  fun recupererDonnees(): String
}

// 2. Implémentation Concrète n°1
class SourceSqlite  : SourceDonnees {
    override fun recupererDonnees(): String {
        return "Données tableau de bord issues de la base SQLite (Mode Hors-ligne)"
    }
}
// 3. Implémentation Concrète n°2
class SourceApiRest  : SourceDonnees {
    override fun recupererDonnees(): String {
        return "Données tableau de bord récupérées via l’API REST (Serveur distant)"
    }
}
class GestionnaireTableauDeBord {
    lateinit var sourceDonnees :  SourceDonnees
    fun afficherTableauDeBord(){
        if (::sourceDonnees.isInitialized){
        println("Chargement de l'écran...")
        println("Affichage : ${sourceDonnees.recupererDonnees()}")
        }else {
            println("Attention : Aucune source de données n'a été configurée !")
        }
    }
}

fun main(){
    val gestionnaire = GestionnaireTableauDeBord()
    gestionnaire.afficherTableauDeBord()
    gestionnaire.sourceDonnees = SourceSqlite()
    gestionnaire.afficherTableauDeBord()
    gestionnaire.sourceDonnees = SourceApiRest()
    gestionnaire.afficherTableauDeBord()
}