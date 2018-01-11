package checkAIR.Airly;

public class NearestSensorMeasurements
{
    private Address address;
    private double airQualityIndex;
    private double distance;
    private int id;

    private Location location;

    private String measurementTime;
    private String name;

    private double pm10;
    private double pm25;
    private double pollutionLevel;

    private String vendor;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(double airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public double getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(int pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return
                "address=" + address +
                        "\nairQualityIndex=" + airQualityIndex +
                        "\ndistance=" + distance +
                        "\nid=" + id +
                        "\nlocation=" + location +
                        "\nmeasurementTime='" + measurementTime + '\'' +
                        "\nname='" + name + '\'' +
                        "\npm10=" + pm10 +
                        "\npm25=" + pm25 +
                        "\npollutionLevel=" + pollutionLevel +
                        "\nvendor='" + vendor + '\'' +
                        "\n";
    }
}
