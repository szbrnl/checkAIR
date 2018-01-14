package checkAIR;


import checkAIR.Airly.AirlyClient;
import checkAIR.Airly.CurrentAndHistoricalMeasurements;
import checkAIR.Console.Console;
import checkAIR.Console.ViewMode;

import java.io.IOException;

public class CheckAIR {

    public static void main(String[] args) throws Exception {
        try {
            AirlyClient airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860");
            CurrentAndHistoricalMeasurements nearestSensorMeasurements = airlyClient.getNearestSensorMeasurement(50.06201, 19.94098);

            System.out.println(nearestSensorMeasurements.toString());

            Console c = new Console(ViewMode.CurrentMeasurements);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
