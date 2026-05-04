package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CorporateWellnessPage {

    private WebDriver driver;

    //Corporate menu
    @FindBy(xpath = "//span[@class='nav-interact']")
    private WebElement corporateMenu;

    //Health & Wellness Plans
    @FindBy(xpath = "//a[text()='Health & Wellness Plans']")
    private WebElement healthWellnessPlans;

    //Page header
    @FindBy(xpath = "//h1 | //h2")
    private WebElement pageHeader;

    //Form fields
    @FindBy(xpath = "(//input[@id='name'][@name='name'])[1]")
    private WebElement nameInput;

    @FindBy(xpath = "(//input[@id='organizationName'])[1]")
    private WebElement organizationNameInput;

    @FindBy(xpath = "(//input[@id='contactNumber'])[1]")
    private WebElement contactNumberInput;

    @FindBy(xpath = "(//input[@id='officialEmailId'])[1]")
    private WebElement emailInput;

    //FIXED: organization size is SELECT, not INPUT
    @FindBy(id = "organizationSize")
    private WebElement organizationSizeDropdown;

    //Schedule Demo button
    @FindBy(xpath = "//button[contains(text(),'Schedule')]")
    private WebElement scheduleDemoButton;

    public CorporateWellnessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Navigation
    public void clickCorporateMenu() {
        corporateMenu.click();
    }

    public void clickHealthAndWellnessPlans() {
        healthWellnessPlans.click();
    }

    //Enter INVALID data
    public void enterInvalidFormDetails() {

        //Wait for form to be ready
        WaitUtils.waitForVisible(driver,
                By.xpath("(//input[@id='name'][@name='name'])[1]"));

        nameInput.sendKeys("12345");               // Invalid name
        organizationNameInput.sendKeys("@@@");    // Invalid organization
        contactNumberInput.sendKeys("abcd123");   // Invalid phone
        emailInput.sendKeys("invalid-email");     // Invalid email

        //Safe dropdown selection
        Select select = new Select(organizationSizeDropdown);
        select.selectByIndex(1);
    }

    //Button state check
    public boolean isScheduleDemoClickable() {
        return scheduleDemoButton.isDisplayed()
                && scheduleDemoButton.isEnabled();
    }

    //Header getter
    public String getPageHeaderText() {
        return pageHeader.getText().trim();
    }
}