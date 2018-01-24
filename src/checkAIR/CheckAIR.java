package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.DatedMeasurements;
import checkAIR.airly.MeasurementType;
import checkAIR.console.Color;
import checkAIR.console.IConsoleView;
import checkAIR.console.PrettyConsole;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CheckAIR {

    //TODO index wyświetlany w ascii arcie z lewej strony?
    //TODO asciiarty
    //TODO get from environment
    //TODO tubydziegodzina

    public static void main(String[] args) {
        CheckAIR checkAIR = new CheckAIR(args);
    }

    private PrettyConsole prettyConsole;
    private AirlyClient airlyClient;


    public CheckAIR(String[] args) {
        OptionsParser optionsParser;
        try {
            optionsParser = new OptionsParser(args);
        } catch (ParseException | IllegalArgumentException ex) {
            System.out.print(ex.getMessage());
            return;
        }

        if (optionsParser.isHelpSelected()) {
            optionsParser.showHelp();
            return;
        }

        String apiKey;

        if (optionsParser.isApikeyGiven()) {
            apiKey = optionsParser.getApiKey();
        } else {
            apiKey = getApiKeyFromEnv();
        }

        String title;
        if (optionsParser.isCoordinatesModeSelected()) {

            if(optionsParser.isMaxDistanceGiven()) {
                try {
                    airlyClient = new AirlyClient(apiKey, optionsParser.getLatitude(), optionsParser.getLongitude(), optionsParser.getMaxDistance());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
            else {
                try {
                    airlyClient = new AirlyClient(apiKey, optionsParser.getLatitude(), optionsParser.getLongitude());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }

            title = Math.round(optionsParser.getLatitude() * 100) / 100.0 + ", " + Math.round(optionsParser.getLongitude() * 100) / 100.0;
        } else {
            try {
                airlyClient = new AirlyClient(apiKey, optionsParser.getSensorId());
            } catch (IOException | IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            title = "sensora " + optionsParser.getSensorId();
        }

        IConsoleView view;

        if (optionsParser.isHistoryModeSelected()) {
            view = showHistory(airlyClient);
        } else {
            view = showCurrent(airlyClient);

        }
        prettyConsole = new PrettyConsole(view, "Stan powietrza dla " + title, "tubedziegodzina");
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


        addMeasurementToView(MeasurementType.AirQualityIndex, airlyClient.getCurrentAirQualityIndex(), view);

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


    private String getApiKeyFromEnv() {
        return "f87f3655b35f40f28e7cd00bd097f860";
    }

}
