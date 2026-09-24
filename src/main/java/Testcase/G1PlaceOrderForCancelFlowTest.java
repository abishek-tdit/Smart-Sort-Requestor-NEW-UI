package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.G1PlaceOrderForCancelFlow.*;
import org.testng.annotations.Test;

public class G1PlaceOrderForCancelFlowTest extends BaseClassMobile {

    @Test
    public void placeOrderForCancelFlow() throws Exception {

        // 1. Select Location
        new ALocationPage(driver).selectLocation();

        // 2. Doorstep Pickup
        new BDoorPickupPage().selectDoorstepPickupFlow();

        // 3. Select Slot + Confirm Order + OK
        CSlotPage slotPage = new CSlotPage();
        slotPage.selectMaterialCategory();
        slotPage.selectSlotConfirmAndOK();

        // 4. Save Recently Placed Order Number
        Thread.sleep(10000);
        new DSaveOrderNumberForCancel().saveRecentlyPlacedOrderNumber();
    }
}