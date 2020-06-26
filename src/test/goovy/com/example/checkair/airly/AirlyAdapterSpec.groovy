package com.example.checkair.airly

import com.example.checkair.airly.api.CurrentMeasurements
import com.example.checkair.airly.api.MeasurementValue
import spock.lang.Specification

import java.time.ZonedDateTime

class AirlyAdapterSpec extends Specification {

    Airly client
    AirlyAdapter airly

    def setup() {
        client = Mock(Airly)
        airly = new AirlyAdapter(client)
    }

    def 'Should return current measurements'() {
        when:
        def result = airly.getCurrentMeasurements()

        then:
        with(result, Measurements) {
            pm10 == 1.0.doubleValue()
            pm25 == 2.0.doubleValue()
            temperature == 33.1.doubleValue()
            humidity == 9.9.doubleValue()
            !pm1
            !pressure
        }

        and:
        client.getNearestMeasurements(2, 3) >> fromValues([
                "PM10"       : 1.0,
                "PM25"       : 2.0,
                "TEMPERATURE": 33.1,
                "HUMIDITY"   : 9.9
        ])
    }

    def "Should cache measurements until tillDateTime"() {

    }

    private static def fromValues(Map<String, Double> values) {
        new com.example.checkair.airly.api.AirlyResult(
                new CurrentMeasurements(ZonedDateTime.now(), ZonedDateTime.now(),
                        values.collect { new MeasurementValue(it.key, it.value) },
                        []
                )
        )
    }
}
