package pages.alchemy;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class StartPageAlchemy extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(StartPageAlchemy.class);

    private final By playButton = By.xpath("//x2.f1/android.view.View/android.view.View/android.view.View[1]/android.view.View[5]");

    public StartPageAlchemy(AndroidDriver driver) {
        super(driver);
    }

    public void clickPlayButton() {
        log.info("Click play button");
        click(playButton);
    }
}
