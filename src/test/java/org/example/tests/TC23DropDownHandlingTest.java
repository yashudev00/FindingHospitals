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
