package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class DoctorsHomePage {

    private WebDriver driver;

    // ✅ Use By locator instead of WebElement
    private final By mumbaiDoctorsLink =
            By.xpath("//a[contains(@href,'/mumbai/doctors')]");

    public DoctorsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToMumbaiDoctors() {
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        driver.findElement(mumbaiDoctorsLink)
                );

        WaitUtils.waitForClickable(driver, mumbaiDoctorsLink).click();
    }
}