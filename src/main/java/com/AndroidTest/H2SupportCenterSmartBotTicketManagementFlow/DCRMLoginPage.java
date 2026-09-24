package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DCRMLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL =
            "https://qa-crm.smartsortsupport.com/login";

    private final By username =
            By.xpath("//input[@placeholder='Enter your username']");

    private final By password =
            By.xpath("//input[@placeholder='Enter your password']");

    private final By signIn =
            By.xpath("//span[contains(@class,'btn-inner') and contains(normalize-space(),'Sign in')]");

    public void loginCRM(String userName, String userPassword) {

        // Launch CRM Chrome
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        // Open CRM
        driver.get(URL);

        // Enter Username
        wait.until(ExpectedConditions.visibilityOfElementLocated(username))
                .sendKeys(userName);

        // Enter Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(password))
                .sendKeys(userPassword);

        // Click Sign In
        wait.until(ExpectedConditions.elementToBeClickable(signIn))
                .click();

        // Wait until login page disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(signIn));

        System.out.println("CRM Login Successful");
    }

    // Return the SAME CRM browser
    public WebDriver getDriver() {
        return driver;
    }
}
