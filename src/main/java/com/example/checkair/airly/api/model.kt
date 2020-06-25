package com.example.checkair.airly.api

import java.time.ZonedDateTime

data class AirlyResult(
        val current: CurrentMeasurements
)

data class CurrentMeasurements(
        val fromDateTime: ZonedDateTime,
        val tillDateTime: ZonedDateTime,
        val values: List<MeasurementValue>,
        val indexes: List<AirQualityIndex>
)

data class AirQualityIndex(
        val name: String,
        val value: Double,
        val level: String,
        val description: String,
        val advice: String,
        val color: String
)

data class MeasurementValue(
        val name: String,
        val value: Double
)
