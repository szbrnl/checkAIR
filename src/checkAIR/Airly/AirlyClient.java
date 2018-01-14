package checkAIR.Airly;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirlyClient {
    private final String apiKey;

    public AirlyClient(String apiKey) {
        this.apiKey = apiKey;
    }


    //TODO zapamiętywanie wszystkiego o co pytamy w tym obiekcie, żeby nie musieć wielokrotnie parsować?
    //TODO rozróżnić opcję z historią?


    public CurrentAndHistoricalMeasurements getNearestSensorMeasurement(double latitude, double longitude) throws IOException {

        //TODO IO exception?

        int id = getNearestSensorId(latitude, longitude);
        CurrentAndHistoricalMeasurements measurements = getSensorDetailedMeasurements(id);

        return measurements;
    }

    private int getNearestSensorId(double latitude, double longitude) throws IOException {
        String Url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" +
                latitude + "&longitude=" +
                longitude + "&maxDistance=1000";

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(retrieveJson(Url)).getAsJsonObject();
        int id = root.get("id").getAsInt();

        return id;
    }


    public CurrentAndHistoricalMeasurements getSensorDetailedMeasurements(int sensorID) throws IOException {

        //TODO IO exception?

        String Url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" +
                sensorID +
                "&historyHours=5&historyResolutionHours=5";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CurrentAndHistoricalMeasurements nearestSensorMeasurement = gson.fromJson(retrieveJson(Url), CurrentAndHistoricalMeasurements.class);

        return nearestSensorMeasurement;
    }

    private JsonReader retrieveJson(String Url) throws IOException {

        HttpURLConnection request;

        try {
            URL url = new URL(Url);
            request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("apikey", apiKey);
            request.connect();
        }
        catch(IOException ex) {
            throw new IOException("Connection error");
        }

        try{
            InputStream inputStream = (InputStream) request.getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JsonReader jsonReader = new JsonReader(inputStreamReader);

            return jsonReader;
        }
        catch(IOException ex) {
            switch(request.getResponseCode()) {
                case 400:
                    throw new IOException("Input validation error (code 400)");
                case 403:
                    throw new IOException("Your API key is invalid (code 403");
                case 404:
                    throw new IOException("Data not found (code 404)");

            }
            throw new IOException("There was a problem with your request");
        }

    }
}
