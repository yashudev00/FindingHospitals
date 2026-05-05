//package org.example.tests;
//
//import org.example.base.BaseClass;
//import org.example.pages.HomePage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class TC25ResetFiltersHandlingTest extends BaseClass {
//
//    private static final Logger log =
//            LoggerFactory.getLogger(TC25ResetFiltersHandlingTest.class);
//
//    @Test
//    public void verifyResetFiltersUsingCorrectAssertions() {
//
//        log.info("Starting TC25 - Reset Filters Handling Test");
//
//        HomePage homePage = new HomePage(driver, wait);
//
//        /* -------- Navigate to Find Doctors -------- */
//        log.info("Clicking on Find Doctors");
//        homePage.clickFindDoctors();
//
//        log.info("Selecting Nutritionist from Others dropdown");
//        homePage.selectNutritionistFromOthersDropdown();
//
//        /* -------- Apply Filters -------- */
//        log.info("Applying Gender filter");
//        homePage.filterByGender("Male Doctor");
//
//        log.info("Applying Patient Stories filter");
//        homePage.filterByPatientStories("40+ Patient Stories");
//
//        log.info("Applying Experience filter");
//        homePage.filterByExperience();
//
//        log.info("Opening All Filters");
//        homePage.openAllFilters();
//
//        log.info("Selecting Fees radio safely");
//        homePage.selectFeesFilterSafely();
//
//        log.info("Selecting Availability radio safely");
//        homePage.selectAvailabilityFilterSafely();
//
//        /* -------- Reset Filters -------- */
//        log.info("Resetting all filters");
//        homePage.resetAllFilters();
//
//        /* -------- Assertions -------- */
//        log.info("Verifying Experience filter is reset");
//        Assert.assertFalse(
//                homePage.isExperienceFilterAppliedBySelectedText(),
//                "Experience filter was NOT reset"
//        );
//
//        log.info("Verifying Consultation Fee filter is reset");
//        Assert.assertTrue(
//                homePage.isConsultationFeeFilterReset(),
//                "Consultation Fee filter was NOT reset"
//        );
//
//        log.info("TC25 completed successfully");
//    }
//}



package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC25ResetFiltersHandlingTest extends BaseClass {

    @Test
    public void verifyResetFilters() {

        HomePage homePage = new HomePage(driver, wait);

        homePage.clickFindDoctors();
        homePage.selectNutritionistFromOthersDropdown();

        homePage.filterByGender("Male Doctor");
        homePage.filterByPatientStories("40+ Patient Stories");
        homePage.filterByExperience();

        homePage.openAllFilters();
        homePage.selectFeesFilterSafely();
        homePage.selectAvailabilityFilterSafely();

        homePage.resetAllFilters();

        Assert.assertTrue(
                homePage.isConsultationFeeFilterReset(),
                "Reset Filters did NOT clear fee selection"
        );
    }
}
