package pages;
import data.EnglishLevelData;
import data.GenderData;
import data.InputFieldData;
import data.WorkGrafData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends AbsBasePage{
    private Logger logger = LogManager.getLogger(ProfilePage.class);

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    public void clearFields(InputFieldData... inputFieldData) {

        for (InputFieldData fieldData : inputFieldData) {
            driver.findElement(By.cssSelector(String.format("input[name='%s']", fieldData.getName()))).clear();
        }
    }

    public void clearFieldsCountryAndEnglish() {
        driver.findElement(By.xpath("//div[@data-num='0']/div/div/button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@data-num='1']/div/div/button[@type='button']")).click();
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//button[@data-value='' and @data-empty='Не указано' and @title='Не выбрано']")).click();
        driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).click();
        driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']" +
                "/parent::*/parent::*//following-sibling::button[contains(text(),'Не указано')]")).click();
    }
    public void inputFioInProfile(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                .sendKeys(data);
    }

    public void inputCountryInProfile() {
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).click();
        logger.info("Страна выбрана");


    }
    public void inputCityInProfile() {
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-title='Город']")));
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Москва')]")).click();
        logger.info("Город выбран");
    }
    public void inputEnglishInProfile(EnglishLevelData englishLevelData) {
        WebElement englishLevelSelectElement = driver.findElement(By
                .xpath("//input[@data-title='Уровень знания английского языка']/following-sibling::div"));
        englishLevelSelectElement.click();

        driver.findElement(By.cssSelector(String.format("[title*='%s']", englishLevelData.getName()))).click();
        logger.info("Выбран уровень английского");
    }
    public void chooseReadyToRelocate(boolean isSelected) {
        String relocate = isSelected ? "Да" : "Нет";
        driver.findElement(By.xpath(String.format("//span[@class=\"radio__label\" and text()=\"%s\"]", relocate))).click();
        logger.info("Готовность к переезду");
    }
    public void chooseWorkingFormat(boolean isSelected, WorkGrafData... workGrafs) {
        for(WorkGrafData workGrafData : workGrafs) {
            WebElement inputSelect = driver.findElement(By.cssSelector(String.format("input[title='%s']", workGrafData.getName())));
            if (inputSelect.isSelected() != isSelected) {
                inputSelect.click();
            }
        }

        logger.info("Выбран формат работы");
    }
    public void chooseContactsOne(String contactType, String contactValue) {
        driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"placeholder\" and" +
                " text()=\"Способ связи\"]")));
        driver.findElement(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]")).click();
        driver.findElement(By.xpath(String.format("//*[@name='contact-2-service']/parent::*/parent::*//following-sibling::button[contains(text(),'%s')]", contactType))).click();


        driver.findElement(By.xpath("//*[@id=\"id_contact-2-value\"]")).sendKeys(contactValue);
        logger.info("Добавлен 1 контакт");
        driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();

    }
    public void chooseContactsTwo(String contactType, String contactValue) {
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath(String.format("//*[@name='contact-3-service']/parent::*/parent::*//following-sibling::button[contains(text(),'%s')]", contactType))).click();

        driver.findElement(By.xpath("//*[@id=\"id_contact-3-value\"]")).sendKeys(contactValue);
        logger.info("Добавлен 2 контакт");

    }

    public void chooseGender(GenderData genderData) {
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.cssSelector(String.format("option[value='%s']", genderData.getName()))).click();
        logger.info("Добавлен пол");
    }
    public void clickOnSave() {
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
        WebElement textSave = driver.findElement(By.xpath("//h3"));
        String actualText = textSave.getText().trim();
        Assertions.assertEquals("Навыки и технологии", actualText);
        logger.info("Сохранились");
    }
    public void checkingFields() {
        Assertions.assertTrue(!driver.findElement(By.id("id_lname_latin")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_lname")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_fname_latin")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_lname_latin")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_blog_name")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.xpath("//input[@title='День рождения']")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.xpath("//input[@data-title='Город']/following-sibling::div")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/following-sibling::div")).getText().isEmpty());
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_1']")).isSelected());
        Assertions.assertTrue(driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-0-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-1-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_gender")).getAttribute("value").isEmpty());
    }
}