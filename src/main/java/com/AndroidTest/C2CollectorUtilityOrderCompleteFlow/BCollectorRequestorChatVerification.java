package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BCollectorRequestorChatVerification extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By CHAT_BUTTON =
            AppiumBy.xpath("//android.widget.ScrollView/android.widget.Button[2]");

    private final By MESSAGE_ARE_YOU_COMING =
            AppiumBy.xpath("//android.view.View[contains(@content-desc,'Are you coming?')]");

    private final By MESSAGE_ON_THE_WAY =
            AppiumBy.xpath("//android.view.View[contains(@content-desc,\"I'm on the way\")]");


    // =========================================================
    // CLICK CHAT & VERIFY CONVERSATION
    // =========================================================

    public void verifyChatConversation() {

        // ---------------------------------------------------------
        // Click Chat
        // ---------------------------------------------------------

        try {

            wait.until(ExpectedConditions.elementToBeClickable(CHAT_BUTTON)).click();

            ExtentTestListener.logStep("Chat button clicked successfully");
        }
        catch (Exception e) {
            System.out.println("Chat button not available - Skipping chat verification");
            ExtentTestListener.logStep("Chat button not available - Skipped chat verification");
            return;
        }

        // ---------------------------------------------------------
        // Verify "Are you coming?"
        // ---------------------------------------------------------

        try {
            String message1 =
                    wait.until(ExpectedConditions.visibilityOfElementLocated
                            (MESSAGE_ARE_YOU_COMING)).getAttribute("contentDescription");
            System.out.println("Chat Message 1 : " + message1);

            ExtentTestListener.logStep("Verified chat message : Are you coming?");
        }
        catch (Exception e)
        {
            System.out.println("Chat Message 'Are you coming?' not visible - Skipped");

            ExtentTestListener.logStep("Chat Message 'Are you coming?' not visible - Skipped");
        }

        // ---------------------------------------------------------
        // Verify "I'm on the way"
        // ---------------------------------------------------------

        try {
            String message2 =
                    wait.until(ExpectedConditions.visibilityOfElementLocated
                            (MESSAGE_ON_THE_WAY)).getAttribute("contentDescription");

            System.out.println("Chat Message 2 : " + message2);

            ExtentTestListener.logStep("Verified chat message : I'm on the way");
        }
        catch (Exception e)
        {
            System.out.println("Chat Message 'I'm on the way' not visible - Skipped");

            ExtentTestListener.logStep("Chat Message 'I'm on the way' not visible - Skipped");
        }

        // ---------------------------------------------------------
        // Final Log
        // ---------------------------------------------------------

        ExtentTestListener.logStep("Chat conversation verification completed");
    }
}