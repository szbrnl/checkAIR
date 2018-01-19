package checkAIR.airly;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.isNaN;

public class AirlyClient {
    private final String apiKey;

    private Measurements currentMeasurements;

    private List<DatedMeasurements> history;

    //TODO w zależości od zapytania pobierze sobie co chce?
    //TODO uwzględnić brak sensora w danej okolicy (może jakiś dodatkowy konstruktor z odległością?)
    public AirlyClient(String apiKey, double latitude, double longitude) throws IOException {
        this.apiKey = apiKey;

        getNearestSensorMeasurement(latitude, longitude);
    }

    public AirlyClient(String apiKey, int sensorId) throws IOException {
        this.apiKey = apiKey;

        getSensorDetailedMeasurements(sensorId);
    }


    //TODO rozróżnić opcję z historią?


    //TODO konwersje na ludzką formę
    public int getCurrentAirQualityIndex() throws NotProvidedException {
        double airQualityIndex = currentMeasurements.airQualityIndex;
        if (isNaN(airQualityIndex))
            throw new NotProvidedException();
        return (int) (Math.round(airQualityIndex));
    }

    public int getCurrentHumidity() throws NotProvidedException {

        return Math.round(
                Optional.ofNullable(currentMeasurements.humidity)
                        .orElseThrow(NotProvidedException::new)
                        .intValue());
//
//        double humidity = currentMeasurements.humidity;
//        if (isNaN(humidity))
//            throw new NotProvidedException();
//        return (int)(Math.round(humidity));
    }

    public String getCurrentMeasurementTime() throws NotProvidedException {
        String measurementTime = currentMeasurements.measurementTime;
        if (measurementTime == null)
            throw new NotProvidedException();
        return measurementTime;
    }

    public int getCurrentPm1() throws NotProvidedException {
        double pm1 = currentMeasurements.pm1;
        if (isNaN(pm1))
            throw new NotProvidedException();
        return (int) (Math.round(pm1));
    }

    public int getCurrentPm10() throws NotProvidedException {
        double pm10 = currentMeasurements.pm10;
        if (isNaN(pm10))
            throw new NotProvidedException();
        return (int) (Math.round(pm10));
    }

    public int getCurrentPm25() throws NotProvidedException {
        double pm25 = currentMeasurements.pm25;
        if (isNaN(pm25))
            throw new NotProvidedException();
        return (int) (Math.round(pm25));
    }

    //TODO konwersja? na co?
    public double getCurrentPollutionLevel() throws NotProvidedException {
        double pollutionLevel = currentMeasurements.pollutionLevel;
        if (isNaN(pollutionLevel))
            throw new NotProvidedException();
        return (int) pollutionLevel;
    }

    public int getCurrentPressure() throws NotProvidedException {
        double currentPressure = currentMeasurements.pressure;
        if (isNaN(currentPressure))
            throw new NotProvidedException();
        return (int) (Math.round(currentPressure));
    }

    public int getCurrentTemperature() throws NotProvidedException {
        double currentTemperature = currentMeasurements.temperature;
        if (isNaN(currentTemperature))
            throw new NotProvidedException();
        return (int) (Math.round(currentTemperature));
    }

    public double getCurrentWindDirection() throws NotProvidedException {

        return Math.round(
                Optional.ofNullable(currentMeasurements.windDirection)
                        .orElseThrow(NotProvidedException::new)
                        .doubleValue());

//        double windDirection = currentMeasurements.windDirection;
//        if (isNaN(windDirection))
//            throw new NotProvidedException();
//        return windDirection;
    }

    public double getCurrentWindSpeed() throws NotProvidedException {
        double windSpeed = currentMeasurements.windSpeed;
        if (isNaN(windSpeed))
            throw new NotProvidedException();
        return Math.round(windSpeed * 10) / 10;
    }


    private void getNearestSensorMeasurement(double latitude, double longitude) throws IOException {

        //TODO IO exception?
        int id = getNearestSensorId(latitude, longitude);
        getSensorDetailedMeasurements(id);

    }

    //TODO trycatch
    private int getNearestSensorId(double latitude, double longitude) throws IOException {
        String Url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" +
                latitude + "&longitude=" +
                longitude + "&maxDistance=1000";

        JsonObject root = new JsonParser().parse(retrieveJson(Url)).getAsJsonObject();
        return root.get("id").getAsInt();
    }

    //TODO jeśli zwróci puste to nie ma takiego sensora
    public void getSensorDetailedMeasurements(int sensorID) throws IOException {
        //TODO IO exception?
        String Url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" +
                sensorID +
                "&historyHours=5&historyResolutionHours=5";


        JsonObject root = new JsonParser().parse(retrieveJson(Url)).getAsJsonObject();

        JsonObject currentMeasurementsJsonObject = root.get("currentMeasurements").getAsJsonObject();
        JsonArray historyJsonArray = root.getAsJsonArray("history");

        //Parsing current measurements
        //this.currentMeasurements = new Measurements(currentMeasurementsJsonObject);


        //TODO czy to działa ???????
        Gson gson = new Gson();
        currentMeasurements = gson.fromJson(currentMeasurementsJsonObject, Measurements.class);


        this.history = new LinkedList<>();

        //Parsing the history array
        historyJsonArray.forEach(x -> history.add(new DatedMeasurements(x.getAsJsonObject())));
    }

    private JsonReader retrieveJson(String Url) throws IOException {
        HttpURLConnection request;

        try {
            URL url = new URL(Url);
            request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("apikey", apiKey);
            request.connect();
        } catch (IOException ex) {
            throw new IOException("Connection error");
        }

        try {
            InputStream inputStream = (InputStream) request.getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JsonReader jsonReader = new JsonReader(inputStreamReader);

            return jsonReader;
        } catch (IOException ex) {
            switch (request.getResponseCode()) {
                case 400:
                    throw new IOException("Input validation error (code 400)");
                case 403:
                    throw new IOException("Your API key is invalid (code 403)");
                case 404:
                    throw new IOException("Data not found (code 404)");

            }
            throw new IOException("There was a problem with your request");
        }

    }

    @Override
    public String toString() {
        return currentMeasurements.toString();
    }
}
