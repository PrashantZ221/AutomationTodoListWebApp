package utils;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class TestUtils extends BaseTest {

    public static void takeScreenshot(String fileName) throws IOException {
        // Take screenshot and store as a file format
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // now copy the screenshot to desired location using copyFile //method
        FileUtils.copyFile(src,
                new File((System.getProperty("user.dir") + "/screenshots/" + fileName + "_" + ".png")));

    }
}
