package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.DatedMeasurements;
import checkAIR.airly.MeasurementType;
import checkAIR.console.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CheckAIR {

    //TODO asciiarty
    //TODO argumenty -> wybrów opcji -pm10 -pm25 -humidity itd
    //TODO tryby działania jakoś ładnie opakować
    public static void main(String[] args) throws Exception {

        AirlyClient airlyClient;
        try {
            airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860", 50.06201, 19.94098);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        CurrentMeasurementsView view = new CurrentMeasurementsView();

//        TODO index wyświetlany w ascii arcie z lewej strony?
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



        HistoryView view1 = new HistoryView();

        List<DatedMeasurements> history = airlyClient.getHistory();


        //TODO reformat
        for (DatedMeasurements x : history) {
            List<MeasurementType> measurementTypes = new LinkedList<>();

            List<Integer> values = new LinkedList<>();

            measurementTypes.add(MeasurementType.Pm10);
            measurementTypes.add(MeasurementType.Pm25);

            values.add(x.getPm10());
            values.add(x.getPm25());

            List<Color> colors = new LinkedList<>();

            colors.add(converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm10, x.getPm10().doubleValue())));
            colors.add(converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm25, x.getPm25().doubleValue())));

            view1.addMeasurement(x.getFromDateTime(), x.getTillDateTime(), measurementTypes, values, colors);
        }


        PrettyConsole prettyConsole = new PrettyConsole(view1, "Stan powietrza w " + 50.06201 + ", " + 50.06201, "");
        System.out.println(prettyConsole.toString());

    }


}
