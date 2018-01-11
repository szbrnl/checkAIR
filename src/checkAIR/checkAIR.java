package checkAIR;


import checkAIR.Airly.AirlyClient;
import checkAIR.Airly.NearestSensorMeasurements;
import checkAIR.Airly.SensorDetailedMeasurements;

public class checkAIR {

    public static void main(String[] args) throws Exception {

        AirlyClient airlyClient = new AirlyClient("f87f3655b35f40f28e7cd00bd097f860");
        NearestSensorMeasurements nearestSensorMeasurements = airlyClient.getNearestSensorMeasurement(50.06201, 19.94098);

        System.out.println(nearestSensorMeasurements.toString());

    }

}
