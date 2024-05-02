package com.automation.dbs.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {

    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
    
    public static WebElement waitForElementVisible(By object, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(object));
    }

    public static void takeScreenshot() {
        String basePath = System.getProperty("user.dir");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");
        String timestamp = formatter.format(new Date());

        String directoryPath = basePath + File.separator + "screenshots" + File.separator + timestamp;

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = directoryPath + File.separator + "screenshot_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
