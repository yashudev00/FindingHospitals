package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC08CaptureTopCitiesTest extends BaseClass {

    @Test
    public void captureTopCities() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        List<String> cities = searchPage.getTopCities();

        cities.forEach(city ->
                log.info("Top City Captured: {}", city)
        );
        Assert.assertFalse(
                cities.isEmpty(),
                "Top cities list is empty"
        );
    }
}