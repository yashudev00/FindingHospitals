package org.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindDoctorsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@data-qa-id='doctor_gender_section']")
    private WebElement genderDropdown;

    @FindBy(xpath = "//li[@data-qa-id='male']")
    private WebElement maleOption;

    @FindBy(xpath = "//div[@data-qa-id='doctor_review_count_section']")
    private WebElement patientStoriesDropdown;

    @FindBy(xpath = "//li[@aria-label='20+ Patient Stories']")
    private WebElement patientStories20Plus;

    @FindBy(xpath = "//div[@data-qa-id='years_of_experience_section']")
    private WebElement experienceDropdown;

    @FindBy(xpath = "//li[@aria-label='5+ Years of experience']")
    private WebElement experience5Plus;

    @FindBy(xpath = "//span[@data-qa-id='all_filters']")
    private WebElement allFilters;

    @FindBy(xpath = "//span[@data-qa-id='₹0-₹500_label']")
    private WebElement feesFilter;

    @FindBy(xpath = "//h1[@class='u-xx-large-font u-bold']")
    private WebElement resultCount;

    public FindDoctorsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectGenderMale() {
        genderDropdown.click();
        maleOption.click();
    }

    public void selectPatientStories20Plus() {
        patientStoriesDropdown.click();
        patientStories20Plus.click();
    }

    public void selectExperience5Plus() {
        experienceDropdown.click();
        experience5Plus.click();
    }

    public void applyFeesFilter() {
        allFilters.click();
        feesFilter.click();
    }

    public WebElement getResultElement() {
        return resultCount;
    }

    public String getResultCountText() {
        return resultCount.getText();
    }
}