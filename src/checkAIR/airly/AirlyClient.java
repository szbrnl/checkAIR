package checkAIR.airly;

import java.io.IOException;
import java.util.*;

public class AirlyClient {
    private final String apiKey;

    private Measurements currentMeasurements;

    private List<DatedMeasurements> history;

    //TODO w zależości od zapytania pobierze sobie co chce? (bez niepotrzebnego parsowania history)
    //TODO uwzględnić brak sensora w danej okolicy (może jakiś dodatkowy konstruktor z odległością?)
    public AirlyClient(String apiKey, double latitude, double longitude) throws IOException {
        this.apiKey = apiKey;

        AirlyJsonParser parser = new AirlyJsonParser(apiKey, latitude, longitude);
        currentMeasurements = parser.getCurrentMeasurements();
        history = parser.getHistory();
    }

    public AirlyClient(String apiKey, int sensorId) throws IOException {
        this.apiKey = apiKey;

        AirlyJsonParser parser = new AirlyJsonParser(apiKey, sensorId);
        currentMeasurements = parser.getCurrentMeasurements();
        history = parser.getHistory();
    }

    public MeasurementQualityIndex getMeasurementQualityIndex(MeasurementType measurementType) throws NotProvidedException {
        switch (measurementType) {
            case Pm10:
                return measurementType.getQualityIndex(currentMeasurements.getPm10());
            case Pm25:
                return measurementType.getQualityIndex(currentMeasurements.getPm25());
            default:
                return MeasurementQualityIndex.NoIndex;
        }
    }

    //TODO rozróżnić opcję z historią?


    public Integer getCurrentAirQualityIndex() {

        return Optional.ofNullable(currentMeasurements.getAirQualityIndex())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentHumidity() {

        return Optional.ofNullable(currentMeasurements.getHumidity())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPm10() {

        return Optional.ofNullable(currentMeasurements.getPm10())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPm25() {

        return Optional.ofNullable(currentMeasurements.getPm25())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPressure() {

        return Optional.ofNullable(currentMeasurements.getPressure())
                .map(Math::round)
                .map(x -> x.intValue() / 100)
                .orElse(null);
    }

    public Double getCurrentTemperature() {

        return Optional.ofNullable(currentMeasurements.getTemperature())
                .map(x -> Math.round(x * 10))
                .map(x -> x.doubleValue() / 10)
                .orElse(null);

    }






    @Override
    public String toString() {
        return currentMeasurements.toString();
    }
}
