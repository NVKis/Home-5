package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.JSHelper;

public class Header extends AbsCommon {
    public Header(WebDriver driver) {
        super(driver);
    }
    JSHelper jsHelper = new JSHelper(driver);

    public void clickButtonEnter() {
        waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=\"Войти\"]")));
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }
    public void popUp() {
        execWithJS(By.xpath("//img[@src=\"/_next/static/images/img/blue-owl-058dcbc134c3f34a7d9d5d52ecfced60.png\"]"));
        execWithJS(By.xpath("//div/a[@href='https://otus.ru/lk/biography/personal']"));
    }

    public void execWithJS(By by){
        WebElement county = driver.findElement(by);
        jsHelper.scrollToElement(county);
        jsHelper.clickElementWithJS(county);
    }
}
