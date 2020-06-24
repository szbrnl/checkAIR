package com.example.checkair.airly;

public class Measurements {

    private Double airQualityIndex;

    private Double humidity;

    private Double pm10;

    private Double pm25;

    private Double pressure;

    private Double temperature;


    public Double getAirQualityIndex() {
        return airQualityIndex;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getPm10() {
        return pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return
                "airQualityIndex=" + airQualityIndex +
                        "\npm10=" + pm10 +
                        "\npm25=" + pm25 +
                        "\npressure=" + pressure +
                        "\nhumidity=" + humidity +
                        "\ntemperature=" + temperature +
                        "\n";
    }
}
