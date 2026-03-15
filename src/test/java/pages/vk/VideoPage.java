package pages.vk;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.sleep;

public class VideoPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(VideoPage.class);

    private final By videoContainer = By.id("com.vk.vkvideo:id/playerContainer");
    private final By videoTimer = By.id("com.vk.vkvideo:id/current_progress");

    public VideoPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isVideoTimerChanging() {
        log.info("Checking if timer value changed");
        waitForElementDisplayed(videoContainer, 15);
        sleep(3000);

        click(videoContainer);
        waitForElementDisplayed(videoTimer, 10);
        String initialValue = driver.findElement(videoTimer).getText();
        log.info("Initial timer value: {}", initialValue);

        sleep(5000);

        click(videoContainer);
        waitForElementDisplayed(videoTimer, 10);
        String updatedValue = driver.findElement(videoTimer).getText();
        log.info("Updated timer value: {}", updatedValue);

        if (!updatedValue.equals(initialValue)) {
            return true;
        }

        log.warn("Video timer didn't update (initial={}, updated={})", initialValue, updatedValue);
        return false;
    }
}
