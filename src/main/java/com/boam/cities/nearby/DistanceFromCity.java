package com.boam.cities.nearby;

import com.boam.cities.city.City;

public class DistanceFromCity implements Comparable<DistanceFromCity> {
    public double distance;
    public City city;

    public DistanceFromCity(double distance, City city) {
        this.distance = distance;
        this.city = city;
    }

    @Override
    public int compareTo(DistanceFromCity other) {
        return Double.compare(distance, other.distance);
    }
}
