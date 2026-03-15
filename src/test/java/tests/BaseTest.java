package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    protected AndroidDriver driver;

    @BeforeAll
    static void setupSelenide() {
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "build/reports/tests";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
    }

    protected void startDriver(String appName) {
        driver = DriverFactory.getDriver(appName);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            try {
                driver.terminateApp(driver.getCurrentPackage());
            } catch (Exception e) {
                log.warn("App didn't terminate: {}", e.getMessage());
            } finally {
                closeWebDriver();
            }
        }
    }

}