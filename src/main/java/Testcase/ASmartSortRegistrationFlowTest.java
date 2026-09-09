package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.ASmartSortRegistrationFlow.ASignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ASmartSortRegistrationFlowTest extends BaseClassMobile {

    @Test(priority = 1)
    public void registrationFlow() {
        try {

            ExtentTestListener.logStep("Registration Flow Started");

            //Sign Up:
            ASignUpPage signUpPage = new ASignUpPage(driver);
            signUpPage.signUp();

            ExtentTestListener.logStep("Registration Flow Completed Successfully");
            Assert.assertTrue(true, "Registration completed successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Registration Flow Failed : " + e.getMessage());
        }
    }
}