package com.boam.cities.city;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvCityDaoTest {

    @Test
    public void TestFindAllCities() {
        CsvCityDao csvCityDao = new CsvCityDao();
        List<City> allCities = csvCityDao.findAllCities();

        assertThat(allCities.size()).isEqualTo(30409);
    }
}