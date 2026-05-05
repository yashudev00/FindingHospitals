package org.example.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static final int TIMEOUT = 30;

    public static WebElement waitForClickable(
            WebDriver driver,
            By locator) {

        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisible(
            WebDriver driver,
            By locator) {

        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementsVisible(
            WebDriver driver,
            List<WebElement> elements) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d ->
                        !elements.isEmpty() &&
                                elements.get(0).isDisplayed()
                );
    }

    public static void waitForMinimumElements(
            WebDriver driver,
            List<WebElement> elements,
            int expectedCount) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> elements.size() >= expectedCount);
    }
}