package checkAIR.Airly;

public class Address {
    private String country;
    private String locality;
    private String route;
    private String streetNumber;

    public String getCountry() {
        return country;
    }

    public String getLocality() {
        return locality;
    }

    public String getRoute() {
        return route;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public String toString() {
        return
                "country='" + country + '\'' +
                        "\nlocality='" + locality + '\'' +
                        "\nroute='" + route + '\'' +
                        "\nstreetNumber='" + streetNumber + '\'' +
                        "\n";
    }
}
