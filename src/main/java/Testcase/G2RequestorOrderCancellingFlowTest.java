package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.G2RequestorOrderCancellingFlow.AOrderForCancel;
import org.testng.annotations.Test;

public class G2RequestorOrderCancellingFlowTest extends BaseClassMobile {

    @Test
    public void requestorOrderCancellingFlow() throws InterruptedException {

        new AOrderForCancel().cancelSavedOrder();
    }
}