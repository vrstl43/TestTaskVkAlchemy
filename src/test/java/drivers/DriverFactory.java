package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

public class DriverFactory {

    public static AndroidDriver getDriver(String appName) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:platformVersion", "11");
            caps.setCapability("appium:deviceName", "emulator-5554");
            caps.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
            caps.setCapability("appium:noReset", true);
            caps.setCapability("appium:autoGrantPermissions", true);

            switch (appName) {
                case "vk-video":
                    caps.setCapability("appium:appPackage", "com.vk.vkvideo");
                    break;

                case "alchemy":
                    caps.setCapability("appium:appPackage", "com.ilyin.alchemy");
                    break;

                default:
                    throw new IllegalArgumentException("Unknown app: " + appName);
            }

            return new AndroidDriver(
                    URI.create("http://localhost:4723").toURL(),
                    caps
            );
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать драйвер для app: " + appName, e);
        }
    }
}