package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC24RadioButtonsHandlingTest extends BaseClass {

    @Test
    public void verifyDropdownAndRadioFilters() {

        HomePage homePage = new HomePage(driver, wait);

        homePage.clickFindDoctors();
        homePage.selectNutritionistFromOthersDropdown();

        homePage.filterByGender("Male Doctor");

        homePage.openAllFilters();
        homePage.selectFeesFilterSafely();
        homePage.selectAvailabilityFilterSafely();

        Assert.assertTrue(
                homePage.isConsultationFeeFilterAppliedBySelectedText(),
                "Consultation Fee radio filter was NOT applied"
        );
    }
}

