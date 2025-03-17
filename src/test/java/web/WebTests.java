package web;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebTests {

    @BeforeEach
    public  void main() throws MalformedURLException {
        //Url ��������� ��� ��������
        Configuration.remote = "http://192.168.0.103:4444/wd/hub";
        //���������� ����� ������� ����� ������������
        Configuration.browser = "chrome";
        //������ ���� ��������
        Configuration.browserSize = "1920x1080";
        //������ ������ ������ DesiredCapabilities, ������������ ��� ���������  ����� ������������ � ������� ���� ����-��������
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
        }});

        Configuration.browserCapabilities = capabilities;
    }
    // �������� ������ �����
    @AfterEach
    public void main2() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("�����. ��������� ������")
    void headerChangingCurrency() {

        open("https://www.wildberries.ru/");
        $(".simple-menu__item.header__currency.j-b-header-country").hover();
        $(".country").find(byText("������������� �����")).click();
        $(".simple-menu__item.header__currency.j-b-header-country").shouldHave(text("KZT"));
        $(".product-card__price.price").shouldHave(text("��"));
    }

    @Test
    @DisplayName("�����. ������� � ��������")
    void headerSwitchingToDelivery() {
        open("https://www.wildberries.ru/");
        $(".navbar-pc__item.j-item-addresses").find(byText("������")).click();
        $(".service-menu__item.selected").shouldHave(text("��������"));
        $(".c-h1").shouldHave(text("��������"));
        $(".delivery-banner").shouldHave(text("������ �������� ����� ��� ����� �� ���� ������"));
        $("#terms-delivery").shouldHave(text("���������� � �������� � ������� ������"));
    }

    @Test
    @DisplayName("�����. ������� � �������")
    void headerSwitchingToBasket() {
        open("https://www.wildberries.ru/");
        $(".navbar-pc__item.j-item-basket").find(byText("�������")).click();
        $(".basket-page__basket-empty.basket-empty").shouldHave(text("� ������� ���� �����"));
    }

    @Test
    @DisplayName("�����. ���������� �� Wildberries")
    void headerSwitchingToSell() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("����������")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://seller.wildberries.ru/about-portal/ru?redirect_url=https%3A%2F%2Fseller.wildberries.ru%2F"));
        $(".preview_Preview__title__3xDP1").shouldHave(text("�� ������ �������� ����� ���������������"));
    }

    @Test
    @DisplayName("�����. ������ � Wildberries")
    void headerSwitchingToWork() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("������")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://career.wb.ru/"));
        $(".Home_welcomeBlockWrapper__h_RWh").shouldHave(text("������ �� ������ ������"));
    }

    @Test
    @DisplayName("�����. ������� � ����������")
    void headerSwitchingToAirlineTicket() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("����������")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://www.wildberries.ru/travel?entry_point=tab_header"));
        $(".common_page__spm6Q").shouldHave(text("���������� �����������"));
        $(".common_page__spm6Q").shouldHave(text("������� ����������"));
    }

    @Test
    @DisplayName("�������. ������� �� ������ ������� �� �������")
    void basketGoMainPage() {
        open("https://www.wildberries.ru/lk/basket");
        $(".basket-empty__wrap").shouldHave(text("� ������� ���� �����"));
        $(".basket-empty__wrap").find(withText("������� �� �������")).click();
        webdriver().shouldHave(url("https://www.wildberries.ru/"));
    }

    @Test
    @DisplayName("�������. �������� ������")
    void basketDeleteGoods() {
        open("https://www.wildberries.ru/");
        $(".product-card__link.j-card-link.j-open-full-product-card").shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".product-page__order-buttons").find(withText("�������� � �������")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldHave(text("1 �����"));
        $(".btn__del.j-basket-item-del").click();
        $(".basket-empty__wrap").shouldHave(text("� ������� ���� �����"));
    }

    @Test
    @DisplayName("�������. ���������� ���������� ������")
    void basketIncreasingQuantityGoods() {

        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $(".product-page__order-buttons").find(withText("�������� � �������")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 �����"));

        // ����� �� 4 �������, ��� ��� ������������� ���� �������������� � �������� ��������� ��������
        try {
            Thread.sleep(4000); // ����� �� 3000 ����������� (3 �������)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��������� � ������ ��������� ������ � ���������� 1 ��
        SelenideElement priceElement1 = $(".list-item__price-new.wallet");
        SelenideElement priceElement2 = $(".b-top__total.line span:nth-of-type(2)");

        // ��������� ����� ���
        String priceText1 = priceElement1.getText();
        String priceText2 = priceElement2.getText();
        // ������ ����� ��������� �����
        double priceNum1 = parsePrice(priceText1);
        double priceNum2 = parsePrice(priceText2);
        assertEquals(priceNum1, priceNum2);
        // ���������� ��������� 1 �� � ������ � � �����
        // assertEquals(priceText1, priceText2);
        $(".count__plus.plus").click();
        $(".accordion__goods-count").shouldHave(text("2 ������"));
        // ����� �� 4 �������, ��� ��� ������������� ���� �������������� � �������� ��������� ��������
        try {
            Thread.sleep(4000); // ����� �� 3000 ����������� (3 �������)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��������� � ������ ��������� ������ � ���������� 2 ��
        SelenideElement priceElement3 = $(".list-item__price-new.wallet");
        SelenideElement priceElement4 = $(".b-top__total.line span:nth-of-type(2)");
        // ��������� ����� ��� ����� ��������� ���������� �� 2 ��
        String priceText3 = priceElement3.getText();
        String priceText4 = priceElement4.getText();
        // ���������� ��������� 2 �� � ������ � � ����� � ��������� ������� ��� ������ ���� �����
        assertEquals(priceText3, priceText4);
        // ��������������� ����� � ����� ��� ���������
        // double priceNum1 = parsePrice(priceText1);
        double priceNum3 = parsePrice(priceText3);
        double priceNum4 = parsePrice(priceText2);

        // ��������� �������� ��������
        assertEquals(priceNum1 * 2, priceNum3);
    }
    private double parsePrice(String priceText) {
        // ������� ������� � ������ "�", � ����� ����������� � �����
        return Double.parseDouble(priceText.replaceAll("[^0-9]", ""));
    }

    @Test
    @DisplayName("�������. ���������� ���������� ������")
    void basketReducingQuantityGoods() {

        open("https://www.wildberries.ru/catalog/102721617/detail.aspx");
        $(".product-page__order-buttons").find(withText("�������� � �������")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 �����"));
        $(".count__plus.plus").click();
        $(".accordion__goods-count").shouldHave(text("2 ������"));
        // ����� �� 4 �������, ��� ��� ������������� ���� �������������� � �������� ��������� ��������
        try {
            Thread.sleep(4000); // ����� �� 3000 ����������� (3 �������)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��������� � ������ ��������� ������ � ���������� 2 ��
        SelenideElement priceElement1 = $(".list-item__price-new.wallet");
        SelenideElement priceElement2 = $(".b-top__total.line span:nth-of-type(2)");

        // ��������� ����� ���
        String priceText1 = priceElement1.getText();
        String priceText2 = priceElement2.getText();
        // ������ ������ � �����
        double priceNum1 = parsePrice(priceText1);
        double priceNum2 = parsePrice(priceText2);
        assertEquals(priceNum1, priceNum2);        // ���������� ��������� 2 �� � ������ � � �����

        $(".count__minus.minus").click();
        $(".accordion__goods-count").shouldHave(text("1 �����"));
        // ����� �� 4 �������, ��� ��� ������������� ���� �������������� � �������� ��������� ��������
        try {
            Thread.sleep(4000); // ����� �� 3000 ����������� (3 �������)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��������� � ������ ��������� ������ � ���������� 2 ��
        SelenideElement priceElement3 = $(".list-item__price-new.wallet");
        SelenideElement priceElement4 = $(".b-top__total.line span:nth-of-type(2)");
        // ��������� ����� ��� ����� ��������� ���������� �� 2 ��
        String priceText3 = priceElement3.getText();
        String priceText4 = priceElement4.getText();
        // ���������� ��������� 2 �� � ������ � � ����� � ��������� ������� ��� ������ ���� �����
        assertEquals(priceText3, priceText4);
        // ��������������� ����� � ����� ��� ���������
        // double priceNum1 = parsePrice(priceText1);
        double priceNum3 = parsePrice(priceText3);

        // ��������� �������� ��������
        assertEquals(priceNum1 / 2, priceNum3);
    }

    @Test
    @DisplayName("�������� ������. ������� � ������ ����� ������")
    void productCardSwitchingToReviewsViaRating() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $("#comments_reviews_link").shouldBe(visible, Duration.ofSeconds(15)).click();
        webdriver().shouldHave(url("https://www.wildberries.ru/catalog/218488991/feedbacks?imtId=298362116"));
        $(".product-feedbacks__title").shouldHave(text("��� ������"));
        $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("�������� �����"));
    }

    @Test
    @DisplayName("�������� ������. ������� � ������ ����� ������ �������� ��� ������")
    void productCardSwitchingToReviewsViaButton() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $("#footerTabs").scrollTo();
        $("a[href='/catalog/218488991/feedbacks?imtId=298362116&size=347810859']").click();
        $(".product-feedbacks__title").shouldHave(text("��� ������"));
        $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("�������� �����"));
    }

    @Test
    @DisplayName("�������� ������. ������� � ��������������")
    void productCardSwitchingToSpecifications() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $(".product-page__btn-detail.hide-mobile.j-details-btn-desktop").shouldBe(Condition.appear).click();
        $(".popup.popup-product-details.shown").shouldHave(text("�������������� � ��������"));
        $(".popup.popup-product-details.shown").shouldHave(text("�������� ����������"));
        $(".popup.popup-product-details.shown").shouldHave(text("����������� �����������"));
        $(".popup.popup-product-details.shown").shouldHave(text("�������"));
    }

    @Test
    @DisplayName("����. ������� ����� ��� ������")
    void incorrectEntry() {
        open("https://www.wildberries.ru");
        $(".navbar-pc__link.j-main-login.j-wba-header-item").shouldBe(Condition.appear).click();
        $(".popup.popup-auth-base.shown").shouldHave(text("����� ��� ������� �������"));
        $("#requestCode").click();
        $(".form-block__message.form-block__message--error").shouldHave(text("������������ ������ ������"));
    }

    @Test
    @DisplayName("�����. ������ ��� ��")
    void SearchText() {
        open("https://www.wildberries.ru");
        $("#searchInput").setValue("������ ��� ��").pressEnter();
        $(".searching-results__title").shouldBe(Condition.appear).shouldHave(text("������ ��� ��"));
        $$(".product-card__wrapper").get(0).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));
        $$(".product-card__wrapper").get(1).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));
        $$(".product-card__wrapper").get(2).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));
        $$(".product-card__wrapper").get(3).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));
        $$(".product-card__wrapper").get(4).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));
    }

    @Test
    @DisplayName("�����. ����� �� ����")
    void searchPhoto() {
        open("https://www.wildberries.ru");
        $("#searchByImageFormAbNew").click();
        $("#popUpFileInput").uploadFromClasspath("sampleFile.jpeg");
        $("#searchGoodsButton").click();
        $$(".product-card__wrapper").get(0).shouldHave(text("������".toLowerCase())).shouldHave(text("������".toUpperCase()));

    }

    @Test
    @DisplayName("�����. ������������ �����")
    void searchUncorrected() {
        open("https://www.wildberries.ru");
        $("#searchInput").setValue("**").pressEnter();
        $(".not-found-search__title").shouldHave(text("������ �� ������� �� �������"));
        $(".not-found-search__text").shouldHave(text("���������� �������� ��-������� ��� ��������� ������"));
        $(".catalog-page__section-header.section-header").shouldHave(text("��������, ��� ����������:"));

    }
}