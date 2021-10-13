package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;


public class TestBase {
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;

        CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);
        String login = credentials.login();
        String password = credentials.password();
        String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");
        String remote = format("https://%s:%s@%s", login, password, url);
        // Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        Configuration.remote = remote;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

}