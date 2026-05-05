package org.example.tests;
import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class TC24RadioButtonsHandlingTest extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(TC24RadioButtonsHandlingTest.class);

    @Test
    public void verifyDropdownAndRadioFilters() {

        log.info("Starting TC24 - Radio Buttons Handling Test");

        HomePage homePage = new HomePage(driver, wait);

        log.info("Clicking on 'Find Doctors'");
        homePage.clickFindDoctors();

        log.info("Selecting Nutritionist from Others dropdown");
        homePage.selectNutritionistFromOthersDropdown();

        log.info("Applying Gender filter: Male Doctor");
        homePage.filterByGender("Male Doctor");

        log.info("Opening All Filters");
        homePage.openAllFilters();

        log.info("Selecting Fees radio button");
        homePage.selectFeesFilter(3);

        log.info("Selecting Availability radio button");
        homePage.selectAvailabilityFilter(2);

        log.info("Verifying Consultation Fee filter using selected text");
        Assert.assertTrue(
                homePage.isConsultationFeeFilterAppliedBySelectedText(),
                "Consultation Fee filter was NOT applied"
        );

        log.info("TC24 completed successfully");
    }
}
