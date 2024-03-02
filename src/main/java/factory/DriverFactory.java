package factory;

import common.AbsCommon;
import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory extends AbsCommon {
    private String browserName = System.getProperty("browser.name");

    public DriverFactory(WebDriver driver) {
        super(driver);
    }

    public WebDriver create() {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            }
        }
        throw new BrowserNotSupportedException(browserName);
    }
}
