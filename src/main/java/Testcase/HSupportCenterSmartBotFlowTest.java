package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.HSupportCenterSmartBotFlow.AChatWithAI;
import com.AndroidTest.HSupportCenterSmartBotFlow.BOpenSmartBotPage;
import com.AndroidTest.HSupportCenterSmartBotFlow.CChatConversationPage;
import org.testng.annotations.Test;

public class HSupportCenterSmartBotFlowTest extends BaseClassMobile {

    @Test
    public void chatWithAI() throws InterruptedException {

        ExtentTestListener.logStep("========== SMARTBOT TEST STARTED ==========");

        // 1. Scroll and click Chat Now
        AChatWithAI chatNow = new AChatWithAI();
        chatNow.clickChatNow();

        // 2. Open SmartBot and start new chat
        BOpenSmartBotPage smartBot = new BOpenSmartBotPage(driver);
        smartBot.openSmartBotAndStartChat();

        // 3. Ask question and wait for response
        CChatConversationPage chatPage = new CChatConversationPage(driver);
        chatPage.askQuestion("Where is my collector?");
        chatPage.closeSmartBot();

        ExtentTestListener.logStep("Question 1 Completed");
        ExtentTestListener.logStep("========== SMARTBOT TEST COMPLETED ==========");

        Thread.sleep(5000);
    }
}