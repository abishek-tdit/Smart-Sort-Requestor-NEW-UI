package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.N3VerifyReferralPointsFlow.ALogoutPage;
import com.AndroidTest.N3VerifyReferralPointsFlow.B1RequestorLoginPage;
import com.AndroidTest.N3VerifyReferralPointsFlow.B2LoginOTP;
import com.AndroidTest.N3VerifyReferralPointsFlow.CLocationPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class N3VerifyReferralPointsFlowTest
        extends BaseClassMobile {

    @Test
    public void verifyReferralPointsFlow() {

        try {

            // =========================================================
            // LAUNCH SMART SORT APP
            // =========================================================
            driver.activateApp("com.abqaiq.smartsort");
            ExtentTestListener.logStep("Smart Sort App Launched Successfully");


            // MOBILE NUMBER
            String mobile = "0500000055";

            ExtentTestListener.logStep("Referral Points Flow Started");


            // LOGOUT
            ALogoutPage logoutPage = new ALogoutPage();
            logoutPage.logout();


            // OTP OBJECT
            B2LoginOTP loginOTP = new B2LoginOTP();


            // CAPTURE OTP BASELINE BEFORE LOGIN
            long previousOtpId = loginOTP.getLatestOtpId(mobile);

            System.out.println("OTP baseline captured : " + previousOtpId);


            // LOGIN
            B1RequestorLoginPage loginPage = new B1RequestorLoginPage(driver);
            loginPage.login(mobile);


            // GET NEW LOGIN OTP
            String otp = loginOTP.getLoginOTP(mobile, previousOtpId);

            // =====================================================
            // MANUAL OTP ENTRY
            // =====================================================

            System.out.println();
            System.out.println("****************************************");
            System.out.println("ENTER NEW LOGIN OTP MANUALLY");
            System.out.println("LOGIN OTP : " + otp);
            System.out.println("****************************************");
            System.out.println();

            ExtentTestListener.logStep("New Login OTP retrieved. Please enter OTP manually : " + otp);

            waitForLoginCompletion();

            // LOCATION
            CLocationPage locationPage = new CLocationPage(driver);
            locationPage.selectLocation();

            ExtentTestListener.logStep("N3 Verify Referral Points Flow Completed");
        }
        catch (Exception e) {

            ExtentTestListener.logStep("N3 Verify Referral Points Flow Failed : "
                            + e.getMessage());

            throw new RuntimeException("N3 Verify Referral Points Flow Failed", e);
        }
    }


    // WAIT FOR LOGIN COMPLETION
    private void waitForLoginCompletion() {

        System.out.println();
        System.out.println("Waiting for manual OTP entry and login completion...");

        ExtentTestListener.logStep("Waiting for manual OTP entry and login completion");

        try {

            new WebDriverWait(driver, Duration.ofSeconds(120)).until
                    (ExpectedConditions.presenceOfElementLocated
                            (AppiumBy.accessibilityId("Request Now")));

            System.out.println("Login completed successfully");

            ExtentTestListener.logStep("Login completed successfully");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Login was not completed after manual OTP entry. "
                            + "Request Now was not available.", e);
        }
    }
}