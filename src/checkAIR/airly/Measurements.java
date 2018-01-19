package checkAIR.airly;

import com.google.gson.JsonObject;

import java.util.Optional;

import static java.lang.Double.NaN;

public class Measurements {

    private Double airQualityIndex;

    private Double humidity;

    private String measurementTime;

    private Double pm1;

    private Double pm10;

    private Double pm25;

    private Double pollutionLevel;

    private Double pressure;

    private Double temperature;

    private Double windDirection;

    private Double windSpeed;

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
