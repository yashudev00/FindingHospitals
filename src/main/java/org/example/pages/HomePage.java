package org.example.pages;

import org.example.utility.ConfigReader;
import org.example.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-locality']")
    private WebElement locationInput;

    @FindBy(xpath = "//i[@class='icon-ic_cross_solid']")
    private WebElement clearLocation;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@data-qa-id='omni-suggestion-main']")
    private List<WebElement> citySuggestions;

    @FindBy(xpath = "//div[@data-qa-id='omni-suggestion-main' and normalize-space()='Hospital']")
    private WebElement hospitalSuggestion;

    @FindBy(xpath = "//div[@class='c-estb-info']")
    private List<WebElement> hospitalResults;

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

    @FindBy(xpath = "//span[@data-qa-id='others-speciality']")
    private WebElement othersDropDown;

    @FindBy(xpath = "//p[text()='Dietitian/Nutritionist']")
    private WebElement clickNutritionist;

    @FindBy(xpath = "//div[@data-qa-id='years_of_experience_section']")
    private WebElement experienceDropdown;

    @FindBy(xpath = "(//li[@class='c-dropdown__list__item'])[6]")
    private WebElement tenPlusExperience;

    @FindBy(xpath = "//span[@data-qa-id='sort_by_selected']")
    private WebElement consultationDropdown;

    @FindBy(xpath = "//li[@data-qa-id='consultation_fees']")
    private WebElement lowToHighFee;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void searchHospitalInDefaultCity() {
        searchHospitalInCity(ConfigReader.get("defaultCity"));
    }

    public void searchHospitalInSpecificCity(String city) {
        searchHospitalInCity(city);
    }

    private void searchHospitalInCity(String city) {
        locationInput.click();
        clearLocation.click();
        locationInput.sendKeys(city);
        WaitUtils.waitForElementsVisible(driver, citySuggestions);
        for (WebElement suggestion : citySuggestions) {
            if (suggestion.getText().trim().equalsIgnoreCase(city)) {
                suggestion.click();
                break;
            }
        }

        searchBox.clear();
        searchBox.sendKeys("Hospital");
        hospitalSuggestion.click();
    }

    public void searchHospitalDirectly() {
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys("Hospital");
        hospitalSuggestion.click();
        WaitUtils.waitForElementsVisible(driver, hospitalResults);
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
        tenPlusExperience.click();
    }

    public void filterByConsultationFee() {
        wait.until(ExpectedConditions.elementToBeClickable(consultationDropdown)).click();
        lowToHighFee.click();
    }



}