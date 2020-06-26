package com.example.checkair.airly

open class AirlyAdapter(
        private val client: Airly
) {

    fun getCurrentMeasurements(): Measurements {
        val airlyMeasurements = client.getNearestMeasurements(latitude = 50.062006, longitude  = 19.940984).current.values.associate {
            it.name to it.value
        }

        return Measurements(
                pm1 = airlyMeasurements["PM1"],
                pm10 = airlyMeasurements["PM10"],
                pm25 = airlyMeasurements["PM25"],
                humidity = airlyMeasurements["HUMIDITY"],
                pressure = airlyMeasurements["PRESSURE"],
                temperature = airlyMeasurements["TEMPERATURE"]
        )
    }
}