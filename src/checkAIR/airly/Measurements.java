package checkAIR.airly;

import com.google.gson.JsonObject;

import java.util.Optional;

import static java.lang.Double.NaN;

public class Measurements {

    //TODO zamienić pola na final
    //TODO usunąć settery
    public Measurements(JsonObject jsonObject) {

        airQualityIndex = (jsonObject.has("airQualityIndex") ? jsonObject.get("airQualityIndex").getAsDouble() : NaN);

        humidity = (jsonObject.has("humidity") ? jsonObject.get("humidity").getAsDouble() : NaN);

        measurementTime = (jsonObject.has("measurementTime") ? jsonObject.get("measurementTime").getAsString() : null);

        pm1 = (jsonObject.has("pm1") ? jsonObject.get("pm1").getAsDouble() : NaN);

        pm10 = (jsonObject.has("pm10") ? jsonObject.get("pm10").getAsDouble() : NaN);

        pm25 = (jsonObject.has("pm25") ? jsonObject.get("pm25").getAsDouble() : NaN);

        pollutionLevel = (jsonObject.has("pollutionLevel") ? jsonObject.get("pollutionLevel").getAsDouble() : NaN);

        pressure = (jsonObject.has("pressure") ? jsonObject.get("pressure").getAsDouble() : NaN);

        temperature = (jsonObject.has("temperature") ? jsonObject.get("temperature").getAsDouble() : NaN);

        windDirection = (jsonObject.has("windDirection") ? jsonObject.get("windDirection").getAsDouble() : NaN);

        windSpeed = (jsonObject.has("windSpeed") ? jsonObject.get("windSpeed").getAsDouble() : NaN);

        //humidity = Optional.ofNullable(jsonObject.get("humidity")).orElse(NaN).getAsDouble();

    }

    private final double airQualityIndex;

    private final double humidity;

    private final String measurementTime;

    private final double pm1;

    private final double pm10;

    private final double pm25;

    private final double pollutionLevel;

    private final double pressure;

    private final double temperature;

    private final double windDirection;

    private final double windSpeed;


    public double getAirQualityIndex() {
        return airQualityIndex;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public double getPm1() {
        return pm1;
    }

    public double getPm10() {
        return pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public double getPollutionLevel() {
        return pollutionLevel;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return
                "airQualityIndex=" + airQualityIndex +
                        "\nhumidity=" + humidity +
                        "\nmeasurementTime='" + measurementTime + '\'' +
                        "\npm1=" + pm1 +
                        "\npm10=" + pm10 +
                        "\npm25=" + pm25 +
                        "\npollutionLevel=" + pollutionLevel +
                        "\npressure=" + pressure +
                        "\ntemperature=" + temperature +
                        "\nwindDirection=" + windDirection +
                        "\nwindSpeed=" + windSpeed +
                        "\n";
    }
}
