import com.github.javafaker.Faker;
import components.Header;
import data.*;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.ProfilePage;
import tools.WaitTools;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Tag("otus")
public class AddInfoToProfile_Test {
    private final Faker faker = new Faker();
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger(AddInfoToProfile_Test.class);

    @BeforeEach()
    public void driverSetup() {
        new DriverFactory(driver);
        driver = DriverFactory.create();
    }

    @AfterEach
    public void driverStop() {
        if (driver!= null) {
            driver.quit();
        }
    }

    @Test
    public void addInfoToProfile() {
        new MainPage(driver).open("/");
        new Header(driver).clickButtonEnter();
        new AuthorizationPage(driver).authorizationUser();
        new Header(driver).popUp();
        new ProfilePage(driver).checkingFields();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clearFieldsCountryAndEnglish();
        profilePage.clearFields(InputFieldData.FNAME);
        profilePage.clearFields(InputFieldData.FNAMELATIN);
        profilePage.clearFields(InputFieldData.LNAME);
        profilePage.clearFields(InputFieldData.LNAMELATIN);
        profilePage.clearFields(InputFieldData.BLOGNAME);
        profilePage.clearFields(InputFieldData.DATEOFBRTH);
        profilePage.clearFields(InputFieldData.COMPANY);
        profilePage.clearFields(InputFieldData.WORK_POSITION);
        profilePage.inputFioInProfile(InputFieldData.FNAME, faker.name().firstName());
        profilePage.inputFioInProfile(InputFieldData.FNAMELATIN, faker.name().lastName());
        profilePage.inputFioInProfile(InputFieldData.LNAME, faker.name().firstName());
        profilePage.inputFioInProfile(InputFieldData.LNAMELATIN, faker.name().name());
        profilePage.inputFioInProfile(InputFieldData.BLOGNAME, faker.name().name());
        profilePage.inputFioInProfile(InputFieldData.COMPANY, faker.company().name());
        profilePage.inputFioInProfile(InputFieldData.WORK_POSITION, faker.job().position());
        profilePage.fillExperienceOfDeveloping(ExperienceOfDeveloping.JAVA, "1 год");
        profilePage.inputFioInProfile(InputFieldData.DATEOFBRTH,
                faker.date().birthday().toInstant().atZone(ZoneId.
                        systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        profilePage.inputCountryInProfile();
        profilePage.inputCityInProfile();
        profilePage.inputEnglishInProfile(EnglishLevelData.BEGINNER);
        profilePage.chooseReadyToRelocate(true);
        profilePage.chooseWorkingFormat(true, WorkGrafData.REMOTELY);
        profilePage.chooseContactsOne("Skype", "Fan2316");
        profilePage.chooseContactsTwo("Habr", "Fan2316");
        profilePage.chooseGender(GenderData.MALE);
        profilePage.clickOnSave();

    }


}