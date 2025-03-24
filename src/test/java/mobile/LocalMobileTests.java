package mobile;

import mobile.pages.LocalMobilePages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class LocalMobileTests extends LocalTestBase {

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
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.searchJava();
    }

    @DisplayName("Навтабы. Переход в сохраненные пустые") //
    @Test
    void searchTest1(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.transitionSaved();
    }

    @DisplayName("Навтабы. Переход в найти. Без истории") //
    @Test
    void searchTest2(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.transitionSearch();
    }

    @DisplayName("Навтабы. Переход в правки. Без истории") //
    @Test
    void searchTest3(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.transitionEdits();
    }

    @DisplayName("Навтабы. Переход в ещё. Без истории") //
    @Test
    void searchTest4(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.transitionMore();
    }

    @DisplayName("Найденная инфа. Добавление статьи в сохраненные по умолчанию") //
    @Test
    void searchTest5(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.searchJava();
        pages.transitionToFoundArticle();
        // добавление статьи с сохраненные
        pages.clickSaveButton();
        // возвращенние на главную страницу
        pages.returningFromSearchToMainPage();
        // переходим в Сохраненные
        pages.transitionSaved();
        // переходим в первый сохраненный список
        pages.goToFirstSavedList();
        // скрываем подсказку
        pages.hidingHint();
        // проверяем название и описание сохраненной статьи
        pages.shouldHaveNameAndDescriptionArticleSaveUserList();
    }

    @DisplayName("Сохраненные. Создание нового списка")
    @Test
    void searchTest6(){
        LocalMobilePages pages = new LocalMobilePages();
        pages.skipOnboarding();
        pages.transitionSaved();
        pages.savedMenuButton();
        pages.savedMenuCreateNewList();
        pages.sendKeysNameNewList();
        pages.sendKeysDescriptionNewList();
        pages.clickButtonOk();
        pages.listNewShouldHaveNameAndDescription();
    }

    @DisplayName("Сохраненные. Удаление списка") //
    @Test
    void searchTest7(){
        LocalMobilePages pages = new LocalMobilePages();
        // создание нового списка
        pages.skipOnboarding();
        pages.transitionSaved();
        pages.savedMenuButton();
        pages.savedMenuCreateNewList();
        pages.sendKeysNameNewList();
        pages.sendKeysDescriptionNewList();
        pages.clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        pages.listNewShouldHaveNameAndDescription();
        // открываем список
        pages.goToFirstSavedList();
        // скрываем подсказку
        pages.hidingHint();
        // заходим в меню списка
        pages.listMenuButton();
        // удаляем список
        pages.listMenuButtonDeleteList();
        pages.clickButtonOk();
        // проверяем что списки отсутствуют
        pages.shouldNotSavedLists();
    }

    @DisplayName("Сохраненные. Изменение названия и описания списка")
    @Test
    void searchTest8(){
        LocalMobilePages pages = new LocalMobilePages();
        // создание нового списка
        pages.skipOnboarding();
        pages.transitionSaved();
        pages.savedMenuButton();
        pages.savedMenuCreateNewList();
        pages.sendKeysNameNewList();
        pages.sendKeysDescriptionNewList();
        pages.clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        pages.listNewShouldHaveNameAndDescription();
        // открываем список
        pages.goToFirstSavedList();
        // скрываем подсказку
        pages.hidingHint();
        // заходим в меню списка
        pages.listMenuButton();
        // выбираем пункт изменить название списка
        pages.listMenuButtonChangeNameList();
        // меняем название и описание списка
        pages.sendKeysNameOldList();
        pages.sendKeysDescriptionOldList();
        pages.clickButtonOk();
        // проверяем имя и описание списка после изменений
        pages.listOldShouldHaveNameAndDescription();
    }

    @DisplayName("Сохраненные. Добавление статьи в список")
    @Test
    void addingArticleToUserList(){
        LocalMobilePages pages = new LocalMobilePages();
        // создание нового списка
        pages.skipOnboarding();
        pages.transitionSaved();
        pages.savedMenuButton();
        pages.savedMenuCreateNewList();
        pages.sendKeysNameNewList();
        pages.sendKeysDescriptionNewList();
        pages.clickButtonOk();
        // проверяем что список создался. проверяем имя и описание созданного списка
        pages.listNewShouldHaveNameAndDescription();
        // переходим в ленту
        pages.transitionFeed();
        // ищем статью java
        pages.searchJava();
        // переход в первую найденную статью
        pages.transitionFirstFoundArticle();
        // нажимаем кнопку сохранить
        pages.clickSaveButton();
        // подтверждаем добавление статьи в список
        pages.confirmingAdditionToList();
        // выбираем список в который добавим статью
        pages.selectingListForArticle();
        // возвращаемся на главную страницу
        pages.returningFromSearchToMainPage();
        // отказываемся от синхронизации списков
        pages.refusingSynchronizeLists();
        // заходим в сохраненные
        pages.transitionSaved();
        // переход в пользовательский список
        pages.transitionUserList();
        // скрываем подсказку
        pages.hidingHint();
        // проверяем название сохраненной статьи в пользователском списке
        pages.shouldHaveNameAndDescriptionArticleSaveUserList();
    }
}