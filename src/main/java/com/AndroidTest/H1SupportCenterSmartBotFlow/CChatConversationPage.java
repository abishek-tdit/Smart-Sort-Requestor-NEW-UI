package com.AndroidTest.H1SupportCenterSmartBotFlow;

import Base.BasePage;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CChatConversationPage extends BasePage {

    public CChatConversationPage(
            io.appium.java_client.android.AndroidDriver driver) {
        super(driver);
    }

    //=========================================================
    // Locators
    //=========================================================

    private final By messageBox =
            AppiumBy.className("android.widget.EditText");

    // Send icon: Smart Bot -> ImageView[3]
    private final By sendButton =
            AppiumBy.xpath(
                    "//android.view.View[@content-desc='Smart Bot']" +
                            "/android.widget.ImageView[3]");

    private final By closeButton =
            AppiumBy.xpath(
                    "//android.view.View[@content-desc='Smart Bot']" +
                            "/android.view.View[3]");

    private final By thinking =
            AppiumBy.xpath("//*[@content-desc='Thinking...']");

    private final By messages =
            AppiumBy.xpath(
                    "//android.view.View[@text]");


    //=========================================================
    // Ask Question
    //=========================================================

    public void askQuestion(String question) {

        log("========== SMARTBOT QUESTION ==========");

        // Enter question
        sendKeys(messageBox, question);
        log("Question Entered : " + question);

        // Wait for Send icon
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement send = wait.until(
                ExpectedConditions.elementToBeClickable(sendButton));

        // Click Send
        send.click();

        log("Clicked Send Button");
        ExtentTestListener.logStep("Question Sent : " + question);

        // Wait and get bot response
        String botReply = waitForBotResponse();

        System.out.println();
        System.out.println("=================================");
        ExtentTestListener.logStep("SMART BOT RESPONSE:");
        System.out.println(botReply);
        System.out.println("=================================");
        System.out.println();

        log("Bot Response Received Successfully");
        ExtentTestListener.logStep("Bot Response Received Successfully");
    }


    //=========================================================
    // Wait For Bot Response
    //=========================================================

    public String waitForBotResponse() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(90));

        try {

            // Wait until Thinking disappears
            wait.until(driver ->
                    driver.findElements(thinking).isEmpty()
            );

            ExtentTestListener.logStep(
                    "SmartBot finished thinking");

            // Wait for bot message
            WebElement response = wait.until(driver -> {

                List<WebElement> elements =
                        driver.findElements(messages);

                WebElement latestMessage = null;

                for (WebElement element : elements) {

                    String text = element.getText();

                    if (text != null && !text.trim().isEmpty()) {

                        // Ignore the question text
                        if (!text.trim().equals("Where is my collector?")) {
                            latestMessage = element;
                        }
                    }
                }

                return latestMessage;
            });

            String responseText = response.getText();

            if (responseText == null ||
                    responseText.trim().isEmpty()) {

                throw new RuntimeException(
                        "SmartBot response is empty");
            }

            return responseText.trim();

        } catch (Exception e) {

            System.out.println(
                    "SmartBot response not received");

            throw e;
        }
    }


    //=========================================================
    // Close SmartBot
    //=========================================================

    public void closeSmartBot() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            WebElement close = wait.until(
                    ExpectedConditions.elementToBeClickable(closeButton));

            close.click();

            ExtentTestListener.logStep(
                    "SmartBot Closed Successfully");

            System.out.println(
                    "SmartBot Closed Successfully");

        } catch (Exception e) {

            System.out.println(
                    "Unable to close SmartBot");

            throw e;
        }
    }
}
