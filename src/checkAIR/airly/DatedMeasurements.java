package checkAIR.airly;

import com.google.gson.JsonObject;

class DatedMeasurements {
    private String fromDateTime;

    private Measurements measurements;

    private String tillDateTime;


    public DatedMeasurements() {
        //TODO trycatch

//        fromDateTime = jsonObject.get("fromDateTime").getAsString();
//
//        tillDateTime = jsonObject.get("tillDateTime").getAsString();
//
//        measurements = new Measurements(jsonObject.getAsJsonObject("measurements"));
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
