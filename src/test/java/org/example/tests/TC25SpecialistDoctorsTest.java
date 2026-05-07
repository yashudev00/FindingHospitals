package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC25SpecialistDoctorsTest extends BaseClass {

    @Test
    public void verifySpecialistDoctorsSection() {

        //log.info("Starting test: Verify Specialist Doctors section");

        HomePage homePage = new HomePage(driver, wait);

        String headingText = homePage.specialistDoctors();

        log.info("Specialities Heading Text retrieved: {}", headingText);

        Assert.assertNotNull(
                headingText,
                "Specialities heading text should not be null"
        );

        log.info("Test completed successfully");
    }
}
