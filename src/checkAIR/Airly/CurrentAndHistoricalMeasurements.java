package checkAIR.Airly;

import java.util.Arrays;

public class CurrentAndHistoricalMeasurements {

    private Measurements currentMeasurements;

    private DatedMeasurements[] forecast = new DatedMeasurements[] {};

    private DatedMeasurements[] history = new DatedMeasurements[] {};

    @Override
    public String toString() {
        return
                "currentMeasurements=" + currentMeasurements +
                        "\nforecast=" + Arrays.toString(forecast) +
                        "\nhistory=" + Arrays.toString(history) +
                        "\n";
    }
}
