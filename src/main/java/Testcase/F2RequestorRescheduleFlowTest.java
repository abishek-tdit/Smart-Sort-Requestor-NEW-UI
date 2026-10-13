package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.F2RequestorRescheduleFlow.*;
import org.testng.annotations.Test;
import utilities.BOrderDataForReschedule;

import static com.sun.activation.registries.LogSupport.log;

public class F2RequestorRescheduleFlowTest  extends BaseClassMobile {

    @Test(priority = 12)
    public void rescheduleFlow() throws Exception {

        String orderNumber = BOrderDataForReschedule.orderNumberForReschedule;

        //Select Location
        Thread.sleep(5000);
        ALocationPage locationPage = new ALocationPage(driver);
        locationPage.selectLocation();

        ExtentTestListener.logStep("Location Selected Successfully");

        //Reschedule
        BOrderForReschedule orderForReschedule = new BOrderForReschedule();
        orderForReschedule.openSavedOrderForReschedule();

        // Slot Selection
        CSlotPage slotPage = new CSlotPage(driver);
        slotPage.selectSlotTiming();
        Thread.sleep(1500);
        slotPage.confirmReschedule();

        ExtentTestListener.logStep("Slot Selected Successfully");
        ExtentTestListener.logStep("========== REQUESTOR RESCHEDULE FLOW COMPLETED ==========");
    }
}