package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.EOrderDataTicketManagement;

import java.time.Duration;
import java.util.Map;

public class HTicketHistoryVerificationPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    // =========================================================
    // Constructor
    // =========================================================

    public HTicketHistoryVerificationPage() {
        super();
    }

    // =========================================================
    // Open Saved Ticket
    // =========================================================

    public void openSavedTicket() {

        String ticketId = EOrderDataTicketManagement.ticketIDNo;

        if (ticketId == null || ticketId.trim().isEmpty()) {

            throw new RuntimeException(
                    "Ticket ID is empty. Cannot open Ticket History."
            );
        }

        ticketId = ticketId.trim();

        System.out.println("=================================");
        System.out.println("Starting Ticket History Verification");
        System.out.println("Ticket ID : " + ticketId);
        System.out.println("=================================");

        try {

            // =====================================================
            // STEP 1 : WAIT AFTER RETURNING FROM CRM
            // =====================================================

            System.out.println(
                    "Waiting 3 seconds for mobile app to update..."
            );

            Thread.sleep(3000);

            System.out.println(
                    "3 seconds wait completed"
            );

            // =====================================================
            // STEP 2 : RELOAD TICKET HISTORY PAGE
            // =====================================================

            System.out.println(
                    "Reloading Ticket History page..."
            );

            driver.executeScript(
                    "mobile: swipeGesture",
                    Map.of(
                            "left", 360,
                            "top", 400,
                            "width", 10,
                            "height", 10,
                            "direction", "down",
                            "percent", 0.70
                    )
            );

            // Wait for page content
            wait.until(driver ->
                    !driver.findElements(
                            AppiumBy.xpath("//*")
                    ).isEmpty()
            );

            System.out.println(
                    "Ticket History page reloaded successfully"
            );

            // =====================================================
            // STEP 3 : DYNAMIC TICKET LOCATOR
            // =====================================================

            By savedTicket = AppiumBy.xpath(
                    "//*[contains(@content-desc,'" + ticketId + "') " +
                            "or contains(@text,'" + ticketId + "')]"
            );

            // =====================================================
            // STEP 4 : CHECK CURRENT SCREEN
            // =====================================================

            boolean ticketFound = false;

            if (!driver.findElements(savedTicket).isEmpty()) {

                ticketFound = true;

                System.out.println(
                        "Saved Ticket found on current screen : "
                                + ticketId
                );
            }

            // =====================================================
            // STEP 5 : SCROLL AND SEARCH
            // =====================================================

            if (!ticketFound) {

                System.out.println(
                        "Saved Ticket not visible. Searching by scrolling..."
                );

                for (int attempt = 1; attempt <= 6; attempt++) {

                    System.out.println(
                            "Scrolling Ticket History : attempt "
                                    + attempt
                    );

                    driver.executeScript(
                            "mobile: scrollGesture",
                            Map.of(
                                    "left", 50,
                                    "top", 300,
                                    "width", 620,
                                    "height", 1000,
                                    "direction", "down",
                                    "percent", 0.70
                            )
                    );

                    // Small wait after scroll for UI update
                    wait.until(driver ->
                            !driver.findElements(
                                    AppiumBy.xpath("//*")
                            ).isEmpty()
                    );

                    if (!driver.findElements(savedTicket).isEmpty()) {

                        ticketFound = true;

                        System.out.println(
                                "Saved Ticket found after scroll : "
                                        + ticketId
                        );

                        break;
                    }
                }
            }

            // =====================================================
            // STEP 6 : TICKET NOT FOUND
            // =====================================================

            if (!ticketFound) {

                System.out.println(
                        "ERROR : Saved Ticket was not found : "
                                + ticketId
                );

                throw new RuntimeException(
                        "Ticket History validation failed. " +
                                "Ticket not found : " + ticketId
                );
            }

            // =====================================================
            // STEP 7 : GET SAVED TICKET ELEMENT
            // =====================================================

            WebElement ticket = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            savedTicket
                    )
            );

            System.out.println(
                    "Saved Ticket is visible : " + ticketId
            );

            // =====================================================
            // STEP 8 : CLICK SAVED TICKET
            // =====================================================

            wait.until(
                    ExpectedConditions.elementToBeClickable(ticket)
            );

            ticket.click();

            System.out.println(
                    "SUCCESS : Saved Ticket clicked : " + ticketId
            );

            System.out.println(
                    "Ticket History Verification opened successfully"
            );

        } catch (TimeoutException e) {

            System.out.println(
                    "ERROR : Timeout while opening Saved Ticket : "
                            + ticketId
            );

            throw new RuntimeException(
                    "Ticket History validation failed. " +
                            "Unable to open ticket : " + ticketId,
                    e
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Ticket History validation interrupted : "
                            + ticketId,
                    e
            );
        }
    }
}
