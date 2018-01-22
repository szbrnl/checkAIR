package checkAIR.airly;

public enum MeasurementType {
    Pm10, Pm25, Temperature, Pressure, Humidity, AirQualityIndex;

    public String getName() {
        return this.name();
    }

    public String getUnit() {

        switch (this) {
            case Pm10:
            case Pm25:
                return "µg/m3";

            case Temperature:
                return "\u2103";

            case Pressure:
                return "hPa";

            case Humidity:
                return "%";

            case AirQualityIndex:
                return "";

            default:
                return "";
        }
    }

    public MeasurementQualityIndex getQualityIndex(Double value) {
        switch (this) {
            case Pm10:
                if(value <=60) return MeasurementQualityIndex.Good;
                if(value <=140) return MeasurementQualityIndex.Moderate;
                return MeasurementQualityIndex.Bad;
            case Pm25:
                if(value <=36) return MeasurementQualityIndex.Good;
                if(value <=84) return MeasurementQualityIndex.Moderate;
                return MeasurementQualityIndex.Bad;

            default:
                return MeasurementQualityIndex.NoIndex;
        }
    }
}
