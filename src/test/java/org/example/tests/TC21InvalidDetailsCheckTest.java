package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC21InvalidDetailsCheckTest extends BaseClass {
    @Test
    public void LabTestsAddToCartTest_24() {

        HomePage homePage = new HomePage(driver,wait);
        homePage.clickFindDoctors();
        homePage.viewMedicalRecords();
        String parentwindow=driver.getWindowHandle();
        Set<String> windows=driver.getWindowHandles();
        for(String s:windows){
            if(!s.equals(parentwindow)){
                driver.switchTo().window(s);
            }
        }
        homePage.enterNum();
        homePage.viewRecords();

        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals("Enter valid mobile number",actualErrorMessage);
    }
}
