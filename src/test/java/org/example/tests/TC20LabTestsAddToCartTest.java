package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.LabTestsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC20LabTestsAddToCartTest extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(TC20LabTestsAddToCartTest.class);

    @Test
    public void LabTestsAddToCartTest_24() {

        LabTestsPage labTestsPage = new LabTestsPage(driver);
        labTestsPage.openLabTests();
        labTestsPage.isTopCitiesDisplayed();
        labTestsPage.clickFirstCity();
        labTestsPage.addToCart();

        int cartCount = labTestsPage.getCartItemCount();
        log.info("Cart item count after adding: {}", cartCount);

        Assert.assertTrue(cartCount > 0, "Cart is empty after adding tests");

        log.info("Lab Tests Add To Cart Test completed successfully");
    }
}
