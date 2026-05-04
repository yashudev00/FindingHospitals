package org.example.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

import java.time.Duration;

public class WaitUtils {

    private static final int TIMEOUT = 600000;

    public static WebElement waitForClickable(
            WebDriver driver, By locator) {

        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisible(
            WebDriver driver, By locator) {

        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static List<WebElement> waitForAllPresence(
            WebDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60000));
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
        );
    }
    public static void waitForNumberOfElements(
            WebDriver driver,
            By locator,
            int expectedCount
    ) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d ->
                        d.findElements(locator).size() >= expectedCount
                );
    }


    public static void waitForPresence(
            WebDriver driver, By locator) {

        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}