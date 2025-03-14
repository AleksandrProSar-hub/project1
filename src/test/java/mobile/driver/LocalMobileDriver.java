package mobile.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import java.io.IOException;

public class LocalMobileDriver implements WebDriverProvider {

    // Обращаемся к серверу appium
        public static URL getAppiumServerUrl() {
            try {
                return new URL("http://localhost:4723/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public WebDriver createDriver(Capabilities capabilities) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.merge(capabilities);

            options.setAutomationName(ANDROID_UIAUTOMATOR2)
                    .setPlatformName(ANDROID)
                    .setDeviceName("R5CW508T6ZM")
                    .setPlatformVersion("14.0")
//                .setDeviceName("Pixel 4 API 30")
//                .setPlatformVersion("11.0")
                    .setApp(getAppPath())
                    .setAppPackage("org.wikipedia.alpha")
                    .setAppActivity("org.wikipedia.main.MainActivity");

            return new AndroidDriver(getAppiumServerUrl(), options);
        }

        // Подгружаем приложение
        private String getAppPath() {
            String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/" + // ссылка на приложение
                    "releases/download/latest/app-alpha-universal-release.apk";
            String appPath = "src/test/resources/apps/app-alpha-universal-release.apk"; // путь куда упадет скаченное приложение

            File app = new File(appPath);
            if (!app.exists()) {
                try (InputStream in = new URL(appUrl).openStream()) {
                    copyInputStreamToFile(in, app);
                } catch (IOException e) {
                    throw new AssertionError("Failed to download application", e);
                }
            }
            return app.getAbsolutePath();
        }
    }

