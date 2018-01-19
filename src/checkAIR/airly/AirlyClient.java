package checkAIR.airly;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import javax.naming.InsufficientResourcesException;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.NotActiveException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static java.lang.Double.isNaN;

public class AirlyClient {
    private final String apiKey;

    private Measurements currentMeasurements;

    private List<DatedMeasurements> history;

    //TODO get(MeasurementType)  ????
    //TODO dodać klasę airlyParser ktora zajmuje się tylko parsowaniem i zwraca Measurements?
    //TODO dodać stopień zagrożenia (jakiś enum?)
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

    public MeasurementQualityIndex getMeasurementQualityIndex(MeasurementType measurementType) throws NotProvidedException {
        switch (measurementType) {
            case Pm10:
                return measurementType.getQualityIndex(currentMeasurements.getPm10());
            case Pm25:
                return measurementType.getQualityIndex(currentMeasurements.getPm25());
            default:
                return MeasurementQualityIndex.NoIndex;
        }
    }


    //TODO poczytać o mapach czy można ich tak użyć (bez getHashcode)
    public Map<String, String> getCurrentAsMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("pm10", Double.toString(currentMeasurements.getPm10()));
        map.put("pm25", Double.toString(currentMeasurements.getPm25()));

        return map;
    }

    //TODO rozróżnić opcję z historią?


    //TODO konwersje na ludzką formę
    public Integer getCurrentAirQualityIndex() {

        return Optional.ofNullable(currentMeasurements.getAirQualityIndex())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentHumidity() {

        return Optional.ofNullable(currentMeasurements.getHumidity())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPm10() {

        return Optional.ofNullable(currentMeasurements.getPm10())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPm25() {

        return Optional.ofNullable(currentMeasurements.getPm25())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getCurrentPressure() {

        return Optional.ofNullable(currentMeasurements.getPressure())
                .map(Math::round)
                .map(x-> x.intValue()/100)
                .orElse(null);
    }

    public Double getCurrentTemperature() {

        return Optional.ofNullable(currentMeasurements.getTemperature())
                .map(x-> Math.round(x*10))
                .map(x-> x.doubleValue()/10)
                .orElse(null);

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

        Gson gson = new Gson();

        JsonObject root = new JsonParser().parse(retrieveJson(Url)).getAsJsonObject();

        JsonObject currentMeasurementsJsonObject = root.get("currentMeasurements").getAsJsonObject();
        JsonArray historyJsonArray = root.getAsJsonArray("history");


        //Parsing current measurements
        this.currentMeasurements = gson.fromJson(currentMeasurementsJsonObject, Measurements.class);

        this.history = Arrays.asList(gson.fromJson(historyJsonArray, DatedMeasurements[].class));

        for (int i = 0; i < 3; i++) {
            System.out.println(history.get(i).toString());
        }

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
