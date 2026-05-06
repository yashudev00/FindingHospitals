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
