package pages.vk;

import com.codeborne.selenide.Condition;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class CatalogVideoPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CatalogVideoPage.class);

    private final By videoLoadingPlaceholder = By.id("com.vk.vkvideo:id/video_card1");
    private final By firstVideoPreview = By.id("com.vk.vkvideo:id/content");
    private final By authBottomSheet = By.id("com.vk.vkvideo:id/fast_login_view");
    private final By authBottomSheetSkipButton = By.id("com.vk.vkvideo:id/fast_login_tertiary_btn");
    private final By miniPlayer = By.id("com.vk.vkvideo:id/playerContainer");
    private final By miniPlayerCloseButton = By.id("com.vk.vkvideo:id/closeView");

    public CatalogVideoPage(AndroidDriver driver) {
        super(driver);
    }

    public void clickFirstVideo() {
        log.info("Click first video preview");
        click(firstVideoPreview);
    }

    public void waitForVideoLoadingFinished() {
        log.info("Waiting for video loading placeholder to disappear");
        $(videoLoadingPlaceholder).shouldBe(Condition.disappear);
    }

    public boolean authBottomSheetIsVisible() {
        log.info("Check element view visibility");
        return isElementPresent(authBottomSheet);
    }

    public void skipAuthBottomSheet() {
        log.info("Close onboarding view");
        click(authBottomSheetSkipButton);
    }

    public boolean miniPlayerIsVisible(){
        log.info("Check mini player view visibility");
        return isElementPresent(miniPlayer);
    }

    public void closeMiniPlayer(){
        log.info("Close mini player");
        click(miniPlayerCloseButton);
    }
}