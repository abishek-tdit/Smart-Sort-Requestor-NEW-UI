package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;

import com.AndroidTest.F1PlaceOrderForRescheduleFlow.*;

import org.testng.annotations.Test;

public class F1PlaceOrderForRescheduleFlowTest extends BaseClassMobile {

    @Test(priority = 11)
    public void placeOrderForRescheduleFlow() throws Exception {

        System.out.println("================================================");
        System.out.println("F1 Place Order For Reschedule Flow Started");
        System.out.println("================================================");

        //LOCATION
        ALocationPage locationPage = new ALocationPage(driver);
        locationPage.selectLocation();

        //DOORSTEP PICKUP
        BDoorPickupPage doorPickupPage = new BDoorPickupPage();
        doorPickupPage.selectDoorstepPickupFlow();

        //SLOT
        CSlotPage slotPage = new CSlotPage();
        slotPage.selectMaterialCategory();
        slotPage.selectSlotConfirmAndOK();

        //SAVE ORDER NUMBER
        Thread.sleep(10000);
        DSaveOrderNumberForReschedule saveOrder = new DSaveOrderNumberForReschedule();
        saveOrder.saveRecentlyPlacedOrderNumber();

        ExtentTestListener.logStep("F1 Place Order For Reschedule Flow Completed Successfully");
    }
}