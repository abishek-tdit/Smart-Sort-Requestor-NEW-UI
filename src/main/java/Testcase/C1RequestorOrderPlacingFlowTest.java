package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.C1RequestorOrderPlacingFlow.*;
import org.testng.annotations.Test;

import static utilities.MobileUtilities.generatedPassword;

public class C1RequestorOrderPlacingFlowTest extends BaseClassMobile {

    @Test(priority = 3)
    public void smartSortOrderFlow() throws Exception {

//        //Login
//        ALoginPage loginPage = new ALoginPage(driver);
//        loginPage.login("0500000555",
//                          generatedPassword);
//
//        ExtentTestListener.logStep("Requestor Login Successful");

        //Location
        CLocationPage locationPage = new CLocationPage(driver);
        locationPage.selectLocation();

        //Door Pickup
        DDoorPickupPage doorPickupPage = new DDoorPickupPage(driver);
        doorPickupPage.selectDoorstepPickupFlow();

//        //Address
//        EAddressPage addressPage = new EAddressPage(driver);
//        addressPage.updateAddressFromSavedAddress();

        //Slot Selection
        Thread.sleep(2000);
        FSlotPage slotPage = new FSlotPage();

        slotPage.selectSlotConfirmAndOK();


        //save order number
        Thread.sleep(5000);
        GSaveOrderNumberForDoorPickup saveOrder = new GSaveOrderNumberForDoorPickup();
        saveOrder.saveRecentlyPlacedOrderNumber();

        //Requestor To Collector Chat
        HRequestorToCollectorChat chatFlow = new HRequestorToCollectorChat();
        chatFlow.executeChatFlow();

        ExtentTestListener.logStep("Smart Sort Order Placing Flow Completed");
    }
}