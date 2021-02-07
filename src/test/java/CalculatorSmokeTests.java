import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorSmokeTests {
    /**
     * This is simple Smoke tests executing with Appium on Android emulator
     * You can check running emulators with cmd
     * adb devices
     * Java version 1.8.0_131 is used
     */

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    //Elements
    String secondNewJob = "//android.widget.FrameLayout[2]/android.widget.LinearLayout/" +
            "android.widget.RelativeLayout/android.widget.ImageView";

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "AOSP on IA Emulator");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void testCalculator() throws Exception {
        Thread.sleep(5000);

        WebElement seven = driver.findElementById("com.android.calculator2:id/digit_7");
        seven.click();
        WebElement plus = driver.findElementById("com.android.calculator2:id/op_add");
        plus.click();
        WebElement three = driver.findElementById("com.android.calculator2:id/digit_3");
        three.click();
        WebElement equalTo = driver.findElementById("com.android.calculator2:id/eq");
        equalTo.click();

        WebElement formulaElement = driver.findElementById("com.android.calculator2:id/formula");

        Thread.sleep(2000);

        WebElement results = driver.findElementById("com.android.calculator2:id/result");

        assertResult(results, "10");

        assert results.getText().equals("10") : "Actual value is : " + results.getText() + " did not match with expected value: 10";

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    public void assertResult(WebElement resultElement, String resultValue) {

        if (resultElement.getText().equals(resultValue)) {
            System.out.println("Test Passed...");
            System.out.println("AR is " + resultElement.getText());
            System.out.println("ER is " + resultValue);
        } else {
            System.out.println("Test Failed...");
            System.out.println("AR is " + resultElement.getText());
            System.out.println("ER is " + resultValue);
        }
    }
}
