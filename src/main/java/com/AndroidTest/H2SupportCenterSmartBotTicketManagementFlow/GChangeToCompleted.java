package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.ExtentTestListener;
import utilities.EOrderDataTicketManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GChangeToCompleted {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ============================================================
    // WAIT SETTINGS
    // ============================================================

    private static final int WAIT_TIME = 15;
    private static final int STEP_DELAY = 2;


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public GChangeToCompleted(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME)
        );
    }


    // ============================================================
    // MAIN FLOW
    // ============================================================

    public void changeTicketToCompleted() {

        ExtentTestListener.logStep(
                "Starting Change Ticket From InProgress To Completed flow"
        );

        System.out.println(
                "Starting Change Ticket From InProgress To Completed flow"
        );


        // ========================================================
        // 1. GET SAVED TICKET ID
        // ========================================================

        String ticketID =
                EOrderDataTicketManagement.ticketIDNo;

        if (ticketID == null || ticketID.trim().isEmpty()) {

            throw new RuntimeException(
                    "Ticket ID is empty. Please create/save the ticket before starting this flow."
            );
        }

        System.out.println(
                "Saved Ticket ID : " + ticketID
        );


        // ========================================================
        // 2. CLICK SAVED TICKET NUMBER
        // ========================================================

        By savedTicketNumber =
                By.xpath(
                        "//div[normalize-space()='"
                                + ticketID.trim()
                                + "']"
                );

        clickElement(
                savedTicketNumber,
                "Saved InProgress Ticket Number clicked successfully : "
                        + ticketID
        );


        // ========================================================
        // 3. SCROLL DOWN FOR STATUS
        // ========================================================

        scrollDown();

        logAndWait(
                "Scrolled down successfully for Status"
        );


        // ========================================================
        // 4. STATUS DROPDOWN
        // ========================================================

        By statusDropdown =
                By.xpath(
                        "//span[@aria-label='InProgress']"
                );

        clickElement(
                statusDropdown,
                "InProgress status dropdown clicked successfully"
        );


        // ========================================================
        // 5. SELECT COMPLETED
        // ========================================================

        By completed =
                By.xpath(
                        "//span[normalize-space()='Completed']"
                );

        clickElement(
                completed,
                "Completed status selected successfully"
        );


        // ========================================================
        // 6. WAIT FOR COMPLETION REASON
        // ========================================================

        /*
         * After selecting Completed, the CRM needs time
         * to render the Completion Reason field.
         */

        logAndWait(
                "Waiting for Completion Reason field..."
        );


        // ========================================================
        // 7. ENTER COMPLETION REASON
        // ========================================================

        enterCompletionReason("abi");


        // ========================================================
        // 8. SCROLL TO BOTTOM
        // ========================================================

        scrollToBottom();

        logAndWait(
                "Scrolled down till end successfully"
        );


        // ========================================================
        // 9. SAVE BUTTON
        // ========================================================

        By saveButton =
                By.xpath(
                        "//span[normalize-space()='Save']"
                );


        // ========================================================
        // 10. FIND SAVE BUTTON
        // ========================================================

        WebElement saveElement =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                saveButton
                        )
                );


        // ========================================================
        // 11. SCROLL SAVE INTO VIEW
        // ========================================================

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({" +
                        "behavior:'instant'," +
                        "block:'center'," +
                        "inline:'nearest'" +
                        "});",
                saveElement
        );

        logAndWait(
                "Save button brought into view successfully"
        );


        // ========================================================
        // 12. CLICK SAVE
        // ========================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        saveButton
                )
        ).click();

        logAndWait(
                "Save button clicked successfully"
        );


        // ========================================================
        // 13. FLOW COMPLETED
        // ========================================================

        ExtentTestListener.logStep(
                "Ticket changed from InProgress to Completed successfully : "
                        + ticketID
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "CHANGE TO COMPLETED COMPLETED"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Ticket ID : " + ticketID
        );

        System.out.println(
                "Previous Status : InProgress"
        );

        System.out.println(
                "New Status : Completed"
        );

        System.out.println(
                "Completion Reason : abi"
        );

        System.out.println(
                "=============================================="
        );
    }


    // ============================================================
    // ENTER COMPLETION REASON
    // ============================================================

    private void enterCompletionReason(String text) {

        System.out.println(
                "Searching for Completion Reason field..."
        );


        WebElement reasonBox = null;


        // ========================================================
        // LOCATOR 1 : TEXTAREA WITH FORM CONTROL
        // ========================================================

        By reasonTextarea =
                By.xpath(
                        "//textarea[@formcontrolname='completedReason']"
                );

        try {

            reasonBox =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    ).until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    reasonTextarea
                            )
                    );

            System.out.println(
                    "Completion Reason textarea found."
            );

        } catch (Exception ignored) {
        }


        // ========================================================
        // LOCATOR 2 : TEXTAREA USING PLACEHOLDER
        // ========================================================

        if (reasonBox == null) {

            By placeholderTextarea =
                    By.xpath(
                            "//textarea[contains(" +
                                    "translate(@placeholder," +
                                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                                    "'abcdefghijklmnopqrstuvwxyz')," +
                                    "'reason')]"
                    );

            try {

                reasonBox =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(5)
                        ).until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        placeholderTextarea
                                )
                        );

                System.out.println(
                        "Completion Reason textarea found using placeholder."
                );

            } catch (Exception ignored) {
            }
        }


        // ========================================================
        // LOCATOR 3 : CONTENTEDITABLE
        // ========================================================

        if (reasonBox == null) {

            By contentEditable =
                    By.xpath(
                            "//*[@contenteditable='true']"
                    );

            try {

                reasonBox =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(5)
                        ).until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        contentEditable
                                )
                        );

                System.out.println(
                        "Completion Reason contenteditable field found."
                );

            } catch (Exception ignored) {
            }
        }


        // ========================================================
        // LOCATOR 4 : QUILL EDITOR
        // ========================================================

        if (reasonBox == null) {

            By quillEditor =
                    By.xpath(
                            "//div[contains(@class,'ql-editor')]"
                    );

            try {

                reasonBox =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(5)
                        ).until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        quillEditor
                                )
                        );

                System.out.println(
                        "Completion Reason Quill editor found."
                );

            } catch (Exception ignored) {
            }
        }


        // ========================================================
        // FIELD NOT FOUND
        // ========================================================

        if (reasonBox == null) {

            throw new RuntimeException(
                    "Completion Reason field was not found. " +
                            "The CRM DOM/locator may have changed."
            );
        }


        // ========================================================
        // SCROLL FIELD INTO VIEW
        // ========================================================

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({" +
                        "behavior:'instant'," +
                        "block:'center'," +
                        "inline:'nearest'" +
                        "});",
                reasonBox
        );

        logAndWait(
                "Completion Reason field brought into view successfully"
        );


        // ========================================================
        // CLICK FIELD
        // ========================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        reasonBox
                )
        ).click();

        logAndWait(
                "Completion Reason field clicked successfully"
        );


        // ========================================================
        // CLEAR EXISTING VALUE
        // ========================================================

        try {

            reasonBox.clear();

        } catch (Exception ignored) {
        }


        // ========================================================
        // ENTER REASON
        // ========================================================

        reasonBox.sendKeys(text);

        logAndWait(
                "Completion Reason entered successfully : " + text
        );
    }


    // ============================================================
    // GENERIC CLICK METHOD
    // ============================================================

    private void clickElement(
            By locator,
            String successMessage
    ) {

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                locator
                        )
                );

        element.click();

        logAndWait(successMessage);
    }


    // ============================================================
    // SCROLL DOWN
    // ============================================================

    private void scrollDown() {

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy({" +
                        "top:600," +
                        "left:0," +
                        "behavior:'instant'" +
                        "});"
        );
    }


    // ============================================================
    // SCROLL TO BOTTOM
    // ============================================================

    private void scrollToBottom() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        // First scroll

        js.executeScript(
                "window.scrollTo({" +
                        "top:document.body.scrollHeight," +
                        "left:0," +
                        "behavior:'instant'" +
                        "});"
        );

        waitForTwoSeconds();


        // Extra scroll

        js.executeScript(
                "window.scrollTo({" +
                        "top:document.documentElement.scrollHeight," +
                        "left:0," +
                        "behavior:'instant'" +
                        "});"
        );

        waitForTwoSeconds();
    }


    // ============================================================
    // LOG + WAIT
    // ============================================================

    private void logAndWait(String message) {

        ExtentTestListener.logStep(message);

        waitForTwoSeconds();
    }


    // ============================================================
    // WAIT WITHOUT THREAD.SLEEP
    // ============================================================

    private void waitForTwoSeconds() {

        long startTime =
                System.currentTimeMillis();

        wait.until(driver ->
                System.currentTimeMillis() - startTime
                        >= STEP_DELAY * 1000L
        );
    }
}
