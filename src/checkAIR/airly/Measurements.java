package checkAIR.airly;

import com.google.gson.JsonObject;

import static java.lang.Double.NaN;

public class Measurements {

    //TODO zamienić pola na final
    //TODO usunąć settery
    public Measurements(JsonObject jsonObject) {
//        airQualityIndex = NaN;
//        humidity = NaN;
//        pm1 = NaN;
//        pm10 = NaN;
//        pm25 = NaN;
//        pollutionLevel = NaN;
//        pressure = NaN;
//        temperature = NaN;
//        windSpeed = NaN;
//        windDirection = NaN;


        //TODO dodać trycatche które w przypadku braku takiego czegoś dają wartość NULL/NaN
        airQualityIndex = jsonObject.get("airQualityIndex").getAsDouble();
        humidity = jsonObject.get("humidity").getAsDouble();
        //TODO trycatch bo tego zazwyczaj nie ma (a właściwie nigdy)
        // measurements.setMeasurementTime(currentMeasurementsJsonObject.get("measurementTime").getAsString());
        pollutionLevel = jsonObject.get("pollutionLevel").getAsDouble();
        pressure =jsonObject.get("pressure").getAsDouble();
        temperature = jsonObject.get("temperature").getAsDouble();
        pm1 = jsonObject.get("pm1").getAsDouble();
        pm10 = jsonObject.get("pm10").getAsDouble();
        pm25 = jsonObject.get("pm25").getAsDouble();
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
