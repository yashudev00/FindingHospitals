package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HospitalDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

  @FindBy(xpath = "//span[@data-qa-id='hospital_name']")
    private WebElement hospitalName;

    @FindBy(xpath = "//span[contains(@class,'common__star-rating__value')]")
    private List<WebElement> ratings;

    @FindBy(xpath = "//p[contains(text(),'Open 24 x 7')]")
    private List<WebElement> open24x7Text;

    @FindBy(xpath = "//span[contains(text(),'Read more info')]")
    private List<WebElement> readMoreLinks;

    @FindBy(xpath = "//span[contains(text(),'Parking')]")
    private List<WebElement> parkingText;

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
        try {
            if (ratings.isEmpty()) {
                log.info("Rating not available → treating as 0");
                return 0.0;
            }
            return Double.parseDouble(ratings.get(0).getText());
        } catch (Exception e) {
            log.warn("Unable to read rating, defaulting to 0", e);
            return 0.0;
        }
    }

    public boolean isOpen24x7() {
        return !open24x7Text.isEmpty();
    }

    public boolean hasParking() {
        try {
            if (readMoreLinks.isEmpty()) {
                log.info("Read more info not available → Parking info not accessible");
                return false;
            }

            readMoreLinks.get(0).click();

            return !parkingText.isEmpty();

        } catch (Exception e) {
            log.warn("Unable to determine parking availability, defaulting to false", e);
            return false;
        }
    }
}
