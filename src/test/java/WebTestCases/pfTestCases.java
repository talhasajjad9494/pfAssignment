package WebTestCases;

import BaseTest.BaseTest;
import Helpers.Helper;
import WebPages.PfPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class pfTestCases extends BaseTest {
    PfPage pfPage;
    void initObj() {
        pfPage = new PfPage(Helper.driver);
    }
    @Test(priority = 1)
    void pfAssignment() throws IOException {
        initObj();
        pfPage.scrollAndAsserLogo();
        pfPage.openLinkAndCaptureScreenshots();
        pfPage.applyNow();
    }
}
