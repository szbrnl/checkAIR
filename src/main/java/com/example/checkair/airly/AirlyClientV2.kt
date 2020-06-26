package com.example.checkair.airly

import com.example.checkair.airly.api.AirlyResult
import com.example.checkair.airly.api.ZonedDateTimeDeserializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.runBlocking
import java.time.ZonedDateTime

interface Airly {
    fun getNearestMeasurements(latitude: Double, longitude: Double): AirlyResult
}

class AirlyClientV2(
        private val apiKey: String
) : Airly {

    // Change to bean
    private val httpClient: HttpClient = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer {
                registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeDeserializer())
                serializeNulls()
                disableHtmlEscaping()
            }
        }
    }

    private fun currentMeasurementsUrl(lat: Double, lon: Double, maxDistance: Int = -1): String = "https://airapi.airly.eu/v2/measurements/nearest?lat=$lat&lng=$lon&maxDistanceKM=$maxDistance&maxResults=3"

    override fun getNearestMeasurements(latitude: Double, longitude: Double): AirlyResult {
        return runBlocking {
            val message = httpClient.get<AirlyResult>(currentMeasurementsUrl(latitude, longitude)) {
                this.header("apikey", apiKey)
            }

            message
        }
    }
}