package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;

import com.AndroidTest.F1PlaceOrderForRescheduleFlow.*;

import org.testng.annotations.Test;

public class F1PlaceOrderForRescheduleFlowTest extends BaseClassMobile {

    @Test(priority = 11)
    public void placeOrderForRescheduleFlow() throws Exception {

//        System.out.println("================================================");
//        System.out.println("F1 Place Order For Reschedule Flow Started");
//        System.out.println("================================================");
//
//        //LOCATION
//        ALocationPage locationPage = new ALocationPage(driver);
//        locationPage.selectLocation();
//
//        //DOORSTEP PICKUP
//        BDoorPickupPage doorPickupPage = new BDoorPickupPage();
//        doorPickupPage.selectDoorstepPickupFlow();
//
//        //ADDRESS
//        CAddressPage addressPage = new CAddressPage();
//        addressPage.updateAddressFromSavedAddress();
//
//        //SLOT
//        DSlotPage slotPage = new DSlotPage();
//        slotPage.selectSlotConfirmAndOK();

        //SAVE ORDER NUMBER
        ESaveOrderNumberForReschedule saveOrder = new ESaveOrderNumberForReschedule();
        saveOrder.saveRecentlyPlacedOrderNumber();

//        System.out.println("================================================");
//        System.out.println("F1 Place Order For Reschedule Flow Completed");
//        System.out.println("================================================");

        ExtentTestListener.logStep("F1 Place Order For Reschedule Flow Completed Successfully");
    }
}