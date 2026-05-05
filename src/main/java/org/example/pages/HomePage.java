package org.example.pages;

import org.example.utility.ConfigReader;
import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-locality']")
    private WebElement locationInput;

    @FindBy(xpath = "//i[@class='icon-ic_cross_solid']")
    private WebElement clearLocation;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
    private WebElement searchBox;

    @FindBy(xpath = "(//div[@class='product-tab__title'])[1]")
    private WebElement findDoctors;

    @FindBy(xpath = "//span[text()='View medical records']")
    private WebElement viewRecords;

    @FindBy(xpath = "//input[@id='loginPhone']")
    private WebElement mobileNum;

    @FindBy(xpath = "//input[@id='loginName']")
    private WebElement fullName;

    @FindBy(xpath = "//button[contains(text(),'VIEW RECORDS')]")
    private WebElement clickRecords;

    @FindBy(xpath = "//div[@id='mobileValidationError']")
    private WebElement errorElement;


    @FindBy(xpath = "//span[@data-qa-id='others-speciality'] ")
    private WebElement othersDropDown;

    @FindBy(xpath = "//p[text()='Dietitian/Nutritionist']")
    private WebElement clickNutritionist;

    @FindBy(xpath = "//div[@data-qa-id='years_of_experience_section']")
    private WebElement experienceDropdown;

    @FindBy(xpath = "//span[@data-qa-id='sort_by_selected']")
    private WebElement consultationDropdown;

    @FindBy(xpath = "//li[@data-qa-id='10,9999999' and @tabindex='0']/span")
    private WebElement tenPluseExperience;

    @FindBy(xpath = "//li[@data-qa-id='consultation_fees']")
    private WebElement lowToHighFee;


    //DO NOT cache Hospital suggestion as WebElement (dynamic DOM)
    private By hospitalSuggestion =
            By.xpath("//div[@data-qa-id='omni-suggestion-main' and normalize-space()='Hospital']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    //Search hospitals in default city
    public void searchHospitalInDefaultCity() {

        String city = ConfigReader.get("defaultCity");

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//input[@data-qa-id='omni-searchbox-locality']")
        ).click();

        clearLocation.click();
        locationInput.sendKeys(city);

        // ✅ Re-locate city suggestion dynamically
        WaitUtils.waitForClickable(
                driver,
                By.xpath("//div[@data-qa-id='omni-suggestion-main' and normalize-space()='"
                        + city + "']")
        ).click();

        searchBox.clear();
        searchBox.sendKeys("Hospital");

        // ✅ Re-locate hospital suggestion dynamically
        WaitUtils.waitForClickable(driver, hospitalSuggestion).click();
    }

    // ✅ Search hospitals in specific city (used in TC08)
    public void searchHospitalInSpecificCity(String city) {

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//input[@data-qa-id='omni-searchbox-locality']")
        ).click();

        clearLocation.click();
        locationInput.sendKeys(city);

        // ✅ Re-locate city suggestion dynamically (IMPORTANT)
        WaitUtils.waitForClickable(
                driver,
                By.xpath("//div[@data-qa-id='omni-suggestion-main' and normalize-space()='"
                        + city + "']")
        ).click();

        searchBox.clear();
        searchBox.sendKeys("Hospital");

        // ✅ Re-locate hospital suggestion dynamically
        WaitUtils.waitForClickable(driver, hospitalSuggestion).click();
    }
    // ✅ NEW: Go to Hospitals page directly (no default city logic)
    public void searchHospitalDirectly() {

        // Enter Hospital keyword directly
        WaitUtils.waitForClickable(
                driver,
                By.xpath("//input[@data-qa-id='omni-searchbox-keyword']")
        ).click();

        searchBox.clear();
        searchBox.sendKeys("Hospital");
        WaitUtils.waitForClickable(driver, hospitalSuggestion).click();

        // Wait until hospital results load
        WaitUtils.waitForPresence(
                driver,
                By.xpath("//h2[@class='line-1']")
        );
    }

    public void clickFindDoctors() {
        findDoctors.click();
    }

    public void viewMedicalRecords(){
        viewRecords.click();
    }

    public void enterNum(){
        mobileNum.sendKeys("463829848");
        fullName.sendKeys("Yashu");
    }

    public void viewRecords(){
        clickRecords.click();
    }

    public String getErrorMessage(){
        return errorElement.getText();
    }

    public void selectNutritionistFromOthersDropdown() {

        othersDropDown.click();
        clickNutritionist.click();

    }

    public void filterByExperience() {
        wait.until(ExpectedConditions.elementToBeClickable(experienceDropdown)).click();
        tenPluseExperience.click();
    }

    public void filterByConsultationFee() {
        wait.until(ExpectedConditions.elementToBeClickable(consultationDropdown)).click();
        lowToHighFee.click();
    }

    public boolean isExperienceFilterApplied() {
        // Example validation: check if the element is displayed or selected
        return tenPluseExperience.isDisplayed();
    }

    public boolean isFeeFilterApplied() {
        // Example validation: check if the element is displayed or selected
        return lowToHighFee.isDisplayed();
    }

}
