import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerialName

@Serializable
data class DonneesActuelles(
    @SerialName("temperature_2m") val temperature2m: Double,
    @SerialName("weather_code") val codeMeteo: Int
)


@Serializable
data class ReponseMeteo(
    val current: DonneesActuelles
)

fun main() = runBlocking{
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    val  MeteoStateFlow = MutableStateFlow<List<ReponseMeteo>>(emptyList())

    launch {
        println("Chargement...")
        val reponse: List<ReponseMeteo> = client.get("https://api.open-meteo.com/v1/forecast?latitude=48.514&longitude=-2.778&current=temperature_2m,weather_code").body()
        MeteoStateFlow.value = reponse
    }
    launch {
        MeteoStateFlow.collect { liste ->
            if (liste.isEmpty()) {
                println("UI : En attente...")
            } else {
                println("UI : Mise à jour affichée avec ${liste.size} éléments.")
                liste.take(5).forEach { println("- ${it.current}") }
            }
        }
    }
    delay(2000)
    client.close()
}


