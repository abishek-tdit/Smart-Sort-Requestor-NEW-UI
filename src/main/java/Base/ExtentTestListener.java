package Base;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener extends BaseClassMobile implements ITestListener {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.getExtent();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("Test Failed");

        // Take screenshot
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName());

        try {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
        catch (Exception e) {
            test.get().fail("Screenshot attach failed");
        }

        test.get().fail(result.getThrowable());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static ExtentTest getTest() {

        return test.get();
    }

    public static void logStep(String message) {

        System.out.println(message);

        if (getTest() != null) {
            getTest().pass(message);
        }

    }
}