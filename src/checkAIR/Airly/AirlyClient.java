package checkAIR.Airly;


import checkAIR.Console.ViewMode;
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

import static java.lang.Double.NaN;

public class AirlyClient {
    private final String apiKey;

    private final ExtendedMeasurements measurements;

    //TODO w zależości od zapytania pobierze sobie co chce?

    public AirlyClient(String apiKey, double latitude, double longitude) throws IOException {
        this.apiKey = apiKey;

        this.measurements = getNearestSensorMeasurement(latitude,longitude);
    }

    public AirlyClient(String apiKey, int sensorId) throws IOException {
        this.apiKey = apiKey;

        this.measurements = getSensorDetailedMeasurements(sensorId);
    }

    //TODO zapamiętywanie wszystkiego o co pytamy w tym obiekcie, żeby nie musieć wielokrotnie parsować?
    //TODO rozróżnić opcję z historią?


    public double getCurrentAirQualityIndex() throws NotProvidedException {
        return airQualityIndex;
    }

    public double getCurrentHumidity() throws NotProvidedException {
        return humidity;
    }

    public String getCurrentMeasurementTime() throws NotProvidedException {
        String measurementTime  = measurements.getCurrentMeasurements().getMeasurementTime();
        if(measurementTime == null)
            throw new NotProvidedException();
        return measurementTime;

        //TODO konwersja
    }

    public double getCurrentPm1() throws NotProvidedException{
        Double pm1 = measurements.getCurrentMeasurements().getPm1();
        if(pm1 == null)
            throw new NotProvidedException();
        return pm1.doubleValue();
    }

    public double getCurrentPm10() throws NotProvidedException {
        Double pm10 = measurements.getCurrentMeasurements().getPm10();
        if(pm10 == null)
            throw new NotProvidedException();
        return pm10.doubleValue();
    }

    public double getCurrentPm25() throws NotProvidedException {
        Double pm25 = measurements.getCurrentMeasurements().getPm25();
        if(pm25 == null)
            throw new NotProvidedException();
        return pm25.doubleValue();
    }

    public double getCurrentPollutionLevel() throws NotProvidedException {
        Double pollutionLevel = measurements.getCurrentMeasurements().getPollutionLevel();
        if(pollutionLevel == null)
            throw new NotProvidedException();
        double aa = NaN;
        return pollutionLevel.doubleValue();
    }

    public double getCurrentPressure() throws NotProvidedException {
        return pressure;
    }

    public double getCurrentTemperature() throws NotProvidedException {
        return temperature;
    }

    public double getCurrentWindDirection() throws NotProvidedException {
        return windDirection;
    }

    public double getCurrentWindSpeed() throws NotProvidedException {
        return windSpeed;
    }



    private ExtendedMeasurements getNearestSensorMeasurement(double latitude, double longitude) throws IOException {

        //TODO IO exception?

        int id = getNearestSensorId(latitude, longitude);
        return getSensorDetailedMeasurements(id);

    }

    private int getNearestSensorId(double latitude, double longitude) throws IOException {
        String Url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" +
                latitude + "&longitude=" +
                longitude + "&maxDistance=1000";

        JsonObject root = new JsonParser().parse(retrieveJson(Url)).getAsJsonObject();
        return root.get("id").getAsInt();
    }

    public ExtendedMeasurements getSensorDetailedMeasurements(int sensorID) throws IOException {
        //TODO IO exception?
        String Url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" +
                sensorID +
                "&historyHours=5&historyResolutionHours=5";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ExtendedMeasurements nearestSensorMeasurement = gson.fromJson(retrieveJson(Url), ExtendedMeasurements.class);
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
                    throw new IOException("Your API key is invalid (code 403)");
                case 404:
                    throw new IOException("Data not found (code 404)");

            }
            throw new IOException("There was a problem with your request");
        }

    }

    @Override
    public String toString() {
        return measurements.getCurrentMeasurements().toString();
    }
}
