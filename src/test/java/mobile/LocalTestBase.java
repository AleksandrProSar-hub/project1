package mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.driver.LocalMobileDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class LocalTestBase {
    @BeforeAll
    static void beforeAll() throws Exception {
        Configuration.browser = LocalMobileDriver.class.getName(); // локальный запуск
       Configuration.browserSize = null; // локальный запуск
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        // open(); // локальный запуск
    }

    @AfterEach
    void afterEach() {
//        Attach.screenshotAs("Last screenshot");
  //      Attach.pageSource();

        closeWebDriver();
    }
}
