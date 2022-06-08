package com.boam.cities.nearby;

import com.boam.cities.city.City;
import com.boam.cities.city.CityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearbyCityFinder {
    private final CityDao cityDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(NearbyCityFinder.class);

    public NearbyCityFinder(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public List<City> findNearbyCitiesFor(String name, String stateId, int numberOfNearbyCities) {
        List<City> allCities = cityDao.findAllCities();
        City city = allCities.stream()
                .filter(city1 -> city1.name.equals(name) && city1.stateId.equals(stateId))
                .findFirst().orElseThrow();

        LOGGER.info("Finding nearby cities for: " + name);

        return allCities.stream().parallel()
                .map(c -> new DistanceFromCity(city.distanceFrom(c), c))
                .sorted()
                .skip(1)
                .limit(numberOfNearbyCities)
                .map(d -> d.city)
                .toList();
    }

}
