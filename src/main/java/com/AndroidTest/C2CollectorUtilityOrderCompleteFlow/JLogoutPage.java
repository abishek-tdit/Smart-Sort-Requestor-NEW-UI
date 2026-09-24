package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class JLogoutPage extends BaseClassMobile {

    public JLogoutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void logout() throws Exception {


        //CLICK PROFILE
        StepTimer.startStep();

        ExtentTestListener.logStep("Clicking PROFILE...");

        WebElement profile =
                waitUntilClickable(AppiumBy.accessibilityId("PROFILE"), 20);

        profile.click();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("PROFILE clicked successfully");

        ExtentTestListener.logStep("Clicked PROFILE | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // 2. WAIT FOR PROFILE PAGE
        // =========================================================

        Thread.sleep(1000);

        ExtentTestListener.logStep("Profile page opened. Searching for Logout from Device...");


        // =========================================================
        // 3. SCROLL DOWN UNTIL LOGOUT FROM DEVICE IS FOUND
        // =========================================================

        StepTimer.startStep();

        boolean logoutFound = false;

        for (int attempt = 1; attempt <= 8; attempt++) {

            System.out.println("Logout search attempt : " + attempt);

            try {

                WebElement logoutElement =
                        driver.findElement(AppiumBy.accessibilityId("Logout from Device"));

                if (logoutElement.isDisplayed()) {

                    System.out.println("Logout from Device is visible");

                    ExtentTestListener.logStep("Logout from Device visible");

                    logoutFound = true;
                    break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Logout from Device not visible yet");
            }


            // =====================================================
            // SCROLL DOWN
            // =====================================================
            try {
                ExtentTestListener.logStep("Scrolling down...");

                driver.executeScript("mobile: scrollGesture",
                        Map.of("left", 40,
                                "top", 250,
                                "width", 640,
                                "height", 1050,
                                "direction", "down",
                                "percent", 0.65));

                ExtentTestListener.logStep("Scroll completed");
            }
            catch (Exception e)
            {
                System.out.println("Scroll failed : " + e.getMessage());
            }
            Thread.sleep(500);
        }

        duration = StepTimer.endStep();

        // =========================================================
        // 4. FAIL IF LOGOUT WAS NOT FOUND
        // =========================================================

        if (!logoutFound) {

            ExtentTestListener.logStep("ERROR : Logout from Device was NOT found");

            throw new RuntimeException("Logout from Device was NOT found after scrolling");
        }


        // =========================================================
        // 5. CLICK LOGOUT FROM DEVICE
        // =========================================================

        StepTimer.startStep();

        System.out.println("Clicking Logout from Device...");

        WebElement logoutFromDevice =
                waitUntilClickable(AppiumBy.accessibilityId("Logout from Device"), 20);

        logoutFromDevice.click();

        duration = StepTimer.endStep();

        System.out.println("Logout from Device clicked successfully");

        ExtentTestListener.logStep("Clicked Logout from Device | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // 6. WAIT FOR LOGOUT POPUP
        // =========================================================

        Thread.sleep(500);

        System.out.println("Logout confirmation popup displayed");


        // =========================================================
        // 7. CLICK LOGOUT ON POPUP
        // =========================================================

        StepTimer.startStep();

        System.out.println("Clicking Logout on confirmation popup...");

        driver.executeScript("mobile: clickGesture",
                 Map.of("x", 560,
                        "y", 924));

        duration = StepTimer.endStep();

        System.out.println("Logout popup clicked successfully");

        ExtentTestListener.logStep("Logout popup clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");
    }
}