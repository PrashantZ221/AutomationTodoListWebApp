package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TestUtils;

import java.io.IOException;

public class ScreenshotOnFailure implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getName();
        try {
            TestUtils.takeScreenshot(methodName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
