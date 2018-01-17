package checkAIR;

import checkAIR.airly.AirlyClient;
import checkAIR.airly.NotProvidedException;
import checkAIR.console.PrettyConsole;
import checkAIR.console.ViewMode;

import java.io.IOException;

public class CheckAIR {

    //TODO a może odpytywanie o odczyty byłoby realizowane przez airlyclient? client.getcurrentcośtam, client.history.srutututu


    public static void main(String[] args) throws Exception {

        AirlyClient airlyClient;

        try {
            airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860", 50.06201, 19.94098);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println(airlyClient.toString());

        PrettyConsole cons = new PrettyConsole(ViewMode.CurrentMeasurements, "Stan powietrza w "+50.06201+ ", "+50.06201, "");
        System.out.println(cons.toString());

        try {
            System.out.println(airlyClient.getCurrentAirQualityIndex());
        }
        catch (NotProvidedException ex) {
            System.out.println("nie dali");
        }
//
//        try {
//            System.out.println(airlyClient.getCurrentWindDirection());
//        }
//        catch (NotProvidedException ex) {
//            System.out.println("nie dali");
//        }
//
//        try {
//            System.out.println(airlyClient.getCurrentAirQualityIndex());
//        }
//        catch (NotProvidedException ex) {
//            System.out.println("nie dali");
//        }


    }


}
