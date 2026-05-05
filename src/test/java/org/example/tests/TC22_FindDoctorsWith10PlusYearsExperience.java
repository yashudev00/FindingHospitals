package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC22_FindDoctorsWith10PlusYearsExperience extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(TC22_FindDoctorsWith10PlusYearsExperience.class);
    @Test
    public void findDoctors(){

        log.info("Starting test: Find Doctors with 10+ years of experience");
        HomePage homePage = new HomePage(driver,wait);

        log.info("Clicking on 'Find Doctors'");
        homePage.clickFindDoctors();

        log.info("Selecting Nutritionist from Others dropdown");
        homePage.selectNutritionistFromOthersDropdown();

        log.info("Applying filter: 10+ years of experience");
        homePage.filterByExperience();

        log.info("Applying filter: Consultation fee (Low to High)");
        homePage.filterByConsultationFee();

//        boolean experienceApplied = homePage.isExperienceFilterApplied();
//        boolean feeApplied = homePage.isFeeFilterApplied();
//
//        log.info("Verifying filters applied correctly");
//        Assert.assertTrue(experienceApplied, "Experience filter was not applied correctly");
//        Assert.assertTrue(feeApplied, "Consultation fee filter was not applied correctly");
//
//        log.info("Test completed successfully");
    }
}
