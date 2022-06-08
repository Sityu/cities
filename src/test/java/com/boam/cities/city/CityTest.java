package com.boam.cities.city;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {


    @Test
    public void testDistanceCalculation() {
        City newYork = City.create("New York", "NY", 40.6943, -73.9249);
        City losAngeles = City.create("Los Angeles", "CA", 34.1141, -118.4068);

        assertThat(newYork.distanceFrom(newYork)).isEqualTo(0);
        assertThat(newYork.distanceFrom(losAngeles)).isEqualTo(2456.5468652130608);
    }

}
