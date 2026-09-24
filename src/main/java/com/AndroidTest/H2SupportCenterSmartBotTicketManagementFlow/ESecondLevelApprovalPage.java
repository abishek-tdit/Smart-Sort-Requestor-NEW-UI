package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.ExtentTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ESecondLevelApprovalPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ESecondLevelApprovalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private final By customerCare =
            By.xpath("//span[contains(@class,'sb-section-label') " +
                    "and normalize-space()='Customer Care']");

    private final By taskManagement =
            By.xpath("//span[contains(@class,'sb-section-label') " +
                    "and normalize-space()='Task Management']");

    private final By ticketAssignment =
            By.xpath("//span[normalize-space()='Ticket Assignment']");

    public void openTicketAssignment() {

        // Wait until CRM login is completed
        wait.until(driver ->
                !driver.getCurrentUrl().toLowerCase().contains("/login"));

        System.out.println("CRM Dashboard Loaded : " + driver.getCurrentUrl());

        // Customer Care
        wait.until(ExpectedConditions.elementToBeClickable(customerCare)).click();

        ExtentTestListener.logStep("Customer Care clicked successfully");

        // Task Management
        wait.until(ExpectedConditions.elementToBeClickable(taskManagement)).click();

        ExtentTestListener.logStep("Task Management clicked successfully");

        // Ticket Assignment
        wait.until(ExpectedConditions.elementToBeClickable(ticketAssignment)).click();

        ExtentTestListener.logStep("Ticket Assignment opened successfully");

        System.out.println("Ticket Assignment opened successfully");

        System.out.println("Second Level Approval completed - CRM browser remains open");
    }
}
