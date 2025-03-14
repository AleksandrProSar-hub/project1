package mobile.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class MobilePages {

    private final String listName1 = "������ 1";
    private final String listName2 = "������ 2";

    private final String listDescription1 = "�������� 1";
    private final String listDescription2 = "�������� 2";

    private final String nameArticleSaveUserList = "Java";

    private final String descriptionArticleSaveUserList = "���� ����������������";


    public void skipOnboarding () {
        $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
    }

    public void searchJava (){
        $(id("org.wikipedia.alpha:id/search_container")).click();
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        $(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("Java"));
    }

    public void transitionSaved () {
        $(id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click();
        $(("android.widget.TextView")).shouldHave(text("����������"));
    }

    public void transitionSearch () {
        $(id("org.wikipedia.alpha:id/nav_tab_search")).click();
        $(("android.widget.TextView")).shouldHave(text("�����"));
        $(id("org.wikipedia.alpha:id/search_card")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/history_title")).shouldHave(text("�������"));
    }

    public void transitionEdits () {
        $(id("org.wikipedia.alpha:id/nav_tab_edits")).click();
        $(("android.widget.TextView")).shouldHave(text("������"));
        $(id("org.wikipedia.alpha:id/messageTitleView")).shouldHave(text("������ �� ��, ��� ������ ����� ������������� ���������?"));
    }

    public void transitionMore () {
        $(id("org.wikipedia.alpha:id/nav_tab_more")).click();
        $(id("org.wikipedia.alpha:id/main_drawer_account_container")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/main_drawer_places_container")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/main_drawer_settings_container")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/main_drawer_donate_container")).shouldBe(visible);
    }

    public void transitionToFoundArticle () {
        $(id("org.wikipedia.alpha:id/page_list_item_title")).click();
    }

    public void transitionFeed () { // ������� � �����
        $(id("org.wikipedia.alpha:id/nav_tab_explore")).click();
    }

    public void clickSaveButton () { // �������� ������ ���������
        $(id("org.wikipedia.alpha:id/page_save")).click();
    }

    public void returningFromSearchToMainPage () {
        $(("android.widget.ImageButton")).click();
        $(("android.widget.ImageButton")).click();
    }

    public void goToFirstSavedList() {
        $(id("org.wikipedia.alpha:id/recycler_view")).click();
    }

    public void savedMenuButton () {
        $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
    }

    public void savedMenuCreateNewList () {
        $(id("org.wikipedia.alpha:id/reading_lists_overflow_create_new_list")).click();
    }

    public void sendKeysNameNewList () {
        $(id("org.wikipedia.alpha:id/text_input")).sendKeys(listName1);
    }

    public void sendKeysDescriptionNewList () {
        $(id("org.wikipedia.alpha:id/secondary_text_input")).sendKeys(listDescription1);
    }

    public void clickButtonOk () {
        $(id("android:id/button1")).click();
    }

    public void listMenuButton () { // ������ ���� � �������� ������
        $(id("org.wikipedia.alpha:id/item_overflow_menu")).click();
    }

    public void listMenuButtonDeleteList () { // � ���� ������ �������� ����� ������� ������
        $(xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia.alpha:id/content\"])[5]")).click();
    }

    public void listMenuButtonChangeNameList () { // � ���� ������ �������� ����� �������� �������� ������
        $(xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia.alpha:id/content\"])[1]")).click(); // �������� �������� ��������
    }

    public void listNewShouldHaveNameAndDescription () { // ��������� ��� � �������� ���������� ������
        $(id("org.wikipedia.alpha:id/item_title")).shouldHave(text(listName1));
        $(id("org.wikipedia.alpha:id/item_description")).shouldHave(text(listDescription1));
    }

    public void hidingHint () {
        $(id("org.wikipedia.alpha:id/buttonView")).click(); // �������� ���������
    }

    public void listOldShouldHaveNameAndDescription () { // ��������� ��� � �������� ������ ����� ���������
        $(id("org.wikipedia.alpha:id/item_title")).shouldHave(text(listName2));
        $(id("org.wikipedia.alpha:id/item_description")).shouldHave(text(listDescription2));
    }

    public void sendKeysNameOldList () {
        $(id("org.wikipedia.alpha:id/text_input")).sendKeys(listName2);
    }

    public void sendKeysDescriptionOldList () {
        $(id("org.wikipedia.alpha:id/secondary_text_input")).sendKeys(listDescription2);
    }

    public void transitionFirstFoundArticle () {
        $(xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/search_results_list\"]/android.view.ViewGroup[1]")).click(); // ������� � ������
    }

    public void transitionUserList () { // ������� � ���������������� ������
        $(xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia.alpha:id/recycler_view\"]/android.view.ViewGroup[2]")).click(); // ������������� � ������
    }

    public void shouldHaveNameAndDescriptionArticleSaveUserList () {
        $(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text(nameArticleSaveUserList));
        $(id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text(descriptionArticleSaveUserList));
    }

    public void confirmingAdditionToList () { // ������������ ���������� ������ � ������

        $(id("org.wikipedia.alpha:id/snackbar_action")).click();
    }

    public void selectingListForArticle () { // �������� ������ � ������� ������� ������

        $(id("org.wikipedia.alpha:id/list_of_lists")).click();
    }

    public void refusingSynchronizeLists () { // ������������ �� ������������� �������
        $(id("android:id/button2")).click();
    }

    public void shouldNotSavedLists () { // ��������� ���������� ����������� �������
        $(id("org.wikipedia.alpha:id/item_title")).shouldNot(exist);
    }
}
