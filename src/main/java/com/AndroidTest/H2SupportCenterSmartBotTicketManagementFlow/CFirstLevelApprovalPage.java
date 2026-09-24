package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.ExtentTestListener;
import utilities.EOrderDataTicketManagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CFirstLevelApprovalPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    // LOGIN + APPROVE TICKET
    public void loginExecutive(String username, String password) {

        try {

            // Launch Chrome
            WebDriverManager.chromedriver().setup();

            webDriver = new ChromeDriver();
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            webDriver.manage().window().maximize();

            // Open Executive Portal
            webDriver.get("https://qa-bo-executive.smartsortsupport.com");

            ExtentTestListener.logStep("Open Executive Portal");

            // Enter Username
            WebElement usernameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("email1"))
            );

            usernameField.clear();
            usernameField.sendKeys(username);

            // Enter Password
            WebElement passwordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='password']"))
            );

            passwordField.clear();
            passwordField.sendKeys(password);

            // Click Sign In
            WebElement signInButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Sign In']"))
            );

            signInButton.click();

            ExtentTestListener.logStep("Sign In Clicked");

            // Wait for Login Success
            wait.until(driver ->
                    !driver.getCurrentUrl().toLowerCase().contains("login"));

            // Get Ticket ID
            String ticketID = EOrderDataTicketManagement.ticketIDNo;

            if (ticketID == null || ticketID.trim().isEmpty()) {
                throw new RuntimeException("Saved Ticket ID is Empty");
            }

            System.out.println("=================================");
            System.out.println("Saved Ticket ID : " + ticketID);
            System.out.println("=================================");

            // Search Approve Button for Ticket
            String approveXpath =
                    "//tr[contains(.,'" + ticketID + "')]//button";

            WebElement approveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(approveXpath))
            );

            approveButton.click();

            ExtentTestListener.logStep("Approve Button Clicked");

            // Continue Without Edit
            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Continue Without Edit']"))
            );

            continueBtn.click();

            ExtentTestListener.logStep("Continue Without Edit Clicked");

            // Select Priority
            WebElement priorityDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[@aria-label='Select Priority']"))
            );

            priorityDropdown.click();

            WebElement priorityMedium = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Medium']"))
            );

            priorityMedium.click();

            ExtentTestListener.logStep("Priority Selected : Medium");

            // Select Transfer To
            WebElement transferDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[@aria-label='Select Transfer To List']"))
            );

            transferDropdown.click();

            WebElement productManager = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Product Manager']"))
            );

            productManager.click();

            ExtentTestListener.logStep("Transfer To : Product Manager");

            // Final Approve
            WebElement finalApprove = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Approve']"))
            );

            finalApprove.click();

            ExtentTestListener.logStep("Final Approve Clicked");

            // Wait Process Complete
            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.xpath("//span[normalize-space()='Approve']"))
            );

            System.out.println("Ticket Approved Successfully : " + ticketID);

            ExtentTestListener.logStep(
                    "Ticket Approved Successfully : " + ticketID);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Executive Login / Ticket Approval Failed",
                    e);

        } finally {

            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}