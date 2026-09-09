package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow.ADirectQuantityOrdersPage;
import com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow.BCompleteOrder;
import com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow.CLogoutPage;
import org.testng.annotations.Test;

public class D2CollectionCenterDirectDeliveryOrderFlowTest extends BaseClassMobile {

    @Test(priority = 9)
    public void GCollectionCenterDirectDeliveryOrderFlow() throws Exception {

        //Launch SS Utility App
        driver.activateApp("com.smartsort.utilities");
        ExtentTestListener.logStep("SS Utility App Launched Successfully");

        //Direct - Order:
        ADirectQuantityOrdersPage ordersPage = new ADirectQuantityOrdersPage();
        ordersPage.clickDirectQuantityOrders();
        ordersPage.clickRecentOrder();
        ordersPage.clickProceedToComplete();
        ordersPage.clickProceedOrder();
        ordersPage.enterAluminiumKg("10");

        //Complete - Order:
        BCompleteOrder completeOrder = new BCompleteOrder();
        completeOrder.clickSubmit();
        completeOrder.clickProceed();
        completeOrder.clickOkPopup();

        //Logout:
        CLogoutPage logoutPage = new CLogoutPage(driver);
        logoutPage.logout();

        ExtentTestListener.logStep("Collection Center Direct Delivery Order Flow Completed");
    }
}