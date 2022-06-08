package com.boam.cities.nearby;

import com.boam.cities.city.City;
import com.boam.cities.city.CityDao;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class NearbyCitiesCsvExporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NearbyCitiesCsvExporter.class);
    private NearbyCityFinder nearbyCityFinder;
    private CityDao cityDao;

    public NearbyCitiesCsvExporter(NearbyCityFinder nearbyCityFinder, CityDao cityDao) {
        this.nearbyCityFinder = nearbyCityFinder;
        this.cityDao = cityDao;
    }

    public File exportNearbyCitiesForAll(int numberOfNearbyCities) {
        List<City> allCities = cityDao.findAllCities();
        String csvFileName = "nearby-cities.csv";
        LOGGER.info("export started...");
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));
            writer.writeNext(header(numberOfNearbyCities));

            allCities.forEach(city -> {
                List<City> nearbyCities =
                        nearbyCityFinder.findNearbyCitiesFor(city.name, city.stateId, numberOfNearbyCities);
                String[] line = toCsvLine(city, nearbyCities);
                writer.writeNext(line);
            });

            writer.close();

            LOGGER.info("export finished");
            return new File(csvFileName);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private String[] header(int numberOfNearbyCities) {
        List<String> header = new ArrayList<>();
        header.add("name");
        for (int i = 1; i <= numberOfNearbyCities; i++) {
            header.add("nearby city " + i);
        }
        return header.toArray(new String[numberOfNearbyCities + 1]);
    }

    private String[] toCsvLine(City city, List<City> nearbyCities) {
        List<String> csvLine = new ArrayList<>();
        csvLine.add(city.toString());
        csvLine.addAll(nearbyCities.stream().parallel().map(City::toString).toList());
        return csvLine.toArray(new String[nearbyCities.size() + 1]);
    }


}
