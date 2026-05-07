package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC22FindDoctorsWith10PlusYearsExperienceTest extends BaseClass {

    @Test
    public void findDoctors(){

        //log.info("Starting test: Find Doctors with 10+ years of experience");
        HomePage homePage = new HomePage(driver,wait);

        log.info("Clicking on 'Find Doctors'");
        homePage.clickFindDoctors();

        log.info("Selecting Nutritionist from Others dropdown");
        homePage.selectNutritionistFromOthersDropdown();

        log.info("Applying filter: 10+ years of experience");
        homePage.filterByExperience();

        log.info("Applying filter: Consultation fee (Low to High)");
        homePage.filterByConsultationFee();
    }
}
