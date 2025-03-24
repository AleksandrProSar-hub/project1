package mobile;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackTestBase {

    static AndroidDriver driver;


    public  void main(String[] args) throws MalformedURLException {
        // Initialization of driver as you already have it
        initializeDriver();
    }

    @BeforeEach
    public  void initializeDriver() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("app", "bs://d7728b5e8ef2c45ca1fa2cc426198d9f281dd535"); // “bs://sample.app” - это специальный URL-адрес в формате BrowserStack, который указывает на приложение:
        // bs:// - префикс, обозначающий, что файл находится в хранилище BrowserStack

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", "alexpro_zF5NjW");
        browserstackOptions.put("accessKey", "pzyrQxkekbc5XxR2bpBz");
        browserstackOptions.put("osVersion", "13.0");
        browserstackOptions.put("deviceName", "Google Pixel 7");
        browserstackOptions.put("projectName", "Project Name");
        browserstackOptions.put("buildName", "1.0");
        browserstackOptions.put("sessionName", "Session Name");
        browserstackOptions.put("appiumVersion", "2.0.0");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("debug", "true");
        browserstackOptions.put("timezone", "New_York");
        capabilities.setCapability("bstack:options", browserstackOptions);

        driver = new AndroidDriver(
                new URL("https://hub-cloud.browserstack.com/wd/hub"),
                capabilities
        );
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}