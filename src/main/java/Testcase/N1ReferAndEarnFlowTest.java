package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.N1ReferAndEarnFlow.*;
import org.testng.annotations.Test;
import utilities.D1ReferEarnReferralCode;

public class N1ReferAndEarnFlowTest extends BaseClassMobile {

    @Test
    public void referAndEarnFlowTest() throws Exception {

        System.out.println("========== REFER & EARN FLOW STARTED ==========");

        // =====================================================
        // 1. SELECT LOCATION
        // =====================================================

        ALocationPage locationPage = new ALocationPage(driver);
        locationPage.selectLocation();

        // =====================================================
        // 2. REFER & EARN
        // =====================================================

        BReferAndEarnPage referAndEarnPage = new BReferAndEarnPage();
        referAndEarnPage.clickReferAFriendAndSaveCode();

        System.out.println("Final Saved Referral Code : "
                        + D1ReferEarnReferralCode.referralCode);

        // =====================================================
        // 3. LOGOUT
        // =====================================================

        CLogoutPage logoutPage = new CLogoutPage();
        logoutPage.logout();

        // =====================================================
        // 4. SIGN UP
        // =====================================================

        D1SignUpPage signUpPage = new D1SignUpPage(driver);
        String generatedMobile = signUpPage.signUp();

        System.out.println("Generated Mobile Number : " + generatedMobile);

        // =====================================================
        // 5. OTP OBJECT
        // =====================================================

        E2LoginOTP loginOTP = new E2LoginOTP();

        // =====================================================
        // 6. CAPTURE OTP BASELINE BEFORE LOGIN
        // =====================================================

        long previousOtpId = loginOTP.getLatestOtpId(generatedMobile);

        System.out.println("OTP baseline captured : " + previousOtpId);

        // =====================================================
        // 7. LOGIN
        // =====================================================

        E1LoginPage loginPage = new E1LoginPage(driver);
        loginPage.login(generatedMobile);

        // =====================================================
        // 8. GET NEW LOGIN OTP
        // =====================================================

        String otp = loginOTP.getLoginOTP(generatedMobile, previousOtpId);

        // =====================================================
        // 9. MANUAL OTP ENTRY
        // =====================================================

        System.out.println();
        System.out.println("****************************************");
        System.out.println("ENTER NEW LOGIN OTP MANUALLY");
        System.out.println("LOGIN OTP : " + otp);
        System.out.println("****************************************");
        System.out.println();

        ExtentTestListener.logStep("New Login OTP retrieved. Please enter OTP manually : " + otp);

        // =====================================================
        // 10. WAIT FOR LOGIN TO COMPLETE
        // =====================================================

        waitForLoginCompletion();

        // =====================================================
        // 11. DOORSTEP PICKUP
        // =====================================================

        FDoorPickupPage doorPickupPage = new FDoorPickupPage();
        doorPickupPage.selectDoorstepPickupFlow();

        // =====================================================
        // 12. SLOT
        // =====================================================

        GSlotPage slotPage = new GSlotPage(driver);
        slotPage.completeSlotFlow();

        // =====================================================
        // 13. SAVE ORDER
        // =====================================================

        Thread.sleep(10000);

        HSaveOrderNumberForReferEarn saveOrder = new HSaveOrderNumberForReferEarn();

        saveOrder.saveRecentlyPlacedOrderNumber();

        System.out.println("Saved Order Number : "
                + utilities.D2OrderDataReferEarn.orderNumberReferEarn);
    }

    // =====================================================
    // WAIT FOR LOGIN / HOME SCREEN
    // =====================================================

    private void waitForLoginCompletion() {

        System.out.println();
        System.out.println("Waiting for manual OTP entry and login completion...");

        ExtentTestListener.logStep("Waiting for manual OTP entry and login completion");

        try {

            new org.openqa.selenium.support.ui.WebDriverWait(driver,
                    java.time.Duration.ofSeconds(120)).until
                    (org.openqa.selenium.support.ui.ExpectedConditions
                            .presenceOfElementLocated
                                    (io.appium.java_client.AppiumBy.accessibilityId("Request Now")));

            System.out.println("Login completed successfully");

            ExtentTestListener.logStep("Login completed successfully - Request Now available");
        }
        catch (Exception e) {

            throw new RuntimeException("Login was not completed after manual OTP entry. "
                            + "Request Now was not available.", e);
        }
    }
}