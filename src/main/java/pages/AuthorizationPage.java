package pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Scanner;

import static tools.PropertyLoader.*;



public class AuthorizationPage extends AbsBasePage{
    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void authorizationUser() {
        String login = loadSystemProperty("login");
        String password = loadSystemProperty("password");

        String clickLoginLocator = "//div[./input[@name='email']]";
        String clickPassLocator = "//div[./input[@type='password']]";
        String inputEmailLocator = "//input[@name='email']";
        String inputPassLocator = "//input[@type='password']";
        driver.findElement(By.xpath(clickLoginLocator)).click();
        driver.findElement(By.xpath(inputEmailLocator)).sendKeys(login);
        driver.findElement(By.xpath(clickPassLocator)).click();
        driver.findElement(By.xpath(inputPassLocator)).sendKeys(password);
        driver.findElement(By.cssSelector("#__PORTAL__ button")).click();
        Assertions.assertTrue(waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[src*='blue-owl']"))));
    }


}