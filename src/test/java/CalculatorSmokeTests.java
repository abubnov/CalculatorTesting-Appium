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


    //Sum testing 7+3=10
    @Test
    public void sumTest() throws Exception {
        enterNumber("7");
        // addition +
        addition();
        enterNumber("3");
        //equals =
        equals();
        String resultA = result();
        assertResult(resultA, "10");
        assert resultA.equals("10") : "Actual value is : " + resultA + " did not match with expected value: 10";


    }

    //Multiplication testing 12345679*9=111111111
    @Test
    public void multipleTest() throws Exception {
        enterNumber("1");
        enterNumber("2");
        enterNumber("3");
        enterNumber("4");
        enterNumber("5");
        enterNumber("6");
        enterNumber("7");
        enterNumber("9");
        multiply();
        enterNumber("9");
        equals();

        String resultB = result();
        assertResult(resultB, "111,111,111");

        assert resultB.equals("111,111,111") : "Actual value is : " + resultB + " did not match with expected value: 10";

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    public void assertResult(String actualResult, String expectedResulte) {

        if (actualResult.equals(expectedResulte)) {
            System.out.println("Test Passed...");
            System.out.println("AR is " + actualResult);
            System.out.println("ER is " + expectedResulte);
        } else {
            System.out.println("Test Failed...");
            System.out.println("AR is " + actualResult);
            System.out.println("ER is " + expectedResulte);
        }
    }

    public void enterNumber(String value) {
        WebElement numberElement = driver.findElementById("com.android.calculator2:id/digit_" + value);
        numberElement.click();
    }

    //Click on Sum element +
    public void addition() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/op_add");
        plus.click();
    }

    //Click on Minus element -
    public void minus() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/op_sub");
        plus.click();
    }

    //Click on Multiply element *
    public void multiply() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/op_mul");
        plus.click();
    }

    //Click on Divide element /
    public void divide() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/op_div");
        plus.click();
    }

    //Click on DEL element *
    public void del() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/del");
        plus.click();
    }

    //Click on Dec Point element .
    public void decPoint() {
        WebElement plus = driver.findElementById("com.android.calculator2:id/dec_point");
        plus.click();
    }

    //Click on Equal element =
    public void equals() {
        WebElement equalTo = driver.findElementById("com.android.calculator2:id/eq");
        equalTo.click();
    }

    public String result() {
        WebElement results = driver.findElementById("com.android.calculator2:id/result");
        String resultText = results.getText();
        return resultText;
    }

    public String formula() {
        WebElement formulaElement = driver.findElementById("com.android.calculator2:id/formula");
        String formulaText = formulaElement.getText();
        return formulaText;
    }


}
