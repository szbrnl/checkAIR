package checkAIR.Airly;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public NearestSensorMeasurements getNearestSensorMeasurement(double latitude, double longitude) throws IOException {

        //TODO IO exception?

        String Url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" +
                latitude + "&longitude=" +
                longitude + "&maxDistance=1000";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        NearestSensorMeasurements measurements = gson.fromJson(retrieveJson(Url), NearestSensorMeasurements.class);

        return measurements;
    }

    public SensorDetailedMeasurements getSensorDetailedMeasurements(int sensorID) throws IOException {

        //TODO IO exception?

        String Url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" +
                sensorID +
                "&historyHours=5&historyResolutionHours=5";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        SensorDetailedMeasurements nearestSensorMeasurement = gson.fromJson(retrieveJson(Url), SensorDetailedMeasurements.class);

        return nearestSensorMeasurement;
    }
//TODO rozróżniać wyjątki (żeby wiedzieć czy można odwołać się do requesta żeby sprawdzić zwrócony kod)
    private JsonReader retrieveJson(String Url) throws IOException {
        try {
            URL url = new URL(Url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("apikey", apiKey);
            request.connect();

            System.out.println(request.getResponseCode());
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
