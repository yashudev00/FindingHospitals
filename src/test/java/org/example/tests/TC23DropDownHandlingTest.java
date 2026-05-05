//package org.example.tests;
//
//import org.example.base.BaseClass;
//import org.example.pages.HomePage;
//import org.testng.annotations.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TC23DropDownHandlingTest extends BaseClass {
//
//    private static final Logger log = LoggerFactory.getLogger(TC23DropDownHandlingTest.class);
//
//    @Test
//    public void verifyDropdownFilters() {
//
//        log.info("Starting TC23 - Dropdown Handling Test");
//
//        HomePage homePage = new HomePage(driver, wait);
//
//        log.info("Clicking on 'Find Doctors'");
//        homePage.clickFindDoctors();
//
//        log.info("Selecting Nutritionist from Others dropdown");
//        homePage.selectNutritionistFromOthersDropdown();
//
//        log.info("Applying Gender filter: Male Doctor");
//        homePage.filterByGender("Male Doctor");
//
//        log.info("Applying Patient Stories filter: 40+");
//        homePage.filterByPatientStories("40+ Patient Stories");
//
//        log.info("Applying Experience filter: 10+ Years");
//        homePage.filterByExperience();
//
//        log.info("Applying Consultation Fee filter: Low to High");
//        homePage.filterByConsultationFee();
//
//        log.info("Verifying Experience filter using selected text");
//        Assert.assertTrue(
//                homePage.isExperienceFilterAppliedBySelectedText(),
//                "Experience filter was NOT applied"
//        );
//
//        log.info("Verifying Consultation Fee filter using selected text");
//        Assert.assertTrue(
//                homePage.isConsultationFeeFilterAppliedBySelectedText(),
//                "Consultation Fee filter was NOT applied"
//        );
//
//        log.info("TC23 completed successfully");
//    }
//}













package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC23DropDownHandlingTest extends BaseClass {

    @Test
    public void verifyDropdownFilters() {

        HomePage homePage = new HomePage(driver, wait);

        homePage.clickFindDoctors();
        homePage.selectNutritionistFromOthersDropdown();

        homePage.filterByGender("Male Doctor");
        homePage.filterByPatientStories("40+ Patient Stories");
        homePage.filterByExperience();
        homePage.filterByConsultationFee();

        Assert.assertTrue(
                homePage.isExperienceFilterAppliedBySelectedText(),
                "Experience filter was NOT applied"
        );

        Assert.assertTrue(
                homePage.isConsultationFeeFilterAppliedBySelectedText(),
                "Consultation Fee filter was NOT applied"
        );
    }
}
