package checkAIR.Airly;

class Measurements {
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

    public void setAirQualityIndex(double airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public double getPm1() {
        return pm1;
    }

    public void setPm1(double pm1) {
        this.pm1 = pm1;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(double pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
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
