package Helpers;

import net.datafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Helper {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static WebDriver driver;
    public static JavascriptExecutor javascriptExecutor;
    public static Faker faker;

    public Helper() {
        Locale locale = Locale.forLanguageTag("en-AU");
        faker = new Faker(locale);
        faker = new Faker();
    }

    public static void createInstance() throws IOException {
        String url = Utility.getValue("Launch", "Website");
        String browser = Utility.getValue("Launch", "Browser");
        driver = BrowserFactory.startBrowser(browser, url);
    }

    // Global function to execute JavaScript Executor
    public static void javaScriptExecutor(WebDriver driver, String script) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script);
    }

    public static void javaScriptExecutor(WebDriver driver, String script, WebElement element) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script, element);
    }

    public static void screenCapture(String folderName, String fileName) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File sourceImage = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File("./ScreenShots/" + folderName + "/" + fileName + ".png");
        try {
            FileUtils.copyFile(sourceImage, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
