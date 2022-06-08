package com.boam.cities.city;

import com.opencsv.bean.CsvBindByPosition;

public class City {
    public String name;
    public String stateId;
    public double lat;
    public double lon;

    public static City create(String name, String stateId, double lat, double lon) {
        City newCity = new City();
        newCity.name = name;
        newCity.stateId = stateId;
        newCity.lat = lat;
        newCity.lon = lon;
        return newCity;
    }

    //in miles
    //btw copied and pasted from - https://dzone.com/articles/distance-calculation-using-3
    public double distanceFrom(City city) {
        double theta = lon - city.lon;
        double dist = Math.sin(deg2rad(lat))
                * Math.sin(deg2rad(city.lat))
                + Math.cos(deg2rad(lat))
                * Math.cos(deg2rad(city.lat))
                * Math.cos(deg2rad(theta));

        return rad2deg((Math.acos(dist))) * 60 * 1.1515;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public String toString() {
        return name + " (" + stateId + ")";
    }
}
