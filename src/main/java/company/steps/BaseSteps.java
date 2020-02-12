package company.steps;

import company.util.TestProperties;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {
    private static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public static void setUp() throws MalformedURLException {

        switch (properties.getProperty("browser")){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));

                DesiredCapabilities capabilities1 = new DesiredCapabilities();
                capabilities1.setBrowserName("gecko");
                capabilities1.setVersion("73.0");
                capabilities1.setCapability("enableVNC", true);
                capabilities1.setCapability("enableVideo", false);
                capabilities1.setCapability("enableLog", false);
                driver = new RemoteWebDriver(
                        URI.create("http://selenoid.aplana.com:4445/wd/hub/").toURL(),
                        capabilities1);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));

                DesiredCapabilities capabilities2 = new DesiredCapabilities();
                capabilities2.setBrowserName("chrome");
                capabilities2.setVersion("73.0");
                capabilities2.setCapability("enableVNC", true);
                capabilities2.setCapability("enableVideo", false);
                capabilities2.setCapability("enableLog", false);
                driver = new RemoteWebDriver(
                        URI.create("http://selenoid.aplana.com:4445/wd/hub/").toURL(),
                        capabilities2);
                break;
        }
        baseUrl = properties.getProperty("app.url");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    @After
    public static void tearDown() {
        driver.quit();
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void goToCalculatorFrame() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().switchTo().frame(0);
    }

    public void exitCalculatorFrame() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().switchTo().defaultContent();
    }

    public void scrollToSelectors() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor jse = (JavascriptExecutor)BaseSteps.getDriver();
        jse.executeScript("window.scrollBy(0,500)");
    }
}
