package mobile;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static mobile.BrowserStackTestBase.driver;


public class BrowserStackPageObject {

    private WebDriverWait wait;

    public BrowserStackPageObject() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Установите ваше время ожидания
    }
    private final String nameArticleSaveUserList = "Java (programming language)";
    private final String descriptionArticleSaveUserList = "Object-oriented programming language";

    private final String listName1 = "List 1";
    private final String listName2 = "List 2";

    private final String listDescription1 = "Description 1";
    private final String listDescription2 = "Description 2";
    @Step ("Скипаем онбординг")
    public void skipOnboarding() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
    }

    @Step ("Поиск java programming")
    public void searchJava (){
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java programming");
        wait.until(d -> d.findElement(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).getText().equals("Java (programming language)"));
    }

    @Step ("Переход в Сохраненные")
    public void transitionSaved() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click();
        wait.until(d -> d.findElement(AppiumBy.className("android.widget.TextView")).getText().equals("Saved"));
    }

    @Step ("Переход в Поиск")
    public void transitionSearch() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_search")).click();
        wait.until(d -> d.findElement(AppiumBy.className("android.widget.TextView")).getText().equals("Search"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/search_card"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/history_title"));
    }

    @Step ("Переход в Правки")
    public void transitionEdits() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_edits")).click();
        wait.until(d -> d.findElement(AppiumBy.className("android.widget.TextView")).getText().equals("Edits"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/messageTitleView"));
    }

    @Step ("Переход в Ещё")
    public void transitionMore() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_more")).click();
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_account_container"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_places_container"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container"));
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_donate_container"));
    }

    @Step ("Переход к статье")
    public void transitionToFoundArticle() {
        driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/search_results_list\"]/android.view.ViewGroup[1]")).click();
    }
    @Step ("Нажимаем кнопку сохранить")
    public void clickSaveButton() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/page_save")).click();
    }

    @Step ("Возвращаемся на главную страницу")
    public void returningFromSearchToMainPage() {
        driver.findElement(AppiumBy.className("android.widget.ImageButton")).click();
        driver.findElement(AppiumBy.className("android.widget.ImageButton")).click();
    }

    @Step ("Проваливаемся в первый сохраненный список")
    public void goToFirstSavedList() {
        driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/recycler_view\"]/android.view.ViewGroup")).click();
    }

    @Step ("Скрываем подсказку")
    public void hidingHint() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/buttonView")).click();
    }

    @Step ("Проверяем имя и описание статьи в сохраненных")
    public void shouldHaveNameAndDescriptionArticleSaveUserList() {
        String actualTitle = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).getText();
        String actualDescription = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")).getText();

        if (!actualTitle.equals(nameArticleSaveUserList)) {
            throw new AssertionError("Expected title: " + nameArticleSaveUserList + ", but found: " + actualTitle);
        }
        if (!actualDescription.equals(descriptionArticleSaveUserList)) {
            throw new AssertionError("Expected description: " + descriptionArticleSaveUserList + ", but found: " + actualDescription);
        }
    }

    @Step ("Нажимаем кнопку закрыть")
    public void clickButtonClose() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/closeButton")).click();
    }

    @Step ("Сохраненные. Проваливаемся в меню")
    public void savedMenuButton() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
    }

    @Step ("Сохраненные. Меню. Выбираем создать новый список")
    public void savedMenuCreateNewList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/reading_lists_overflow_create_new_list")).click();
    }
    @Step ("Вводим имя нового списка")
    public void sendKeysNameNewList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/text_input")).sendKeys(listName1);
    }

    @Step ("Вводим описание нового списка")
    public void sendKeysDescriptionNewList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/secondary_text_input")).sendKeys(listDescription1);
    }

    @Step ("Нажимаем кнопку ОК")
    public void clickButtonOk() {
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Step ("Проверяем создание списка с заданным именем и описанием")
    public void listNewShouldHaveNameAndDescription() {
        String actualTitle = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_title")).getText();
        String actualDescription = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_description")).getText();

        if (!actualTitle.equals(listName1)) {
            throw new AssertionError("Expected title: " + listName1 + ", but found: " + actualTitle);
        }
        if (!actualDescription.equals(listDescription1)) {
            throw new AssertionError("Expected description: " + listDescription1 + ", but found: " + actualDescription);
        }
    }

    @Step ("Список. Нажимаем кнопку Меню")
    public void listMenuButton() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_overflow_menu")).click();
    }
    @Step ("Список. Меню. Выбираем Удалить список")
    public void listMenuButtonDeleteList() {
        driver.findElement(AppiumBy.xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia.alpha:id/content\"])[5]")).click();
    }
    @Step ("Проверяем отсутствие сохраненных списков")
    public void shouldNotSavedLists() {
        try {
            driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_title"));
            throw new AssertionError("Saved lists should not be present");
        } catch (NoSuchElementException e) {
            // Expected behavior
        }
    }
    @Step ("Список. Меню. Выбираем изменить название списка")
    public void listMenuButtonChangeNameList() {
        driver.findElement(AppiumBy.xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia.alpha:id/content\"])[1]")).click();
    }

    @Step ("Вводим новое название списка")
    public void sendKeysNameOldList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/text_input")).sendKeys(listName2);
    }

    @Step ("Вводим новое описание списка")
    public void sendKeysDescriptionOldList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/secondary_text_input")).sendKeys(listDescription2);
    }

    @Step ("Проверяем изменение имени и название списка")
    public void listOldShouldHaveNameAndDescription() {
        String actualTitle = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_title")).getText();
        String actualDescription = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/item_description")).getText();

        if (!actualTitle.equals(listName2)) {
            throw new AssertionError("Expected title: " + listName2 + ", but found: " + actualTitle);
        }
        if (!actualDescription.equals(listDescription2)) {
            throw new AssertionError("Expected description: " + listDescription2 + ", but found: " + actualDescription);
        }
    }
    public void transitionFeed() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_explore")).click();
    }

    @Step ("Переход в первую найденную запись")
    public void transitionFirstFoundArticle() {
        driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/search_results_list\"]/android.view.ViewGroup[1]")).click();
    }

    public void confirmingAdditionToList() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/snackbar_action")).click();
    }

    @Step ("Выбираем список для статьи")
    public void selectingListForArticle() {
        driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/list_of_lists")).click();
    }
    @Step ("Отказываемся от синхронизации списков")
    public void refusingSynchronizeLists() {
        driver.findElement(AppiumBy.id("android:id/button2")).click();
    }

    @Step ("Переходим в пользовательский список")
    public void transitionUserList() {
        driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/recycler_view\"]/android.view.ViewGroup[2]")).click();
    }
}