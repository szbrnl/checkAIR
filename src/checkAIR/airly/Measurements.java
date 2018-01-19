package checkAIR.airly;

import com.google.gson.JsonObject;

import java.util.Optional;

import static java.lang.Double.NaN;

public class Measurements {

    //TODO zamienić na referencyjne z domyślnymi nullami
    public Measurements(JsonObject jsonObject) {

//        airQualityIndex = (jsonObject.has("airQualityIndex") ? jsonObject.get("airQualityIndex").getAsDouble() : NaN);
//
//        humidity = (jsonObject.has("humidity") ? jsonObject.get("humidity").getAsDouble() : null);
//
//        measurementTime = (jsonObject.has("measurementTime") ? jsonObject.get("measurementTime").getAsString() : null);
//
//        pm1 = (jsonObject.has("pm1") ? jsonObject.get("pm1").getAsDouble() : NaN);
//
//        pm10 = (jsonObject.has("pm10") ? jsonObject.get("pm10").getAsDouble() : NaN);
//
//        pm25 = (jsonObject.has("pm25") ? jsonObject.get("pm25").getAsDouble() : NaN);
//
//        pollutionLevel = (jsonObject.has("pollutionLevel") ? jsonObject.get("pollutionLevel").getAsDouble() : NaN);
//
//        pressure = (jsonObject.has("pressure") ? jsonObject.get("pressure").getAsDouble() : NaN);
//
//        temperature = (jsonObject.has("temperature") ? jsonObject.get("temperature").getAsDouble() : NaN);
//
//        windDirection = (jsonObject.has("windDirection") ? jsonObject.get("windDirection").getAsDouble() : null);
//
//        windSpeed = (jsonObject.has("windSpeed") ? jsonObject.get("windSpeed").getAsDouble() : NaN);
//
//        //humidity = Optional.ofNullable(jsonObject.get("humidity")).orElse(NaN).getAsDouble();

    }

    public Double airQualityIndex;

    public Double humidity;

    public String measurementTime;

    public double pm1;

    public double pm10;

    public double pm25;

    public double pollutionLevel;

    public double pressure;

    public double temperature;

    public Double windDirection;

    public double windSpeed;

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
