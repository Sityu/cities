package com.boam.cities.nearby;

import com.boam.cities.city.City;
import com.boam.cities.city.CsvCityDao;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NearbyCitiesFinderTest {

    @Test
    public void testFindNearbyCities() {
        CsvCityDao csvCityDao = new CsvCityDao();
        NearbyCityFinder nearbyCityFinder = new NearbyCityFinder(csvCityDao);
        List<City> nearbyCities = nearbyCityFinder.findNearbyCitiesFor("New York", "NY", 5);

        assertThat(nearbyCities).extracting("name")
                .containsExactly("Brooklyn",
                        "Hoboken",
                        "Manhattan",
                        "Union City",
                        "Queens")
                .hasSize(5);
    }
}
