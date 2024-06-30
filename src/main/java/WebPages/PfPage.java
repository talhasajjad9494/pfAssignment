package WebPages;

import Helpers.Helper;
import Helpers.Waits;
import WebElementPaths.WebElementsPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PfPage extends Helper {
    public PfPage(WebDriver localDriver) {
        driver = localDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = WebElementsPaths.logo)
    static WebElement logo;
    static String anchorLinks = "//ul[@id = 'top-menu']//li//a";
    List<WebElement> links = driver.findElements(By.xpath(anchorLinks));
    @FindBy(css = WebElementsPaths.applyNowHeaderButton)
    static WebElement applyNowHeaderButton;
    @FindBy(css = WebElementsPaths.applyButton)
    static WebElement applyButton;
    @FindBy(css = WebElementsPaths.dobInputField)
    static WebElement dobInputField;
    @FindBy(css = WebElementsPaths.fullNameField)
    static WebElement fullNameField;
    @FindBy(css = WebElementsPaths.emailField)
    static WebElement emailField;
    @FindBy(css = WebElementsPaths.cnicField)
    static WebElement cnicField;
    @FindBy(css = WebElementsPaths.phoneField)
    static WebElement phoneField;
    @FindBy(css = WebElementsPaths.addressField)
    static WebElement addressField;
    @FindBy(css = WebElementsPaths.cityField)
    static WebElement cityField;
    @FindBy(css = WebElementsPaths.universityField)
    static WebElement universityField;
    @FindBy(css = WebElementsPaths.cgpaField)
    static WebElement cgpaField;
    @FindBy(css = WebElementsPaths.expectedSalaryField)
    static WebElement expectedSalaryField;
    @FindBy(css = WebElementsPaths.dojInputField)
    static WebElement dojInputField;
    @FindBy(css = WebElementsPaths.hearAboutUs)
    static WebElement hearAboutUs;
    @FindBy(css = WebElementsPaths.resumeField)
    static WebElement resumeField;


    public static String scrollToBottom = "window.scrollTo(0, document.body.scrollHeight)";
    public static String windowOpen = "window.open()";
    public static String moveToElement = "arguments[0].scrollIntoView(true);";

    public void scrollAndAsserLogo() {
        System.out.println(driver.getTitle());
        javaScriptExecutor(driver, scrollToBottom);
        Waits.waitForElementUntilVisibility(driver, logo, 30);
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }

    public void openLinkAndCaptureScreenshots() throws IOException {
        System.out.println(links.size());
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            javaScriptExecutor(driver, windowOpen);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.get(url);
            screenCapture("pf", dateFormat.format(new Date()));
            driver.close();
            driver.switchTo().window(tabs.getFirst());
        }
    }

    public void applyNow() throws IOException {
        Waits.waitForElementToBeClickAble(driver, applyNowHeaderButton, 30);
        Waits.clickButton(driver, applyNowHeaderButton, 30);
        javaScriptExecutor(driver, moveToElement, applyButton);
        Waits.waitForElementToBeClickAble(driver, applyButton, 30);
        Waits.clickButton(driver, applyButton, 30);
        javaScriptExecutor(driver, moveToElement, dobInputField);
        String fullName = faker.name().fullName();
        Waits.sendKeys(driver, fullNameField, fullName, 30);
        String email = faker.internet().emailAddress();
        Waits.sendKeys(driver, emailField, email, 30);
        String cnicNumber = faker.number().digits(13);
        Waits.sendKeys(driver, cnicField, cnicNumber, 30);
        String contactNumber = faker.number().digits(11);
        Waits.sendKeys(driver, phoneField, contactNumber, 30);
        String address = faker.address().fullAddress();
        Waits.sendKeys(driver, addressField, address, 30);
        String city = faker.address().city();
        Waits.sendKeys(driver, cityField, city, 30);
        String university = faker.university().name();
        Waits.sendKeys(driver, universityField, university, 30);
        String cgpa = faker.number().digits(1);
        Waits.sendKeys(driver, cgpaField, cgpa, 30);
        String expectedSalary = faker.number().digits(6);
        Waits.sendKeys(driver, expectedSalaryField, expectedSalary, 30);
        String hear = faker.programmingLanguage().name();
        Waits.sendKeys(driver, hearAboutUs, hear, 30);
    }
}
