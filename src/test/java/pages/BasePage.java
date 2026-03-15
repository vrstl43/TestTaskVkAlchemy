package pages;

import com.codeborne.selenide.Condition;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        $(locator)
                .shouldBe(Condition.visible)
                .click();
    }

    protected void waitForElementDisplayed(By locator, int timeoutSeconds) {
        $(locator).shouldBe(Condition.visible, Duration.ofSeconds(timeoutSeconds));
    }

    protected boolean isElementPresent(By locator) {
        try {
            $(locator).shouldBe(Condition.visible);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}