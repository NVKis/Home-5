package factory;

import common.AbsCommon;
import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class DriverFactory extends AbsCommon {
    private static final String BROWSER_NAME = "1";//System.getProperty("browser.name", "chrome").toLowerCase();

    public DriverFactory(WebDriver driver) {
        super(driver);
    }

    public static WebDriver create() {
        switch (BROWSER_NAME) {
            case "chrome":
                return createChromeDriver();
            default:
                return new ChromeDriver();
        }
    }

    private static final String SELENOID_URL = System.getProperty("remoteURL", "http://193.104.57.173:4444/wd/hub");


    private static WebDriver createChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability("selenoid:options", Map.of("enableVNC", true));

        URL selenoidUrl;
        try {
            selenoidUrl = new URL(SELENOID_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Некорректный URL Selenoid", e);
        }

        return new RemoteWebDriver(selenoidUrl, capabilities);
    }
}
