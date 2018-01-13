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
//TODO rozróżniać wyjątki (żeby wiedzieć czy można odwołać się do requesta żeby sprawdzić zwrócony kod)
    private JsonReader retrieveJson(String Url) throws IOException {
        try {
            URL url = new URL(Url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("apikey", apiKey);
            request.connect();

            //System.out.println(request.getResponseCode());
            InputStream inputStream = (InputStream) request.getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JsonReader jsonReader = new JsonReader(inputStreamReader);

            return jsonReader;
        }
        catch(IOException ex) {
            throw new IOException(ex.getMessage());
        }

    }
}
