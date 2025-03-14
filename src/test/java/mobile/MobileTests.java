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

    @DisplayName("���������. ��������� ��������")
    @Test
    void onboardingPage(){
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("��������� ������������"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("����� ������� ������������"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("������ ��� ������ � ��������������"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("������ � ������������������"));
    }

    @DisplayName("���������. �������") //��������� ������� ��������. ���������, �����, ������ ����� �����������, �����, ������, ���
    @Test
    void onboardingSkip(){
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("��������� ������������"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        $(id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe(visible); // �������� ���������
        $(id("org.wikipedia.alpha:id/search_container")).shouldBe(visible); // �����
        $(id("org.wikipedia.alpha:id/nav_tab_explore")).shouldBe(visible); // ������ �����
        $(id("org.wikipedia.alpha:id/nav_tab_reading_lists")).shouldBe(visible); // ������ �����������
        $(id("org.wikipedia.alpha:id/nav_tab_search")).shouldBe(visible); // ������ �����
        $(id("org.wikipedia.alpha:id/nav_tab_edits")).shouldBe(visible); // ������
        $(id("org.wikipedia.alpha:id/nav_tab_more")).shouldBe(visible); // ���
    }

    @DisplayName("�����. ����������") //
    @Test
    void searchTest(){
        new MobilePages().skipOnboarding();
        new MobilePages().searchJava();
    }

    @DisplayName("�������. ������� � ����������� ������") //
    @Test
    void searchTest1(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
    }

    @DisplayName("�������. ������� � �����. ��� �������") //
    @Test
    void searchTest2(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSearch();
    }

    @DisplayName("�������. ������� � ������. ��� �������") //
    @Test
    void searchTest3(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionEdits();
    }

    @DisplayName("�������. ������� � ���. ��� �������") //
    @Test
    void searchTest4(){
        new MobilePages().skipOnboarding();
        new MobilePages().transitionMore();
    }

    @DisplayName("��������� ����. ���������� ������ � ����������� �� ���������") //
    @Test
    void searchTest5(){
        new MobilePages().skipOnboarding();
        new MobilePages().searchJava();
        new MobilePages().transitionToFoundArticle();
        // ���������� ������ � �����������
        new MobilePages().clickSaveButton();
        // ������������ �� ������� ��������
        new MobilePages().returningFromSearchToMainPage();
        // ��������� � �����������
        new MobilePages().transitionSaved();
        // ��������� � ������ ����������� ������
        new MobilePages().goToFirstSavedList();
        // �������� ���������
        new MobilePages().hidingHint();
        // ��������� �������� � �������� ����������� ������
        new MobilePages().shouldHaveNameAndDescriptionArticleSaveUserList();
    }

    @DisplayName("�����������. �������� ������ ������")
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

    @DisplayName("�����������. �������� ������") //
    @Test
    void searchTest7(){
        // �������� ������ ������
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // ��������� ��� ������ ��������. ��������� ��� � �������� ���������� ������
        new MobilePages().listNewShouldHaveNameAndDescription();
        // ��������� ������
        new MobilePages().goToFirstSavedList();
        // �������� ���������
        new MobilePages().hidingHint();
        // ������� � ���� ������
        new MobilePages().listMenuButton();
        // ������� ������
        new MobilePages().listMenuButtonDeleteList();
        new MobilePages().clickButtonOk();
        // ��������� ��� ������ �����������
        new MobilePages().shouldNotSavedLists();
    }

    @DisplayName("�����������. ��������� �������� � �������� ������")
    @Test
    void searchTest8(){
        // �������� ������ ������
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // ��������� ��� ������ ��������. ��������� ��� � �������� ���������� ������
        new MobilePages().listNewShouldHaveNameAndDescription();
        // ��������� ������
        new MobilePages().goToFirstSavedList();
        // �������� ���������
        new MobilePages().hidingHint();
        // ������� � ���� ������
        new MobilePages().listMenuButton();
        // �������� ����� �������� �������� ������
        new MobilePages().listMenuButtonChangeNameList();
        // ������ �������� � �������� ������
        new MobilePages().sendKeysNameOldList();
        new MobilePages().sendKeysDescriptionOldList();
        new MobilePages().clickButtonOk();
        // ��������� ��� � �������� ������ ����� ���������
        new MobilePages().listOldShouldHaveNameAndDescription();
    }

    @DisplayName("�����������. ���������� ������ � ������")
    @Test
    void addingArticleToUserList(){
        // �������� ������ ������
        new MobilePages().skipOnboarding();
        new MobilePages().transitionSaved();
        new MobilePages().savedMenuButton();
        new MobilePages().savedMenuCreateNewList();
        new MobilePages().sendKeysNameNewList();
        new MobilePages().sendKeysDescriptionNewList();
        new MobilePages().clickButtonOk();
        // ��������� ��� ������ ��������. ��������� ��� � �������� ���������� ������
        new MobilePages().listNewShouldHaveNameAndDescription();
        // ��������� � �����
        new MobilePages().transitionFeed();
        // ���� ������ java
        new MobilePages().searchJava();
        // ������� � ������ ��������� ������
        new MobilePages().transitionFirstFoundArticle();
        // �������� ������ ���������
        new MobilePages().clickSaveButton();
        // ������������ ���������� ������ � ������
        new MobilePages().confirmingAdditionToList();
        // �������� ������ � ������� ������� ������
        new MobilePages().selectingListForArticle();
        // ������������ �� ������� ��������
        new MobilePages().returningFromSearchToMainPage();
        // ������������ �� ������������� �������
        new MobilePages().refusingSynchronizeLists();
        // ������� � �����������
        new MobilePages().transitionSaved();
        // ������� � ���������������� ������
        new MobilePages().transitionUserList();
        // �������� ���������
        new MobilePages().hidingHint();
        // ��������� �������� ����������� ������ � ��������������� ������
        new MobilePages().shouldHaveNameAndDescriptionArticleSaveUserList();
    }
}
