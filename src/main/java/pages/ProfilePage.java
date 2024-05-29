package pages;
import data.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.JSHelper;


public class ProfilePage extends AbsBasePage{
    private final Logger logger = LogManager.getLogger(ProfilePage.class);

    JSHelper jsHelper = new JSHelper(driver);

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
        execWithJS(By.xpath("//input[@name='country']//.."));
        execWithJS(By.xpath("(//button[@data-value='' and @data-empty='Не указано' and @title='Не выбрано'])[1]"));
        driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).click();
        driver.findElement(By.xpath("//div[@class = 'lk-cv-block__select-scroll  js-custom-select-options']//*[@title='Не выбрано']"));
    }

    public void inputFioInProfile(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                .sendKeys(data);
    }

    public void execWithJS(By by){
        try {
            Thread.sleep(1500);
            WebElement county = driver.findElement(by);
            jsHelper.scrollToElement(county);
            jsHelper.clickElementWithJS(county);
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
                WebElement el = driver.findElement(By.xpath(String.format("//input[@title = '%s']//..//span", workGrafData.getName())));
                el.click();
            }
        }

        logger.info("Выбран формат работы");
    }

    public void chooseContactsOne(String contactType, String contactValue) {
        driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='placeholder'][text()='Способ связи'])[1]")));
        driver.findElement(By.xpath("(//div[contains(@class, 'lk-cv-block__input_md-4')]//span[text()='Способ связи'])[last()]//..")).click();
        driver.findElement(By.xpath(String.format("(//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_service js-custom-select-options'])[last()]//*[@title='%s']", contactType))).click();


        WebElement el = driver.findElement(By.xpath("//input[@name = 'contact-1-value']"));
        el.click();
        el.sendKeys(contactValue);
        logger.info("Добавлен 1 контакт");
    }

    public void chooseContactsTwo(String contactType, String contactValue) {
        driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();

        driver.findElement(By.xpath("(//div[contains(@class, 'lk-cv-block__input_md-4')]//span[text()='Способ связи'])[last()]//..")).click();
        driver.findElement(By.xpath(String.format("(//div[@class='lk-cv-block__select-scroll lk-cv-block__select-scroll_service js-custom-select-options'])[last()]//*[@title='%s']", contactType))).click();

        WebElement el = driver.findElement(By.xpath("//input[@name = 'contact-2-value']"));
        el.click();
        el.sendKeys(contactValue);
        logger.info("Добавлен 2 контакт");
    }

    public void fillExperienceOfDeveloping(ExperienceOfDeveloping language, String experienceOfDevelopingValue){
        execWithJS(By.xpath("//*[@class = 'experience-add js-formset-add']"));
        chooseProgLang(language);

        chooseExpDuration(experienceOfDevelopingValue);
    }

    public void chooseProgLang(ExperienceOfDeveloping language){
        WebElement lang = driver.findElement(By.xpath("//div[@class = 'select select_full']//*[@name = 'experience-0-experience']"));
        jsHelper.scrollToElement(lang);
        driver.findElement(By.xpath(String.format("//option[text() = '%s']", language.getName()))).click();
    }

    public void chooseExpDuration(String experienceOfDevelopingValue){
        driver.findElement(By.xpath(String.format("//option[text() = '%s']", experienceOfDevelopingValue))).click();
    }

    public void chooseGender(GenderData genderData) {
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.cssSelector(String.format("option[value='%s']", genderData.getName()))).click();
        logger.info("Добавлен пол");
    }

    public void clickOnSave() {
        driver.findElement(By.xpath("//button[@title='Сохранить и продолжить']")).click();
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