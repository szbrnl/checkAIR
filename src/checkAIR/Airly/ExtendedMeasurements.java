package checkAIR.Airly;

import java.util.Arrays;

class ExtendedMeasurements {
    private Measurements currentMeasurements;

    private DatedMeasurements[] forecast = new DatedMeasurements[] {};

    private DatedMeasurements[] history = new DatedMeasurements[] {};

    public Measurements getCurrentMeasurements() {
        return currentMeasurements;
    }

    public DatedMeasurements[] getForecast() {
        return forecast;
    }

    public DatedMeasurements[] getHistory() {
        return history;
    }

    //    public double getCurrentAirQualityIndex() {
//        return currentMeasurements.getAirQualityIndex();
//    }
//
//    public double getCurrentHumidity() {
//        return currentMeasurements.getHumidity();
//    }
//
//    public String getCurrentMeasurementTime() {
//        return currentMeasurements.getMeasurementTime();
//    }
//
//    public double getCurrentPm1() {
//        return currentMeasurements.getPm1();
//    }
//
//    public double getCurrentPm10() {
//        return currentMeasurements.getPm10();
//    }
//
//    public double getCurrentPm25() {
//        return currentMeasurements.getPm25();
//    }
//
//    public double getCurrentPollutionLevel() {
//        return currentMeasurements.getPollutionLevel();
//    }
//
//    public double getCurrentPressure() {
//        return currentMeasurements.getPressure();
//    }
//
//    public double getCurrentTemperature() {
//        return currentMeasurements.getTemperature();
//    }
//
//    public double getCurrentWindDirection() {
//        return currentMeasurements.getWindDirection();
//    }
//
//    public double getCurrentWindSpeed() {
//        return currentMeasurements.getWindSpeed();
//    }


    @Override
    public String toString() {
        return
                "currentMeasurements=" + currentMeasurements +
                        "\nforecast=" + Arrays.toString(forecast) +
                        "\nhistory=" + Arrays.toString(history) +
                        "\n";
    }
}
