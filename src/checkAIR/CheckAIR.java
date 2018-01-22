package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.DatedMeasurements;
import checkAIR.airly.MeasurementType;
import checkAIR.console.*;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;


public class CheckAIR {

    //TODO asciiarty
    //TODO argumenty -> wybrów opcji -pm10 -pm25 -humidity itd
    //TODO tryby działania jakoś ładnie opakować
    public static void main(String[] args){

        CheckAIR checkAIR = new CheckAIR(args);
    }


    PrettyConsole prettyConsole;

    public CheckAIR(String[] args) {
        OptionsParser optionsParser;
        try {
            optionsParser = new OptionsParser(args);
        }
        catch(ParseException ex) {
            System.out.print(ex.getMessage());
            return;
        }
        catch(IllegalArgumentException ex)
        {
            System.out.print(ex.getMessage());
            return;
        }

        if(optionsParser.helpSelected()) {
            optionsParser.showHelp();
            return;
        }

        String apiKey;
        AirlyClient airlyClient;
        if(optionsParser.apiKeyGiven()) {
            apiKey = optionsParser.getApiKey();
        }
        else
        {
            //from env
            apiKey = "f87f3655b35f40f28e7cd00bd097f860";
        }

        String title;
        if(optionsParser.coordinatesModeSelected()) {
            try {
                airlyClient = new AirlyClient(apiKey, optionsParser.getLatitude(), optionsParser.getLongitude());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            title = Math.round(optionsParser.getLatitude()*100)/100.0 + ", " + Math.round(optionsParser.getLongitude()*100)/100.0;
        }
        else {
            try {
                airlyClient = new AirlyClient(apiKey, optionsParser.getSensorId());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            title = "sensora " + optionsParser.getSensorId();
        }

        IConsoleView view;

        if(optionsParser.historyModeSelected()) {
            view = showHistory(airlyClient);
        }
        else {
            view = showCurrent(airlyClient);

        }
        prettyConsole = new PrettyConsole(view, "Stan powietrza dla " + title , "tubedziegodzina");
        System.out.println(prettyConsole.toString());

    }

    private IConsoleView showHistory(AirlyClient airlyClient) {
        HistoryView view = new HistoryView();

        List<DatedMeasurements> historyMeasurements = airlyClient.getHistory();
        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        //TODO reformat
        for (DatedMeasurements x : historyMeasurements ) {
            List<MeasurementType> measurementTypes = new LinkedList<>();

            List<Integer> values = new LinkedList<>();

            measurementTypes.add(MeasurementType.Pm10);
            measurementTypes.add(MeasurementType.Pm25);

            values.add(x.getPm10());
            values.add(x.getPm25());

            List<Color> colors = new LinkedList<>();

            colors.add(converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm10, x.getPm10().doubleValue())));
            colors.add(converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm25, x.getPm25().doubleValue())));

            view.addMeasurement(x.getFromDateTime(), x.getTillDateTime(), measurementTypes, values, colors);
        }


        return view;
    }

    private IConsoleView showCurrent(AirlyClient airlyClient) {
        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        CurrentMeasurementsView view = new CurrentMeasurementsView();

//        TODO index wyświetlany w ascii arcie z lewej strony?
        //TODO uwzględnić nulle
        view.addMeasurement(MeasurementType.AirQualityIndex,
                airlyClient.getCurrentAirQualityIndex(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.AirQualityIndex)));

        view.addMeasurement(MeasurementType.Pm25,
                airlyClient.getCurrentPm25(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.Pm25)));

        view.addMeasurement(MeasurementType.Pm10,
                airlyClient.getCurrentPm10(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.Pm10)));

        view.addMeasurement(MeasurementType.Humidity,
                airlyClient.getCurrentHumidity(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.Humidity)));

        view.addMeasurement(MeasurementType.Temperature,
                airlyClient.getCurrentTemperature(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.Temperature)));

        view.addMeasurement(MeasurementType.Pressure,
                airlyClient.getCurrentPressure(),
                converter.convert(airlyClient.getCurrentMeasurementQualityIndex(MeasurementType.Pressure)));


        return view;
    }


}
