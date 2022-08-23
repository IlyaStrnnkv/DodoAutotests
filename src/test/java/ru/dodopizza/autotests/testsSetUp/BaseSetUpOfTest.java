package ru.dodopizza.autotests.testsSetUp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import ru.dodopizza.utils.listeners.ScreenshotListener;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.Integer.parseInt;
import static ru.dodopizza.autotests.data.DefaultTestSetupData.*;

@Listeners({ScreenshotListener.class})
public class BaseSetUpOfTest implements GroupsTest {

    @BeforeTest(groups = {base}, alwaysRun = true)
    public void setUpBrowser() {
        Properties properties = getPropertiesXml();
        Configuration.baseUrl = properties.getProperty("baseUrl", BASE_URL);
        Configuration.timeout = parseInt(properties.getProperty("timeout", TIMEOUT));
        Configuration.pageLoadTimeout = parseInt(properties.getProperty("pageLoadTimeout", PAGE_LOAD_TIMEOUT));
        // mvn clean test -P local,all
    }

    @BeforeClass(groups = {base}, alwaysRun = true)
    public void setUp() {
        open();
    }
}
