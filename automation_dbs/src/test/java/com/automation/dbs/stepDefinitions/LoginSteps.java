package com.automation.dbs.stepDefinitions;

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
        driver.get("https://the-internet.herokuapp.com/login");
        loginPages = new LoginPages(driver);
    }

    @When("User input valid username and password")
    public void validCredentials(){
        loginPages.inputUsername("tomsmith");
        loginPages.inputPassword("SuperSecretPassword!");
    }

    @And("User click button Login")
    public void btnLogin(){
        loginPages.clickLogin();
    }

    @Then("User successfully direct to HomePage")
    public void directHomePage(){
        loginPages.homePage();
    }


    //--Login Cases
    @When("User input invalid username and invalid password")
    public void invalidCredentials(){
        loginPages.inputUsername("johndoe");
        loginPages.inputPassword("WrongPassword!");
    }

    @Then("User failed to Login and get Error Message")
    public void errorLoginMsg(){
        Assert.assertTrue(driver.getPageSource().contains("Your username is invalid!"));
    }
}
