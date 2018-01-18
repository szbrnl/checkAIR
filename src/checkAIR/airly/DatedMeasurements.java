package checkAIR.airly;

import com.google.gson.JsonObject;

class DatedMeasurements {
    private String fromDateTime;

    private Measurements measurements;

    private String tillDateTime;


    public DatedMeasurements(JsonObject jsonObject) {
        //TODO Parsing the json

    }

    @Override
    public String toString() {
        return
                "fromDateTime='" + fromDateTime + '\'' +
                        "\nmeasurements=" + measurements +
                        "\ntillDateTime='" + tillDateTime + '\'' +
                        "\n";
    }
}
