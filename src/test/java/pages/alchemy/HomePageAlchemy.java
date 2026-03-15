package pages.alchemy;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class HomePageAlchemy extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(HomePageAlchemy.class);

    private final By addHintsButton = By.xpath("//x2.f1/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]");
    private final By hintsCountLabel = By.xpath("//x2.f1/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View" + "/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView");
    private final By watchAdButton = By.xpath("//x2.f1/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View" + "/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View");
    private final By hintsTitle = By.xpath("//android.widget.TextView[@text='Your hints']");
    private final By watchAdLabel = By.xpath("//android.widget.TextView[@text='Watch']");

    public HomePageAlchemy(AndroidDriver driver) {
        super(driver);
    }

    public void clickAddHints() {
        log.info("Click add hints button");
        click(addHintsButton);
    }

    public int getHintsCount() {
        log.info("Getting current hints count");
        String text = $(hintsCountLabel).getText();
        return Integer.parseInt(text);
    }

    public boolean isHintsPageDisplayed() {
        return isElementPresent(hintsTitle);
    }

    public void clickWatchAd() {
        log.info("Click watch ad button");
        waitForElementDisplayed(watchAdLabel, 60);
        click(watchAdButton);
    }
}
