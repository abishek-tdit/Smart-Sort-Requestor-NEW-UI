package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.LCustomerSupportFlow.ACallSupportPage;
import com.AndroidTest.LCustomerSupportFlow.BContactUsPage;
import org.testng.annotations.Test;

public class LCustomerSupportFlowTest extends BaseClassMobile {

    @Test
    public void callSupportCenterTest() {

        System.out.println("========== CALL SUPPORT TEST STARTED ==========");

        // Call Support Center Flow
        ACallSupportPage callSupportPage = new ACallSupportPage();
        callSupportPage.callSupportCenter();

        // Contact Us Flow
        BContactUsPage contactUsPage = new BContactUsPage();
        contactUsPage.contactUsFlow();

        System.out.println("========== CALL SUPPORT TEST COMPLETED ==========");
    }
}
