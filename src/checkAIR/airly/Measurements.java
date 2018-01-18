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

    public void setAirQualityIndex(double airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public void setPm1(double pm1) {
        this.pm1 = pm1;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public void setPollutionLevel(double pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

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
