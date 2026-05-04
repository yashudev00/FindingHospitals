package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HospitalDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@data-qa-id='hospital_name']")
    private WebElement hospitalName;

    @FindBy(xpath = "//span[contains(@class,'common__star-rating__value')]")
    private WebElement rating;

    @FindBy(xpath = "//span[contains(text(),'Read more info')]")
    private WebElement readMore;

    private static final Logger log =
            LoggerFactory.getLogger(HospitalDetailsPage.class);

    public HospitalDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getHospitalName() {
        return hospitalName.getText();
    }

    public double getRating() {

        By ratingLocator =
                By.xpath("//span[contains(@class,'common__star-rating__value')]");

        try {
            List<WebElement> ratings = driver.findElements(ratingLocator);

            // ✅ If rating element does not exist → treat as 0
            if (ratings.isEmpty()) {
                log.info("Rating not available → treating as 0");
                return 0.0;
            }

            return Double.parseDouble(ratings.get(0).getText());

        } catch (Exception e) {
            // ✅ Any parsing / UI issue → safe fallback
            log.warn("Unable to read rating, defaulting to 0", e);
            return 0.0;
        }
    }

    public boolean isOpen24x7() {
        return driver.findElements(
                By.xpath("//p[contains(text(),'Open 24 x 7')]")
        ).size() > 0;
    }
    public boolean hasParking() {

        By readMoreLocator =
                By.xpath("//span[contains(text(),'Read more info')]");

        try {
            List<WebElement> readMoreLinks =
                    driver.findElements(readMoreLocator);

            // ✅ If "Read more info" not present → no parking info
            if (readMoreLinks.isEmpty()) {
                log.info("Read more info not available → Parking info not accessible");
                return false;
            }

            // ✅ Click only if present
            readMoreLinks.get(0).click();

            // ✅ Now check for Parking
            return driver.findElements(
                    By.xpath("//span[contains(text(),'Parking')]")
            ).size() > 0;

        } catch (Exception e) {
            // ✅ Any UI issue → safe fallback
            log.warn("Unable to determine parking availability, defaulting to false", e);
            return false;
        }
    }
}