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


    public Double getAirQualityIndex() {
        return airQualityIndex;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public Double getPm1() {
        return pm1;
    }

    public Double getPm10() {
        return pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public Double getPollutionLevel() {
        return pollutionLevel;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public Double getWindSpeed() {
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
