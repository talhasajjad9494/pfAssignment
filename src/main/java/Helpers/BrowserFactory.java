package Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserFactory {
    static WebDriver driver;

    public static WebDriver startBrowser(String browserName, String url) {

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            ops.addArguments("--disable-notifications");
            driver = new ChromeDriver(ops);
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions ops = new FirefoxOptions();
            ops.addPreference("dom.webnotifications.enabled", false);
            driver = new FirefoxDriver(ops);
        } else if (browserName.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            SafariOptions ops = new SafariOptions();
            ops.setCapability("safari:webNotificationsDisabled", true);
            driver = new SafariDriver(ops);
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions ops = new EdgeOptions();
            ops.setCapability("ms:edgeOptions", "--disable-notifications");
            driver = new EdgeDriver(ops);
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
}
