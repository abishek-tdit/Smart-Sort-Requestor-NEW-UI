package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.BSmartSortRegistrationArabicFlow.ASignUpArabicPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BSmartSortRegistrationArabicFlowTest
        extends BaseClassMobile {

    @Test(priority = 2)
    public void registrationArabicFlow() {

        try {

            System.out.println("========== ARABIC REGISTRATION TEST STARTED ==========");

            ExtentTestListener.logStep("Arabic Registration Flow Started");


            // ARABIC SIGN UP
            ASignUpArabicPage signUpPage = new ASignUpArabicPage(driver);
            signUpPage.signUp();

            ExtentTestListener.logStep("Arabic Registration Flow Completed Successfully");

            System.out.println("========== ARABIC REGISTRATION TEST COMPLETED ==========");

        } catch (Exception e) {

            e.printStackTrace();

            ExtentTestListener.logStep("Arabic Registration Flow Failed : "
                            + e.getMessage());

            Assert.fail("Arabic Registration Flow Failed : "
                            + e.getMessage());
        }
    }
}