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
        namesFrame.add("humidity");

        Frame valueFrames = new Frame(false);
        valueFrames.add(airlyClient.getCurrentPm25());
        valueFrames.add(airlyClient.getCurrentPm10());
        valueFrames.add(airlyClient.getCurrentPm1());
        valueFrames.add(airlyClient.getCurrentHumidity());


        IConsoleView mainView = new CurrentMeasurementsView();
        //mainView.addFrame(namesFrame, 1,1);
        //mainView.addFrame(valueFrames,14,14);





        PrettyConsole cons = new PrettyConsole(mainView, "Stan powietrza w "+50.06201+ ", "+50.06201, "");

        cons.addFrame(namesFrame);
        cons.addFrame(valueFrames);

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
