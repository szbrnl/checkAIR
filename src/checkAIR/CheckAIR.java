package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.MeasurementQualityIndex;
import checkAIR.airly.MeasurementType;
import checkAIR.airly.NotProvidedException;
import checkAIR.console.*;

import java.io.IOException;
import java.util.Optional;


public class CheckAIR {

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

        System.out.println(airlyClient.toString());

        MeasurementQualityIndexToColorConverter converter = new MeasurementQualityIndexToColorConverter();


        Frame namesFrame = new Frame(true);
        namesFrame.add("pm25");
        namesFrame.add("pm10");
        namesFrame.add("humidity");
        namesFrame.add("temperature");
        namesFrame.add("pressure");

        Frame valueFrames = new Frame(false);
System.out.println(airlyClient.getCurrentTemperature());

        valueFrames.add(Optional.ofNullable(airlyClient.getCurrentPm25()).map(x-> x.toString()).orElse("No information"), converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm25)));
        valueFrames.add(Optional.ofNullable(airlyClient.getCurrentPm10()).map(x-> x.toString()).orElse("No information"), converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pm10)));
        valueFrames.add(Optional.ofNullable(airlyClient.getCurrentHumidity()).map(x-> x.toString()).orElse("No information"), converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Humidity)));
        valueFrames.add(Optional.ofNullable(airlyClient.getCurrentTemperature()).map(x-> x.toString()).orElse("No information"), converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Temperature)));
        valueFrames.add(Optional.ofNullable(airlyClient.getCurrentPressure()).map(x-> x.toString()).orElse("No information"), converter.convert(airlyClient.getMeasurementQualityIndex(MeasurementType.Pressure)));


        IConsoleView mainView = new CurrentMeasurementsView();
        //mainView.addFrame(namesFrame, 1,1);
        //mainView.addFrame(valueFrames,14,14);


        PrettyConsole cons = new PrettyConsole(mainView, "Stan powietrza w " + 50.06201 + ", " + 50.06201, "");

        cons.addFrame(namesFrame);
        cons.addFrame(valueFrames);

        System.out.println(cons.toString());
//
//        try {
//            System.out.println(airlyClient.getCurrentHumidity());
//        } catch (NotProvidedException ex) {
//            System.out.println("nie dali");
//        }
//
//
//        try {
//            System.out.println(airlyClient.getCurrentAirQualityIndex());
//        } catch (NotProvidedException ex) {
//            System.out.println("nie dali");
//        }


    }


}
