import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class TC_ViewsSetup {
    AndroidDriver driver;

    @Test
    public void doSampleActionsInViews() throws MalformedURLException {
        driver = capabilities();
        WebElement viewsButton = driver.findElement(MobileBy.AccessibilityId("Views"));
        viewsButton.click();

        WebElement expandableList = driver.findElement(MobileBy.AccessibilityId("Expandable Lists"));

        Actions action = new Actions(driver);
        action.click(expandableList).build().perform();

        WebElement customAdapter = driver.findElement(MobileBy.cssSelector("[content-desc*='Custom Adapter']"));
        action.click(customAdapter).perform();

        WebElement peopleNameOption = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='People Names']"));

        TouchAction<?> touchAction = new TouchAction<>(driver).longPress(new LongPressOptions().withElement(element(peopleNameOption)).
                withDuration(Duration.ofSeconds(2)));
        touchAction.perform();

        WebElement sampleActionButton = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Sample action']"));
        sampleActionButton.click();

        WebElement toastClickVerified = driver.findElement(MobileBy.xpath("//android.widget.Toast"));
        String peopleNameClicked = toastClickVerified.getText();
        Assert.assertEquals(peopleNameClicked, "People Names: Group 0 clicked");
    }

    @Test
    public void updateInlineTime() throws MalformedURLException {
        driver = capabilities();
        WebElement viewsButton = driver.findElement(MobileBy.AccessibilityId("Views"));
        viewsButton.click();

        AndroidElement dateWidgets = (AndroidElement) driver.findElementByAccessibilityId("Date Widgets");
        dateWidgets.click();

        AndroidElement inlineClock = (AndroidElement) driver.findElementByAccessibilityId("2. Inline");
        inlineClock.click();

        AndroidElement hourPicker = (AndroidElement) driver.findElementByAccessibilityId("3");
        hourPicker.click();

        AndroidElement minutePicker1 = (AndroidElement) driver.findElementByAccessibilityId("15");
        AndroidElement minutePicker2 = (AndroidElement) driver.findElementByAccessibilityId("45");
        TouchAction<?> action = new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(minutePicker1))
                        .withDuration(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(minutePicker2))
                .release();
        action.perform();

        AndroidElement minuteTextView = (AndroidElement) driver.findElementById("android:id/minutes");
        String actualMinute = minuteTextView.getText();
        Assert.assertEquals(actualMinute, "45");
    }

    public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
        // Open file
        File appDir = new File("src");
        File app = new File(appDir, "main/resources/APIDemos-debug.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "MintEmulatorPixelXL");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability("appPackage", "com.doctoranywhere");
//        cap.setCapability("appActivity", "com.doctoranywhere.activity.loginsignup.LinkMainActivity");
//        cap.setCapability("autoGrantPermissions", "true");

        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }
}
