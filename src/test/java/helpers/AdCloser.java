package helpers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AdCloser {

    private static final Logger log = LoggerFactory.getLogger(AdCloser.class);

    private final AndroidDriver driver;
    private final List<By> closeButtons = new ArrayList<>();

    public AdCloser(AndroidDriver driver) {
        this.driver = driver;
        initCloseButtons();
    }

    private void initCloseButtons() {
        closeButtons.add(By.id("com.google.android.gms:id/close"));
        closeButtons.add(By.id("close_button"));
        closeButtons.add(By.id("iv_close"));
        closeButtons.add(By.id("iv_skip"));
        closeButtons.add(By.xpath("//*[@text='Close']"));
        closeButtons.add(By.xpath("//*[@content-desc='Close']"));
        closeButtons.add(By.xpath("//*[@content-desc='closeButton']"));
        closeButtons.add(By.xpath("//*[contains(@resource-id,'skip')]"));
        closeButtons.add(By.xpath("//*[contains(@resource-id,'close')]"));
        closeButtons.add(By.xpath("//*[@resource-id='m-playable-skip']"));
        closeButtons.add(By.xpath("//*[@resource-id='com.ilyin.alchemy:id/bigo_ad_btn_close']"));
        closeButtons.add(By.xpath("//*[@resource-id='com.ilyin.alchemy:id/mbridge_windwv_close']"));
        closeButtons.add(By.xpath("//*[contains(@text,'Close') or contains(@text,'Skip') or " + "contains(@content-desc,'Close') or contains(@content-desc,'Skip')]"));
        closeButtons.add(By.xpath("//*[@content-desc='pageIndex: 1']//*[@clickable='true']"));
        closeButtons.add(By.xpath("//*[@content-desc='pageIndex: 1']//android.widget.ImageView"));
    }

    public boolean waitAndCloseAd(Duration timeout) {

        log.info("Checking if an ad is visible");

        Wait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(StaleElementReferenceException.class);

        try {
            Boolean result = wait.until(d -> {
                for (By locator : closeButtons) {
                    List<WebElement> elements = d.findElements(locator);
                    if (!elements.isEmpty()) {
                        WebElement el = elements.get(0);
                        if (el.isDisplayed()) {
                            log.info("Ad detected. Closing it using locator: {}", locator);
                            el.click();
                            return true;
                        }
                    }
                }
                return false;
            });

            if (result) {
                log.info("Ad was successfully closed");
                return true;
            }

        } catch (Exception ignored) {
            log.debug("No ad close button appeared during the wait");
        }

        log.info("Ad close button not found. Trying to close the ad using BACK button");

        try {
            driver.navigate().back();
            log.info("BACK button pressed as a fallback");
            return true;
        } catch (Exception e) {
            log.warn("Failed to close ad using BACK button");
        }

        return false;
    }

    public void sendToBackground() {
        log.info("Sending the app to the background for a short time");
        driver.runAppInBackground(Duration.ofSeconds(2));
    }

    public void restoreApp() {
        log.info("Bringing the app back to the foreground");
        driver.activateApp("com.ilyin.alchemy");
    }
}