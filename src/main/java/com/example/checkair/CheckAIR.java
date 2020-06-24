package com.example.checkair;

import com.example.checkair.airly.AirlyClient;
import com.example.checkair.airly.DatedMeasurements;
import com.example.checkair.airly.MeasurementType;
import checkAIR.console.*;
import com.example.checkair.console.Color;
import com.example.checkair.console.IConsoleView;
import com.example.checkair.console.PrettyConsole;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class CheckAIR {

    public static void main(String[] args) {
        try {
            CheckAIR checkAIR = new CheckAIR(args);
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    private PrettyConsole prettyConsole;
    private AirlyClient airlyClient;


    public CheckAIR(String[] args) throws IllegalArgumentException, IOException{
        OptionsParser optionsParser;
        try {
            optionsParser = new OptionsParser(args);
        } catch (ParseException | IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

        if (optionsParser.isHelpSelected()) {
            optionsParser.showHelp();
            return;
        }

        String apiKey;

        if (optionsParser.isApikeyGiven()) {
            apiKey = optionsParser.getApiKey();
        } else {
            try {
                apiKey = getApiKeyFromEnv();
            }
            catch (IllegalArgumentException ex)
            {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }

        String title;
        if (optionsParser.isCoordinatesModeSelected()) {

            if(optionsParser.isMaxDistanceGiven()) {
                try {
                    airlyClient = new AirlyClient(apiKey, optionsParser.getLatitude(), optionsParser.getLongitude(), optionsParser.getMaxDistance());
                } catch (IOException ex) {
                    throw new IOException(ex.getMessage());
                }
            }
            else {
                try {
                    airlyClient = new AirlyClient(apiKey, optionsParser.getLatitude(), optionsParser.getLongitude());
                } catch (IOException ex) {
                    throw new IOException(ex.getMessage());
                }
            }

            title = " " + Math.round(optionsParser.getLatitude() * 100) / 100.0 + ", " + Math.round(optionsParser.getLongitude() * 100) / 100.0;

        } else {
            try {
                airlyClient = new AirlyClient(apiKey, optionsParser.getSensorId());
            } catch (IOException ex) {
                throw new IOException(ex.getMessage());
            }
            catch(IllegalArgumentException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }

            title = " sensor " + optionsParser.getSensorId();
        }

        IConsoleView view;

        if (optionsParser.isHistoryModeSelected()) {
            view = showHistory(airlyClient);
        } else {
            view = showCurrent(airlyClient);

        }

        String secondTitle = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(Calendar.getInstance().getTime());
        prettyConsole = new PrettyConsole(view, "Air quality for" + title, secondTitle);
        System.out.println(prettyConsole.toString());

    }

    private IConsoleView showHistory(AirlyClient airlyClient) {

        HistoryView view = new HistoryView();
        List<DatedMeasurements> historyMeasurements = airlyClient.getHistory();

        for (DatedMeasurements measurement : historyMeasurements) {

            List<MeasurementType> measurementTypes = Arrays.asList(MeasurementType.Pm10, MeasurementType.Pm25);

            List<Integer> values = Arrays.asList(measurement.getPm10(), measurement.getPm25());

            addMeasurementToView(measurementTypes, values, measurement.getFromDateTime(), measurement.getTillDateTime(), view);

        }

        return view;
    }

    private IConsoleView showCurrent(AirlyClient airlyClient) {

        CurrentMeasurementsView view = new CurrentMeasurementsView();


        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();
        Color airQualityIndexColor = converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.AirQualityIndex));

        view.setAsciiArtNumber(airlyClient.getCurrentAirQualityIndex(), airQualityIndexColor );


        addMeasurementToView(MeasurementType.Pm25, airlyClient.getCurrentPm25(), view);

        addMeasurementToView(MeasurementType.Pm10, airlyClient.getCurrentPm10(), view);

        addMeasurementToView(MeasurementType.Humidity, airlyClient.getCurrentHumidity(), view);

        addMeasurementToView(MeasurementType.Pressure, airlyClient.getCurrentPressure(), view);

        addMeasurementToView(MeasurementType.Temperature, airlyClient.getCurrentTemperature(), view);


        return view;
    }

    private void addMeasurementToView(MeasurementType type, Integer value, CurrentMeasurementsView view) {

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        view.addMeasurement(type,
                value,
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(type))
        );

    }

    private void addMeasurementToView(MeasurementType type, Double value, CurrentMeasurementsView view) {

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        view.addMeasurement(type,
                value,
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(type))
        );

    }

    public void addMeasurementToView(List<MeasurementType> types, List<Integer> values, String fromDateTime, String tillDateTime, HistoryView view) {

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        List<Color> colors = new LinkedList<>();

        for (int i = 0; i < values.size() && i < values.size(); i++) {
            colors.add(
                    converter.convert(
                            airlyClient.getMeasurementQualityIndex(types.get(i), values.get(i).doubleValue())
                    )
            );
        }

        view.addMeasurement(fromDateTime, tillDateTime, types, values, colors);
    }


    private String getApiKeyFromEnv() throws IllegalArgumentException {

        String envVariable = System.getenv("API_KEY");

        if(envVariable == null)
            throw new IllegalArgumentException("Could not find API_KEY in env");

        return envVariable;
    }

}
