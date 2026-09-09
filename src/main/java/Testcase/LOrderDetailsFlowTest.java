package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.LOrderDetailsFlow.APendingOrders;
import com.AndroidTest.LOrderDetailsFlow.BCompletedPage;
import com.AndroidTest.LOrderDetailsFlow.CRescheduledPage;
import com.AndroidTest.LOrderDetailsFlow.DCancelledPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

public class LOrderDetailsFlowTest extends BaseClassMobile {

    @Test(priority = 1, description = "Validate first 5 Pending Orders")
    public void validateFirstFivePendingOrders() {

        AndroidDriver driver = getDriver();

        //PENDING
        APendingOrders pendingOrders = new APendingOrders(driver);
        pendingOrders.validateFirstFivePendingOrders();

        //COMPLETED
        BCompletedPage completedPage = new BCompletedPage(driver);
        completedPage.validateFirstFiveCompletedOrders();

        //RESCHEDULE
        CRescheduledPage rescheduledPage = new CRescheduledPage(driver);
        rescheduledPage.checkRescheduledOrders();

        //CANCELLED
        DCancelledPage cancelledPage = new DCancelledPage(driver);
        cancelledPage.validateFirstFiveCancelledOrders();
    }
}