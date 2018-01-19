package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.MeasurementType;
import checkAIR.console.*;

import java.io.IOException;
import java.util.Optional;


public class CheckAIR {


    //TODO currentmeasurementsview dostaje rodzaj odczytu i jego wartosc, sam sobie robi 2 Frame. Później dodajemy caly view do console


    //TODO getCurrentAsMap -> można iterować po tych które są aktualnie dostępne
    //TODO argumenty -> wybrów opcji -pm10 -pm25 -humidity itd
    public static void main(String[] args) throws Exception {

        AirlyClient airlyClient;
        try {
            airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860", 50.06201, 19.94098);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        //System.out.println(airlyClient.toString());

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();

        CurrentMeasurementsView view = new CurrentMeasurementsView();

        view.addMeasurement(MeasurementType.Pm25,
                airlyClient.getCurrentPm25(),
                converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm25)));

        view.addMeasurement(MeasurementType.Pm10,
                airlyClient.getCurrentPm10(),
                converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm10)));

        view.addMeasurement(MeasurementType.Humidity,
                airlyClient.getCurrentHumidity(),
                converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Humidity)));

        view.addMeasurement(MeasurementType.Temperature,
                airlyClient.getCurrentTemperature(),
                converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Temperature)));

        view.addMeasurement(MeasurementType.Pressure,
                airlyClient.getCurrentPressure(),
                converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pressure)));


        PrettyConsole cons = new PrettyConsole(view, "Stan powietrza w " + 50.06201 + ", " + 50.06201, "");

        System.out.println(cons.toString());

    }


}
