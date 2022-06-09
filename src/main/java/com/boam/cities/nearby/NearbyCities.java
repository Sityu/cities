package com.boam.cities.nearby;

import com.boam.cities.city.City;
import com.boam.cities.nearby.export.ExportableToCsv;

import java.util.ArrayList;
import java.util.List;

public class NearbyCities implements ExportableToCsv {

    public City city;
    public List<City> nearbyCities;

    public NearbyCities(City city, List<City> nearbyCities) {
        this.city = city;
        this.nearbyCities = nearbyCities;
    }

    public String[] csvLine() {
        List<String> csvLine = new ArrayList<>();
        csvLine.add(city.toString());
        csvLine.addAll(nearbyCities.stream().parallel().map(City::toString).toList());
        return csvLine.toArray(new String[nearbyCities.size() + 1]);
    }


}
