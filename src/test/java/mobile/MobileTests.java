package mobile;

import mobile.pages.MobilePages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.xpath;

public class MobileTests extends LocalTestBase {

    @DisplayName("Онбординг. Проверяем страницы")
    @Test
    void onboardingPage(){
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Свободная энциклопедия"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Новые способы исследований"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Списки для чтения с синхронизацией"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Данные и конфиденциальность"));
    }

    @DisplayName("Онбординг. Пропуск") //проверяем главную страницу. Википедия, поиск, кнопки лента Сохраненные, Найти, Правки, Ещё
    @Test
    void onboardingSkip(){
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Свободная энциклопедия"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        $(id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe(visible); // заголово Википедия
        $(id("org.wikipedia.alpha:id/search_container")).shouldBe(visible); // поиск
        $(id("org.wikipedia.alpha:id/nav_tab_explore")).shouldBe(visible); // кнопка Лента
        $(id("org.wikipedia.alpha:id/nav_tab_reading_lists")).shouldBe(visible); // кнопка Сохраненные
        $(id("org.wikipedia.alpha:id/nav_tab_search")).shouldBe(visible); // кнопка Найти
        $(id("org.wikipedia.alpha:id/nav_tab_edits")).shouldBe(visible); // Правки
        $(id("org.wikipedia.alpha:id/nav_tab_more")).shouldBe(visible); // Ещё
    }

    @DisplayName("Поиск. Стандартно") //
    @Test
    void searchTest(){
        new MobilePages().skipOnboarding();
        new MobilePages().searchJava();
    }

    @DisplayName("Навтабы. Переход в сохраненные пустые") //
    @Test
    void searchTest1(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
    }

    @DisplayName("Навтабы. Переход в найти. Без истории") //
    @Test
    void searchTest2(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSearch();
    }

    @DisplayName("Навтабы. Переход в правки. Без истории") //
    @Test
    void searchTest3(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionEdits();
    }

    @DisplayName("Навтабы. Переход в ещё. Без истории") //
    @Test
    void searchTest4(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionMore();
    }

    @DisplayName("Найденная инфа. Добавление статьи в сохраненные по умолчанию") //
    @Test
    void searchTest5(){
        new MobilePages().skipOnboarding();
        new MobilePages().searchJava();
        new MobilePages().transitionToFoundArticle();
        // добавление статьи с сохраненные
        new MobilePages().clickSaveButton();
        // возвращенние на главную страницу
        new MobilePages().returningFromSearchToMainPage();
        // переходим в Сохраненные
        new MobilePages().transitionSaved();
        // переходим в первый сохраненный список
        new MobilePages().goToFirstSavedList();
        // скрываем подсказку
        new MobilePages().hidingHint();
        // проверяем название и описание сохраненной статьи
        new MobilePages().shouldHaveNameAndDescriptionArticleSaveUserList();
    }

    @DisplayName("Сохраненные. Создание нового списка")
    @Test
    void searchTest6(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        new MobilePages().listNewShouldHaveNameAndDescription();
    }

    @DisplayName("Сохраненные. Удаление списка") //
    @Test
    void searchTest7(){
        // создание нового списка
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        new MobilePages().listNewShouldHaveNameAndDescription();
        // открываем список
        new MobilePages().goToFirstSavedList();
        // скрываем подсказку
        new MobilePages().hidingHint();
        // заходим в меню списка
        new MobilePages().listMenuButton();
        // удаляем список
        new MobilePages().listMenuButtonDeleteList();
        new MobilePages().clickButtonOk();
        // проверяем что списки отсутствуют
        new MobilePages().shouldNotSavedLists();
    }

    @DisplayName("Сохраненные. Изменение названия и описания списка")
    @Test
    void searchTest8(){
        // создание нового списка
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        new MobilePages().listNewShouldHaveNameAndDescription();
        // открываем список
        new MobilePages().goToFirstSavedList();
        // скрываем подсказку
        new MobilePages().hidingHint();
        // заходим в меню списка
        new MobilePages().listMenuButton();
        // выбираем пункт изменить название списка
        new MobilePages().listMenuButtonChangeNameList();
        // меняем название и описание списка
        new MobilePages().sendKeysNameOldList();
        new MobilePages().sendKeysDescriptionOldList();
        new MobilePages().clickButtonOk();
        // проверяем имя и описание списка после изменений
        new MobilePages().listOldShouldHaveNameAndDescription();
    }

    @DisplayName("Сохраненные. Добавление статьи в список")
    @Test
    void addingArticleToUserList(){
        // создание нового списка
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        new MobilePages().listNewShouldHaveNameAndDescription();
        // переходим в ленту
        new MobilePages().transitionFeed();
        // ищем статью java
        new MobilePages().searchJava();
        // переход в первую найденную статью
        new MobilePages().transitionFirstFoundArticle();
        // нажимаем кнопку сохранить
        new MobilePages().clickSaveButton();
        // подтверждаем добавление статьи в список
        new MobilePages().confirmingAdditionToList();
        // выбираем список в который добавим статью
        new MobilePages().selectingListForArticle();
        // возвращаемся на главную страницу
        new MobilePages().returningFromSearchToMainPage();
        // отказываемся от синхронизации списков
        new MobilePages().refusingSynchronizeLists();
        // заходим в сохраненные
        new MobilePages().transitionSaved();
        // переход в пользовательский список
        new MobilePages().transitionUserList();
        // скрываем подсказку
        new MobilePages().hidingHint();
        // проверяем название сохраненной статьи в пользователском списке
        new MobilePages().shouldHaveNameAndDescriptionArticleSaveUserList();
    }
}
