package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.C4CollectorNavigateToCollectionCenterFlow.ALoginPage;
import com.AndroidTest.C4CollectorNavigateToCollectionCenterFlow.BCollectorNavigateToCollectionCenterDOPage;
import com.AndroidTest.C4CollectorNavigateToCollectionCenterFlow.CLogoutPage;
import org.testng.annotations.Test;

import static utilities.MobileUtilities.generatedPassword;

public class C4CollectorNavigateToCollectionCenterFlowTest extends BaseClassMobile {

    @Test(priority = 6)
    public void collectorNavigateToCollectionCenterTest() throws Exception {

        //Rahul  : 0500055447
        //TDIT   : 0500000654

        // Login:
        ALoginPage loginPage = new ALoginPage();
        loginPage.login("0500055447",
                generatedPassword);

        //Collector Navigate To Collection Center:
        BCollectorNavigateToCollectionCenterDOPage doPage = new BCollectorNavigateToCollectionCenterDOPage();
        doPage.openAcceptedDeliveryOrders();

        //Logout:
        CLogoutPage logoutPage = new CLogoutPage(driver);
        logoutPage.clearStorage();

        ExtentTestListener.logStep("Collector  Navigate 3rd  Flow Completed Successfully");
    }
}