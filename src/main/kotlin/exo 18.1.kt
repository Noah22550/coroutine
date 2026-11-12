package org.example
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class LogDelegate<T>(private var valeur: T) {

  operator fun getValue(thisRef: Any?, propriete: KProperty<*>): T {
    return valeur
  }
  operator fun setValue(thisRef: Any?, propriete: KProperty<*>, nouvelleValeur: T) {
    valeur = nouvelleValeur
  }
}

class CapteurMoteur {
  var temperature: Double by Delegates.observable(0.0) { _, _, nouvValeur ->
    when {
        nouvValeur > 150 || nouvValeur < -20.0 -> {
            println("[ALERTE] Température invalide détectée : $nouvValeur°C. (Plage autorisée: -20 à 150)")
        }
        nouvValeur < 150 || nouvValeur > -20.0 -> {
            println("[CAPTEUR] Nouvelle température enregistrée : $nouvValeur°C")
        }
    }
  }
}

fun main() {
   val capteur = CapteurMoteur()
    capteur.temperature = 50.0
    capteur.temperature = 200.0
}