package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.EOrderDataTicketManagement;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BRaiseTicketPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

    //=========================================================
    // LOCATORS
    //=========================================================

    private final By raiseTicket =
            AppiumBy.accessibilityId("Raise Ticket");

    private final By subjectLine =
            AppiumBy.accessibilityId("Select subject line");

    private final By orderRelated =
            AppiumBy.accessibilityId("Order Related");

    // Exact locator from Appium Inspector
    private final By ticketSubject =
            By.xpath("//android.widget.EditText");

    private final By submitTicket =
            AppiumBy.accessibilityId("Submit Ticket");

    private final By ticketSuccessPopup =
            By.xpath(
                    "//*[contains(@content-desc,'Ticket created successfully')]"
            );

    private final By okButton =
            AppiumBy.accessibilityId("OK");


    //=========================================================
    // RAISE TICKET
    //=========================================================

    public void raiseTicket() {

        // 1. Click Raise Ticket
        wait.until(
                ExpectedConditions.elementToBeClickable(raiseTicket)
        ).click();

        System.out.println("Raise Ticket clicked successfully");


        // 2. Click Subject Line
        wait.until(
                ExpectedConditions.elementToBeClickable(subjectLine)
        ).click();

        System.out.println("Subject Line dropdown clicked successfully");


        // 3. Select Order Related
        wait.until(
                ExpectedConditions.elementToBeClickable(orderRelated)
        ).click();

        System.out.println("Order Related selected successfully");


        // 4. Enter Ticket Subject
        WebElement subject = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ticketSubject)
        );

        wait.until(driver -> subject.isEnabled());

        subject.click();
        subject.clear();

        subject.sendKeys("cant able to place order");

        System.out.println("Ticket Subject entered successfully");


        // Verify text was entered
        wait.until(driver -> {
            try {
                String text = subject.getText();

                if (text == null || text.isEmpty()) {
                    text = subject.getAttribute("text");
                }

                return text != null &&
                        text.trim().equals("cant able to place order");

            } catch (Exception e) {
                return false;
            }
        });

        System.out.println("Ticket Subject verified successfully");


        // 5. Click Submit Ticket
        wait.until(
                ExpectedConditions.elementToBeClickable(submitTicket)
        ).click();

        System.out.println("Submit Ticket clicked successfully");


        // 6. Wait for Success Popup
        WebElement popup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ticketSuccessPopup
                )
        );

        String message = popup.getAttribute("contentDescription");

        if (message == null || message.isEmpty()) {
            message = popup.getAttribute("text");
        }

        System.out.println("Success Message : " + message);


        // 7. Extract Ticket ID
        Matcher matcher =
                Pattern.compile("TCK-\\d+").matcher(message);

        if (!matcher.find()) {
            throw new RuntimeException(
                    "Ticket ID not found in success popup : " + message
            );
        }

        EOrderDataTicketManagement.ticketIDNo =
                matcher.group();

        System.out.println(
                "Ticket Created Successfully : "
                        + EOrderDataTicketManagement.ticketIDNo
        );


        // 8. Click OK
        wait.until(
                ExpectedConditions.elementToBeClickable(okButton)
        ).click();

        System.out.println("OK clicked successfully");


        // 9. Wait until popup disappears
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        ticketSuccessPopup
                )
        );

        System.out.println(
                "Saved Ticket ID : "
                        + EOrderDataTicketManagement.ticketIDNo
        );


        // 10. Return from Raise Ticket screen
        driver.navigate().back();

        wait.until(driver -> {
            try {
                return !driver.getPageSource().isEmpty();
            } catch (Exception e) {
                return false;
            }
        });

        System.out.println("Returned from Raise Ticket flow");
        driver.navigate().back();
    }
}