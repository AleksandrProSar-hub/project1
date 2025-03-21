package web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class BrowserConfig {
    private static final String REMOTE_URL = "http://192.168.0.103:4444/wd/hub";
    private static final String BROWSER_SIZE = "1920x1080";

    public static void setupBrowser(String browserName, String browserVersion) {
        Configuration.remote = REMOTE_URL;
        Configuration.browserSize = BROWSER_SIZE;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
        }});

        Configuration.browser = browserName;
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
