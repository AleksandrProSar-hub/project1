package mobile;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BrowserStackTest extends BrowserStackTestBase {

    @Test
    @Feature("Навтабы")
    @Tag("MOBILE")
    @DisplayName("Навтабы. Простой поиск")
    void searchTest() {
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.searchJava();
    }

    @Test
    @Feature("Навтабы")
    @Tag("MOBILE")
    @DisplayName("Навтабы. Переход в сохраненные пустые")
    void searchTest1() {
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.transitionSaved();
    }

    @Test
    @Feature("Навтабы")
    @Tag("MOBILE")
    @DisplayName("Навтабы. Переход в найти. Без истории")
    void searchTest2(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.transitionSearch();
    }

    @Test
    @Feature("Навтабы")
    @Tag("MOBILE")
    @DisplayName("Навтабы. Переход в правки. Без истории")
    void searchTest3(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.transitionEdits();
    }

    @Test
    @Feature("Навтабы")
    @Tag("MOBILE")
    @DisplayName("Навтабы. Переход в ещё. Без истории")
    void searchTest4(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.transitionMore();
    }

    @Test
    @Feature("Сохраненные")
    @Tag("MOBILE")
    @DisplayName("Сохраненные. Добавление статьи в сохраненные по умолчанию") //
    void searchTest5(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.searchJava();
        pages.transitionToFoundArticle();
        // скипаем попап
        pages.clickButtonClose();
        // добавление статьи с сохраненные
        pages.clickSaveButton();
        // возвращение на главную страницу
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

    @Test
    @Feature("Сохраненные")
    @Tag("MOBILE")
    @DisplayName("Сохраненные. Создание нового списка")
    void searchTest6(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
        pages.skipOnboarding();
        pages.transitionSaved();
        pages.savedMenuButton();
        pages.savedMenuCreateNewList();
        pages.sendKeysNameNewList();
        pages.sendKeysDescriptionNewList();
        pages.clickButtonOk();
        pages.listNewShouldHaveNameAndDescription();
    }

    @Test
    @Feature("Сохраненные")
    @Tag("MOBILE")
    @DisplayName("Сохраненные. Удаление списка") //
    void searchTest7(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
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

    @Test
    @Feature("Сохраненные")
    @Tag("MOBILE")
    @DisplayName("Сохраненные. Изменение названия и описания списка")
    void searchTest8(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
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

    @Test
    @Feature("Сохраненные")
    @Tag("MOBILE")
    @DisplayName("Сохраненные. Добавление статьи в список")
    void addingArticleToUserList(){
        BrowserStackPageObject pages = new BrowserStackPageObject();
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
        // закрываем попап
        pages.clickButtonClose();
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