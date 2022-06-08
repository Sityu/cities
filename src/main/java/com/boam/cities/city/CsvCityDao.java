package com.boam.cities.city;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

@Repository
public class CsvCityDao implements CityDao {
    private static List<City> allCities = new ArrayList<>();

    public List<City> findAllCities() {
        return allCities;
    }

    private List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        try {
            CSVReader reader = getCsvReader();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                cities.add(City.create(getName(nextLine), getStateId(nextLine), getLat(nextLine), getLon(nextLine)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }

    private double getLon(String[] line) {
        return parseDouble(line[7]);
    }

    private double getLat(String[] line) {
        return parseDouble(line[6]);
    }
    private String getName(String[] line) {
        return line[0];
    }

    private String getStateId(String[] line) {
        return line[2];
    }
    private CSVReader getCsvReader() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                requireNonNull(getClass().getClassLoader().getResourceAsStream("uscities.csv")), UTF_8));
        reader.skip(1);
        return reader;
    }

    @SuppressWarnings("unused")
    @PostConstruct
    public void init() {
        allCities = getAllCities();
    }
}
