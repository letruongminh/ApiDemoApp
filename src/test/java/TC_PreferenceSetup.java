import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC_PreferenceSetup {
    AndroidDriver<AndroidElement> driver;

    @Test
    public void submitNewWifiName() throws MalformedURLException {
        driver = capabilities();
        WebElement preferenceButton = driver.findElement(By.cssSelector("[content-desc='Preference']"));
        preferenceButton.click();

        WebElement preferenceDependenciesButton = driver.findElement(By.cssSelector("[content-desc*='Preference dependencies']"));
        preferenceDependenciesButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement wifiCheckbox = driver.findElement(By.id("android:id/checkbox"));
        wifiCheckbox.click();

        List<AndroidElement> wifiSettings = driver.findElements(By.id("android:id/title"));
        wifiSettings.get(2).click();

        WebElement editWifiSettingsTextbox = driver.findElement(By.id("android:id/edit"));
        editWifiSettingsTextbox.clear();
        editWifiSettingsTextbox.sendKeys("Minh Le");

        WebElement submitButton = driver.findElement(By.id("android:id/button1"));
        submitButton.click();
    }

    @Test
    public void cancelSubmitWifiSettings() throws MalformedURLException {
        driver = capabilities();
        WebElement preferenceButton = driver.findElement(By.cssSelector("[content-desc='Preference']"));
        preferenceButton.click();

        WebElement preferenceDependenciesButton = driver.findElement(By.cssSelector("[content-desc*='Preference dependencies']"));
        preferenceDependenciesButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement wifiCheckbox = driver.findElement(By.id("android:id/checkbox"));
        wifiCheckbox.click();

        List<AndroidElement> wifiSettings = driver.findElements(By.id("android:id/title"));
        wifiSettings.get(2).click();

        WebElement submitButton = driver.findElement(By.id("android:id/button2"));
        submitButton.click();
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

        return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }
}
