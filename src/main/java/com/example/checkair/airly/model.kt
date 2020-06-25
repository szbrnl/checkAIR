package com.example.checkair.airly

data class Measurements(
        val pm1: Double?,
        val pm25: Double?,
        val pm10: Double?,
        val pressure: Double?,
        val humidity: Double?,
        val temperature: Double?
)