package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.vk.CatalogVideoPage;
import pages.vk.VideoPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VkVideoTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        startDriver("vk-video");
    }

    @DisplayName("Play video")
    @Test
    void testVideoPlayback() {
        CatalogVideoPage catalogVideoPage = new CatalogVideoPage(driver);

        if (catalogVideoPage.authBottomSheetIsVisible()) {
            catalogVideoPage.skipAuthBottomSheet();
        }

        if (catalogVideoPage.miniPlayerIsVisible()) {
            catalogVideoPage.closeMiniPlayer();
        }

        catalogVideoPage.waitForVideoLoadingFinished();
        catalogVideoPage.clickFirstVideo();

        VideoPage videoPage = new VideoPage(driver);

        assertTrue(videoPage.isVideoTimerChanging());
    }
}
