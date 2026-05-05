package org.example.utility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String screenshotDir =
                    System.getProperty("user.dir") + "/screenshots";

            Files.createDirectories(new File(screenshotDir).toPath());
            File dest = new File(
                    screenshotDir + "/" + testName + "_" + timestamp + ".png"
            );

            Files.copy(src.toPath(), dest.toPath());
            System.out.println(
                    "Screenshot captured: " + dest.getAbsolutePath()
            );
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
