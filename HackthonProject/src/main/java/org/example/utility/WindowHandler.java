package org.example.utility;

import org.openqa.selenium.WebDriver;

public class WindowHandler {

    // ✅ Only handle switching — no clicking here
    public static String switchToChildWindow(WebDriver driver) {

        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return parent;
    }

    public static void switchToParent(WebDriver driver, String parentWindow) {
        driver.switchTo().window(parentWindow);
    }
}