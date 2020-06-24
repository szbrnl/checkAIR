package checkAIR.airly;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class AirlyJsonParser {

    private final String apiKey;

    private int sensorId;

    private JsonObject rootObject;

    private final int defaultMaxSearchDistance = 1000;

    public AirlyJsonParser(String apiKey, double latitude, double longitude) throws IOException {
        this.apiKey = apiKey;
        getSensorId(latitude, longitude);

        getRootObject();

    }

    public AirlyJsonParser(String apiKey, double latitude, double longitude, int maxSearchDistance) throws IOException {
        this.apiKey = apiKey;
        getSensorId(latitude, longitude, maxSearchDistance);

        getRootObject();

    }

    public AirlyJsonParser(String apiKey, int sensorId) throws IOException {
        this.apiKey = apiKey;
        this.sensorId = sensorId;

        getRootObject();
    }

    private void getRootObject() throws IOException {
        String Url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" +
                sensorId +
                "&historyHours=5&historyResolutionHours=5";

        rootObject = retrieveJson(Url);

        if (rootObject.get("currentMeasurements").getAsJsonObject().size() == 0) {
            throw new IllegalArgumentException("Could not find sensor with given ID");
        }

    }

    private void getSensorId(double latitude, double longitude) throws IOException {

        getSensorId(latitude, longitude, defaultMaxSearchDistance);
    }

    private void getSensorId(double latitude, double longitude, int maxSearchDistance) throws IOException {
        String Url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" +
                latitude + "&longitude=" +
                longitude + "&maxDistance=" + maxSearchDistance;

        JsonObject sensorMeasurementsRoot = retrieveJson(Url);

        try {
            sensorId = sensorMeasurementsRoot.get("id").getAsInt();
        } catch (NullPointerException ex) {
            throw new IOException("Could not find sensor near specified area. Try increasing maxDistance");
        }
    }


    public Measurements getCurrentMeasurements() {
        return new Gson().fromJson(rootObject.get("currentMeasurements"), Measurements.class);
    }

    public List<DatedMeasurements> getHistory() {
        return Arrays.asList(new Gson().fromJson(rootObject.getAsJsonArray("history"), DatedMeasurements[].class));
    }

    private JsonObject retrieveJson(String Url) throws IOException {
        HttpURLConnection request;

        try {
            URL url = new URL(Url);
            request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("apikey", apiKey);
            request.connect();
        } catch (IOException ex) {
            throw new IOException("Connection error");
        }


        try (InputStream inputStream = (InputStream) request.getContent();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)
        ) {

            JsonReader jsonReader = new JsonReader(inputStreamReader);

            return new JsonParser().parse(jsonReader).getAsJsonObject();

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
}