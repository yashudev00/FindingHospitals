package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TC09VerifyTopCitiesAreUniqueTest extends BaseClass {

    @Test
    public void verifyTopCitiesAreUnique() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        List<String> cities = searchPage.getTopCities();
        Assert.assertFalse(cities.isEmpty(),
                "Top cities list is empty");

        Set<String> uniqueCities = new HashSet<>(cities);
        cities.forEach(city ->
                log.info("City: {}", city)
        );

        Assert.assertEquals(
                uniqueCities.size(),
                cities.size(),
                "Duplicate cities found in Top Cities list"
        );
        log.info("Top Cities list contains only unique values");
    }
}