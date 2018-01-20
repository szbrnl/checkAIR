package checkAIR.airly;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DatedMeasurements {
    private String fromDateTime;

    private Measurements measurements;

    private String tillDateTime;

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getTillDateTime() {
        return tillDateTime;
    }

    public final Measurements getMeasurements() {
        return measurements;
    }


    public Integer getPm10() {

        return Optional.ofNullable(measurements.getPm10())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
    }

    public Integer getPm25() {

        return Optional.ofNullable(measurements.getPm25())
                .map(Math::round)
                .map(Long::intValue)
                .orElse(null);
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
