package tests;

import helpers.AdCloser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.alchemy.HomePageAlchemy;
import pages.alchemy.StartPageAlchemy;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlchemyTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        startDriver("alchemy");
    }

    @Test
    @DisplayName("Получение дополнительных подсказок после просмотра рекламы")
    void testGetHintsAfterWatchingAd() {
        StartPageAlchemy startPage = new StartPageAlchemy(driver);
        startPage.clickPlayButton();

        HomePageAlchemy homePage = new HomePageAlchemy(driver);
        homePage.clickAddHints();

        int initialHints = homePage.getHintsCount();
        homePage.clickWatchAd();

        AdCloser adCloser = new AdCloser(driver);
        boolean closed = adCloser.waitAndCloseAd(Duration.ofSeconds(50));

        if (closed && !homePage.isHintsPageDisplayed()) {
            driver.navigate().back();
        }

        adCloser.sendToBackground();
        adCloser.restoreApp();

        int updatedHints = homePage.getHintsCount();
        assertTrue(updatedHints > initialHints);
    }
}
