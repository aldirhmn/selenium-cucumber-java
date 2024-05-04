package com.automation.dbs.stepDefinitions;

import com.automation.dbs.utilities.Helpers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.automation.dbs.pages.LoginPages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginSteps {
    
    private WebDriver driver;
    private LoginPages loginPages;

    @Given("User open browser and navigate to Url")
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.get(Helpers.getProperty("baseUrl"));
        loginPages = new LoginPages(driver);
    }

    @When("User input username with {string} and password with {string}")
    public void validCredentials(String username, String password){
        loginPages.inputUsername(username);
        loginPages.inputPassword(password);
    }

    @And("User click button Login")
    public void btnLogin(){
        loginPages.clickLogin();
    }

    @Then("User successfully direct to HomePage")
    public void directHomePage(){
        loginPages.homePage();
    }

    @Then("User failed to Login and get Error Message")
    public void errorLoginMsg(){
        Assert.assertTrue(driver.getPageSource().contains("Your username is invalid!"));
        Helpers.takeScreenshot();
        driver.quit();
    }
}
