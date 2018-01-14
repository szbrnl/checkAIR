package checkAIR.airly;

import static java.lang.Double.NaN;

public class Measurements {

    public Measurements() {
        airQualityIndex = NaN;
        humidity = NaN;
        pm1 = NaN;
        pm10 = NaN;
        pm25 = NaN;
        pollutionLevel = NaN;
        pressure = NaN;
        temperature = NaN;
        windSpeed = NaN;
        windDirection = NaN;
    }

    private double airQualityIndex;

    private double humidity;

    private String measurementTime;

    private double pm1;

    private double pm10;

    private double pm25;

    private double pollutionLevel;

    private double pressure;

    private double temperature;

    private double windDirection;

    private double windSpeed;


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
