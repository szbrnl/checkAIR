package com.example.checkair

import com.example.checkair.airly.AirlyAdapter
import com.example.checkair.airly.AirlyClientV2
import com.example.checkair.airly.apk
import com.example.checkair.console.Color
import com.example.checkair.console.ColumnarView
import com.example.checkair.console.PrettyConsole


object KotlinCheckAIR {
    @JvmStatic
    fun main(args: Array<String>) {
        AirlyAdapter(AirlyClientV2(apk)).getCurrentMeasurements()

        println(PrettyConsole(showCurrent(), "a", "b"))
    }

    fun showCurrent(): ColumnarView {
        val view = CurrentMeasurementsView()
        view.setAsciiArtNumber(44, Color.TextYellow)

        addMeasurementToView("Pm25", 2.0, view)
        addMeasurementToView("Pm10", 1.0, view)
        addMeasurementToView("Humidity", .1, view)
        addMeasurementToView("Pressure", .1, view)
        addMeasurementToView("Temperature", .2, view)

        return view
    }


    private fun addMeasurementToView(type: String, value: Double, view: CurrentMeasurementsView) {
        view.addMeasurement(type,
                value,
                Color.TextRed
        )
    }

}