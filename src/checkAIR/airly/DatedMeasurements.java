package checkAIR.airly;

import com.google.gson.JsonObject;

class DatedMeasurements {
    private String fromDateTime;

    private Measurements measurements;

    private String tillDateTime;


    @Override
    public String toString() {
        return
                "fromDateTime='" + fromDateTime + '\'' +
                        "\nmeasurements=" + measurements +
                        "\ntillDateTime='" + tillDateTime + '\'' +
                        "\n";
    }
}
