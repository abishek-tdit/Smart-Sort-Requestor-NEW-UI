package com.AndroidTest.N3VerifyReferralPointsFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ALogoutPage extends BaseClassMobile {

    private final WebDriverWait wait;

    public ALogoutPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =====================================================
    // LOCATORS
    // =====================================================

    private final By ACCOUNT =
            By.xpath("//android.view.View[@content-desc='Account']");

    private final By LOGOUT =
            By.xpath("//android.view.View[@content-desc='Logout']");

    private final By YES =
            By.xpath("//android.widget.Button[@content-desc='Yes']");


    // =====================================================
    // LOGOUT FLOW
    // =====================================================

    public void logout() {

        try {

            // -------------------------------------------------
            // Click Account
            // -------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(ACCOUNT)
            ).click();

            ExtentTestListener.logStep("Clicked Account");


            // -------------------------------------------------
            // Click Logout
            // -------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(LOGOUT)
            ).click();

            ExtentTestListener.logStep("Clicked Logout");


            // -------------------------------------------------
            // Click Yes
            // -------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(YES)
            ).click();

            ExtentTestListener.logStep("Clicked Yes");


            // -------------------------------------------------
            // Completed
            // -------------------------------------------------

            ExtentTestListener.logStep("Logout Completed");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Logout Failed : " + e.getMessage()
            );

            throw new RuntimeException(
                    "Logout Flow Failed",
                    e
            );
        }
    }
}
