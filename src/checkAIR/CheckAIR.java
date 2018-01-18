package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.NotProvidedException;
import checkAIR.console.*;

import java.io.IOException;

public class CheckAIR {

    public static void main(String[] args) throws Exception {

        AirlyClient airlyClient;

        try {
            airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860", 50.06201, 19.94098);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println(airlyClient.toString());


        Frame namesFrame = new Frame(true);
        namesFrame.add("pm25");
        namesFrame.add("pm10");
        namesFrame.add("pm1");

        Frame valueFrames = new Frame(true);
        valueFrames.add("odczyt1");
        valueFrames.add("odczyt234234");
        valueFrames.add("odczytsdieur348");



        IConsoleView mainView = new CurrentMeasurementsView();
        //mainView.addFrame(namesFrame, 1,1);
        //mainView.addFrame(valueFrames,14,14);





        PrettyConsole cons = new PrettyConsole(mainView, "Stan powietrza w "+50.06201+ ", "+50.06201, "");
        System.out.println(cons.toString());

        try {
            System.out.println(airlyClient.getCurrentHumidity());
        }
        catch (NotProvidedException ex) {
            System.out.println("nie dali");
        }

        try {
            System.out.println(airlyClient.getCurrentWindDirection());
        }
        catch (NotProvidedException ex) {
            System.out.println("nie dali");
        }

        try {
            System.out.println(airlyClient.getCurrentAirQualityIndex());
        }
        catch (NotProvidedException ex) {
            System.out.println("nie dali");
        }


    }


}
