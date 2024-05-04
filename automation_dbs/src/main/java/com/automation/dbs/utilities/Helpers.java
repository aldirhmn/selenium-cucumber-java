package com.automation.dbs.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

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
    private static String folderName;
    private static Properties properties;
    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
    
    public static WebElement waitForElementVisible(By object, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(object));
    }

    static {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/ config/config.properties")) {
            properties = new Properties();
            properties.load(inputStream);

        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed Load Config Properties");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    static {
        String basePath = System.getProperty("user.dir");
        SimpleDateFormat folderFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-SSS");
        folderName = folderFormatter.format(new Date());
        String directoryPath = basePath + File.separator + "screenshots" + File.separator + folderName;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void takeScreenshot() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");
        String timestamp = formatter.format(new Date());
        String basePath = System.getProperty("user.dir");
        String directoryPath = basePath + File.separator + "screenshots" + File.separator + folderName;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = directoryPath + File.separator + "screenshot_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
