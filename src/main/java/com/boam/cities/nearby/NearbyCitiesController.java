package com.boam.cities.nearby;

import com.boam.cities.city.City;
import com.boam.cities.city.CsvCityDao;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/cities/nearby")
public class NearbyCitiesController {
    private final NearbyCityFinder nearbyCityFinder;

    public NearbyCitiesController(NearbyCityFinder nearbyCityFinder, CsvCityDao csvCityDao) {
        this.nearbyCityFinder = nearbyCityFinder;
    }

    @GetMapping("/{cityName}/{stateId}")
    public ResponseEntity<List<City>> findNearbyCities(@PathVariable String cityName,
                                                       @PathVariable String stateId) {

        return ResponseEntity.ok(nearbyCityFinder.findNearbyCitiesFor(cityName, stateId, 5));
    }

    @GetMapping(value = "/export-all")
    public ResponseEntity<Resource> findNearbyCitiesForAll() throws Exception {
        File export = nearbyCityFinder.exportNearbyCitiesForAll(5);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=nearby-cities.csv")
                .contentLength(export.length())
                .contentType(APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new FileInputStream(export)));
    }
}
