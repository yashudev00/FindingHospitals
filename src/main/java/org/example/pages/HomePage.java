package org.example.pages;

import org.example.utility.ConfigReader;
import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(className = "c-omni-clear")
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

    @FindBy(id="loginPhone")
    private WebElement mobileNum;

    @FindBy(id="loginName")
    private WebElement fullName;

    @FindBy(xpath = "//button[contains(text(),'VIEW RECORDS')]")
    private WebElement clickRecords;

    @FindBy(id="mobileValidationError")
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

    @FindBy(xpath = "//i[@data-qa-id='all_filters_icon']")
    private WebElement allFiltersIcon;

    @FindBy(xpath = "(//div[@data-qa-id='Fees_radio'])[3]")
    private WebElement feesRadioOption;

    @FindBy(xpath = "(//div[@data-qa-id='Availability_radio'])[2]")
    private WebElement availabilityRadioOption;

    @FindBy(xpath = "(//span[@data-qa-id='selected_dropdown_filter'])[1]")
    private WebElement genderDropdown;

    @FindBy(xpath = "//li[@aria-label='Male Doctor']")
    private WebElement maleDoctorOption;

    @FindBy(xpath = "(//i[contains(@class,'icon-ic_dropdown')])[2]")
    private WebElement patientStoriesDropdown;

    @FindBy(xpath = "//li[@aria-label='40+ Patient Stories']")
    private WebElement patientStories40Plus;

    @FindBy(xpath = "(//span[@data-qa-id='selected_dropdown_filter'])[3]")
    private WebElement selectedExperienceFilterText;

    @FindBy(xpath = "//span[@data-qa-id='sort_by_selected']")
    private WebElement selectedConsultationFeeText;

    @FindBy(xpath = "//button[@class='c-btn--unstyled  u-spacer--right-thin']")
    private WebElement resetFiltersButton;

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

        By suggestionBy = By.xpath(
                "//div[@data-qa-id='omni-suggestion-main'][1]"
        );

        WebElement suggestion =
                WaitUtils.waitForVisible(driver, suggestionBy);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        suggestion
                );
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

    public void openAllFilters() {
        allFiltersIcon.click();
    }

    public void selectFeesFilter(int index) {
        feesRadioOption.click();
    }

    public void selectAvailabilityFilter(int index) {
        availabilityRadioOption.click();
    }

    public void selectFeesFilterSafely() {
        wait.until(ExpectedConditions.visibilityOf(feesRadioOption));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", feesRadioOption);
    }

    public void selectAvailabilityFilterSafely() {
        wait.until(ExpectedConditions.visibilityOf(availabilityRadioOption));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", availabilityRadioOption);
    }

    public void filterByGender(String genderOption) {
        genderDropdown.click();
        maleDoctorOption.click();
    }

    public void filterByPatientStories(String option) {
        patientStoriesDropdown.click();
        patientStories40Plus.click();
    }

    public boolean isExperienceFilterAppliedBySelectedText() {
        return selectedExperienceFilterText.getText().contains("10");
    }

    public boolean isConsultationFeeFilterAppliedBySelectedText() {
        return !selectedConsultationFeeText.getText().equalsIgnoreCase("Sort by");
    }

    public boolean isConsultationFeeFilterReset() {
        return !feesRadioOption.getAttribute("class").contains("checked");
    }

    public void resetAllFilters() {
        resetFiltersButton.click();
    }

}