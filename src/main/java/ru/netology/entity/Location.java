package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int building;

    public Location(String city, Country country, String street, int building) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() { return country; }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return building == location.building && city == location.city
                && country == location.country && street == location.street;
    }

    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;

        result = prime * result + city.hashCode();
                result = prime * result + country.hashCode();
                result = prime * result + street.hashCode();
                result = prime * result + building;

        return result;
    }
}
