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

public class FChangeToInProgress {

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

    public FChangeToInProgress(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME)
        );
    }


    // ============================================================
    // MAIN FLOW
    // ============================================================

    public void changeTicketToInProgress() {

        ExtentTestListener.logStep(
                "Starting Change Ticket To InProgress flow"
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
                        "//div[contains(@class,'ta-card-code')" +
                                " and normalize-space()='" +
                                ticketID.trim() +
                                "']"
                );

        clickElement(
                savedTicketNumber,
                "Saved Ticket Number clicked successfully : " + ticketID
        );


        // ========================================================
        // 3. PROJECT DROPDOWN
        // ========================================================

        By projectDropdown =
                By.xpath("//span[@aria-label='Select Project']");

        clickElement(
                projectDropdown,
                "Project dropdown clicked successfully"
        );


        // ========================================================
        // 4. SELECT SMARTSORT
        // ========================================================

        By smartSort =
                By.xpath("//span[normalize-space()='SmartSort']");

        clickElement(
                smartSort,
                "SmartSort selected successfully"
        );


        // ========================================================
        // 5. COUNTRY DROPDOWN
        // ========================================================

        By countryDropdown =
                By.xpath("//span[@aria-label='Select Country']");

        clickElement(
                countryDropdown,
                "Country dropdown clicked successfully"
        );


        // ========================================================
        // 6. SELECT SAUDI ARABIA
        // ========================================================

        By saudiArabia =
                By.xpath("//span[normalize-space()='Saudi Arabia']");

        clickElement(
                saudiArabia,
                "Saudi Arabia selected successfully"
        );


        // ========================================================
        // 7. SCROLL DOWN
        // ========================================================

        scrollDown();

        logAndWait(
                "Scrolled down successfully"
        );


        // ========================================================
        // 8. WORK TYPE DROPDOWN
        // ========================================================

        By workTypeDropdown =
                By.xpath("//span[@aria-label='Select Work Type']");

        clickElement(
                workTypeDropdown,
                "Work Type dropdown clicked successfully"
        );


        // ========================================================
        // 9. SELECT BUG
        // ========================================================

        By bug =
                By.xpath(
                        "//span[contains(@class,'ng-star-inserted')" +
                                " and normalize-space()='Bug']"
                );

        clickElement(
                bug,
                "Bug work type selected successfully"
        );


        // ========================================================
        // 10. STATUS DROPDOWN
        // ========================================================

        By statusDropdown =
                By.xpath("//span[@aria-label='Open']");

        clickElement(
                statusDropdown,
                "Status dropdown clicked successfully"
        );


        // ========================================================
        // 11. SELECT IN PROGRESS
        // ========================================================

        By inProgress =
                By.xpath(
                        "//span[normalize-space()='InProgress']"
                );

        clickElement(
                inProgress,
                "InProgress status selected successfully"
        );


        // ========================================================
        // 12. SCROLL UP FOR DESCRIPTION
        // ========================================================

        scrollUpForDescription();


        // ========================================================
        // 13. ENTER DESCRIPTION
        // ========================================================

        enterDescription("OK");


        // ========================================================
        // 14. SCROLL DOWN AFTER DESCRIPTION
        // ========================================================

        scrollDown();

        logAndWait(
                "Scrolled down successfully after Description"
        );


        // ========================================================
        // 15. TEAM DROPDOWN
        // ========================================================

        By teamDropdown =
                By.xpath("//span[@aria-label='Select Team']");

        clickElement(
                teamDropdown,
                "Team dropdown clicked successfully"
        );


        // ========================================================
        // 16. SELECT QA TESTING TEAM
        // ========================================================

        By qaTestingTeam =
                By.xpath(
                        "//strong[normalize-space()='QA Testing Team']"
                );

        clickElement(
                qaTestingTeam,
                "QA Testing Team selected successfully"
        );


        // ========================================================
        // 17. SPACE DROPDOWN
        // ========================================================

        By spaceDropdown =
                By.xpath("//span[@aria-label='Select spaces']");

        clickElement(
                spaceDropdown,
                "Space dropdown clicked successfully"
        );


        // ========================================================
        // 18. SELECT SERVICE DESK-TICKETS
        // ========================================================

        By serviceDeskTickets =
                By.xpath(
                        "//span[normalize-space()='Service Desk-Tickets']"
                );

        clickElement(
                serviceDeskTickets,
                "Service Desk-Tickets selected successfully"
        );


        // ========================================================
        // 19. SCROLL DOWN FOR ASSIGNER
        // ========================================================

        scrollDown();

        logAndWait(
                "Scrolled down successfully for Assigner"
        );


        // ========================================================
        // 20. ASSIGNER DROPDOWN
        // ========================================================

        By assignerDropdown =
                By.xpath(
                        "//span[@aria-label='Select Assigner']"
                );

        clickElement(
                assignerDropdown,
                "Assigner dropdown clicked successfully"
        );


        // ========================================================
        // 21. SELECT PRODUCTION MANAGER
        // ========================================================

        By productionManager =
                By.xpath(
                        "//span[normalize-space()='Production Manager']"
                );

        clickElement(
                productionManager,
                "Production Manager selected successfully"
        );


        // ========================================================
        // 22. EMPLOYEE DROPDOWN
        // ========================================================

        By employeeDropdown =
                By.xpath(
                        "//span[@aria-label='Select Employee']"
                );

        clickElement(
                employeeDropdown,
                "Employee dropdown clicked successfully"
        );


        // ========================================================
        // 23. SELECT HARIKARAN
        // ========================================================

        By harikaran =
                By.xpath(
                        "//span[contains(@class,'ng-star-inserted')" +
                                " and normalize-space()='Harikaran']"
                );

        clickElement(
                harikaran,
                "Harikaran selected successfully"
        );


        // ========================================================
        // 24. SCROLL TO BOTTOM
        // ========================================================

        scrollToBottom();

        logAndWait(
                "Scrolled down till end successfully"
        );


        // ========================================================
        // 25. SAVE BUTTON
        // ========================================================

        By saveButton =
                By.xpath(
                        "//span[normalize-space()='Save']"
                );

        WebElement saveElement =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                saveButton
                        )
                );


        // ========================================================
        // 26. SCROLL SAVE BUTTON INTO VIEW
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
        // 27. CLICK SAVE
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
        // 28. FLOW COMPLETED
        // ========================================================

        ExtentTestListener.logStep(
                "Change Ticket To InProgress flow completed successfully for : "
                        + ticketID
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "CHANGE TO INPROGRESS COMPLETED"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Ticket ID : " + ticketID
        );

        System.out.println(
                "Status : InProgress"
        );

        System.out.println(
                "Description : OK"
        );

        System.out.println(
                "Team : QA Testing Team"
        );

        System.out.println(
                "Space : Service Desk-Tickets"
        );

        System.out.println(
                "Assigner : Production Manager"
        );

        System.out.println(
                "Employee : Harikaran"
        );

        System.out.println(
                "=============================================="
        );
    }


    // ============================================================
    // ENTER DESCRIPTION
    // ============================================================

    private void enterDescription(String text) {

        System.out.println(
                "Waiting for Description field..."
        );

        /*
         * First try common textarea.
         */
        By textarea =
                By.xpath(
                        "//textarea"
                );

        /*
         * Second try contenteditable.
         */
        By contentEditable =
                By.xpath(
                        "//*[@contenteditable='true']"
                );

        /*
         * Third try Quill.
         */
        By quillEditor =
                By.xpath(
                        "//div[contains(@class,'ql-editor')]"
                );


        WebElement descriptionBox = null;


        // ========================================================
        // TRY TEXTAREA
        // ========================================================

        try {

            descriptionBox =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    ).until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    textarea
                            )
                    );

            System.out.println(
                    "Description textarea found."
            );

        } catch (Exception ignored) {
        }


        // ========================================================
        // TRY CONTENTEDITABLE
        // ========================================================

        if (descriptionBox == null) {

            try {

                descriptionBox =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(5)
                        ).until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        contentEditable
                                )
                        );

                System.out.println(
                        "Contenteditable Description field found."
                );

            } catch (Exception ignored) {
            }
        }


        // ========================================================
        // TRY QUILL EDITOR
        // ========================================================

        if (descriptionBox == null) {

            try {

                descriptionBox =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(5)
                        ).until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        quillEditor
                                )
                        );

                System.out.println(
                        "Quill Description field found."
                );

            } catch (Exception ignored) {
            }
        }


        // ========================================================
        // DESCRIPTION NOT FOUND
        // ========================================================

        if (descriptionBox == null) {

            throw new RuntimeException(
                    "Description field not found. " +
                            "The CRM DOM/locator may have changed."
            );
        }


        // ========================================================
        // SCROLL DESCRIPTION INTO VIEW
        // ========================================================

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({" +
                        "behavior:'instant'," +
                        "block:'center'," +
                        "inline:'nearest'" +
                        "});",
                descriptionBox
        );

        logAndWait(
                "Description field brought into view successfully"
        );


        // ========================================================
        // CLICK DESCRIPTION
        // ========================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        descriptionBox
                )
        ).click();

        logAndWait(
                "Description field clicked successfully"
        );


        // ========================================================
        // CLEAR + ENTER OK
        // ========================================================

        descriptionBox.clear();

        descriptionBox.sendKeys(text);

        logAndWait(
                "Description entered successfully : " + text
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
    // SCROLL UP FOR DESCRIPTION
    // ============================================================

    private void scrollUpForDescription() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        System.out.println(
                "Scrolling up for Description..."
        );

        for (int i = 0; i < 5; i++) {

            js.executeScript(
                    "window.scrollBy({" +
                            "top:-700," +
                            "left:0," +
                            "behavior:'instant'" +
                            "});"
            );

            waitForTwoSeconds();
        }

        ExtentTestListener.logStep(
                "Scrolled up successfully for Description"
        );
    }


    // ============================================================
    // SCROLL TO BOTTOM
    // ============================================================

    private void scrollToBottom() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollTo({" +
                        "top:document.body.scrollHeight," +
                        "left:0," +
                        "behavior:'instant'" +
                        "});"
        );

        waitForTwoSeconds();

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
    // WAIT WITHOUT Thread.sleep
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