package BaseTest;

import Helpers.Helper;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void initializeDriver() throws Exception {
        Helper.createInstance();
    }
}
