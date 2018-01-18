package checkAIR.airly;

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


    @Override
    public String toString() {
        return
                "currentMeasurements=" + currentMeasurements +
                        "\nforecast=" + Arrays.toString(forecast) +
                        "\nhistory=" + Arrays.toString(history) +
                        "\n";
    }
}
