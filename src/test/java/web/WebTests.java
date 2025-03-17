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
        //Url удалённого веб драйвера
        Configuration.remote = "http://192.168.0.103:4444/wd/hub";
        //Определяем какой браузер будем использовать
        Configuration.browser = "chrome";
        //Размер окна браузера
        Configuration.browserSize = "1920x1080";
        //Создаём объект класса DesiredCapabilities, используется как настройка  вашей конфигурации с помощью пары ключ-значение
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
        }});

        Configuration.browserCapabilities = capabilities;
    }
    // возможно лишний кусок
    @AfterEach
    public void main2() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("headerChangingCurrency")
    void headerChangingCurrency() {

        open("https://www.wildberries.ru/");
        $(".simple-menu__item.header__currency.j-b-header-country").hover();
        $(".country").find(byText("Казахстанский тенге")).click();
        $(".simple-menu__item.header__currency.j-b-header-country").shouldHave(text("KZT"));
        $(".product-card__price.price").shouldHave(text("тг"));
    }

    @Test
    @DisplayName("Хедер. Переход в доставку")
    void headerSwitchingToDelivery() {
        open("https://www.wildberries.ru/");
        $(".navbar-pc__item.j-item-addresses").find(byText("Адреса")).click();
        $(".service-menu__item.selected").shouldHave(text("Доставка"));
        $(".c-h1").shouldHave(text("Доставка"));
        $(".delivery-banner").shouldHave(text("Быстро доставим любой Ваш заказ по всей России"));
        $("#terms-delivery").shouldHave(text("Информация о доставке и пунктах выдачи"));
    }

    @Test
    @DisplayName("Хедер. Переход в корзину")
    void headerSwitchingToBasket() {
        open("https://www.wildberries.ru/");
        $(".navbar-pc__item.j-item-basket").find(byText("Корзина")).click();
        $(".basket-page__basket-empty.basket-empty").shouldHave(text("В корзине пока пусто"));
    }

    @Test
    @DisplayName("Хедер. Продавайте на Wildberries")
    void headerSwitchingToSell() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("Продавайте")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://seller.wildberries.ru/about-portal/ru?redirect_url=https%3A%2F%2Fseller.wildberries.ru%2F"));
        $(".preview_Preview__title__3xDP1").shouldHave(text("За каждой продажей стоят предприниматели"));
    }

    @Test
    @DisplayName("Хедер. Работа в Wildberries")
    void headerSwitchingToWork() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("Работа")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://career.wb.ru/"));
        $(".Home_welcomeBlockWrapper__h_RWh").shouldHave(text("Вместе мы сможем больше"));
    }

    @Test
    @DisplayName("Хедер. Переход в авиабилеты")
    void headerSwitchingToAirlineTicket() {
        open("https://www.wildberries.ru/");
        $(".header__simple-menu.simple-menu").find(withText("Авиабилеты")).click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://www.wildberries.ru/travel?entry_point=tab_header"));
        $(".common_page__spm6Q").shouldHave(text("Популярные направления"));
        $(".common_page__spm6Q").shouldHave(text("Дешевые авиабилеты"));
    }

    @Test
    @DisplayName("Корзина. Переход из пустой корзины на главную")
    void basketGoMainPage() {
        open("https://www.wildberries.ru/lk/basket");
        $(".basket-empty__wrap").shouldHave(text("В корзине пока пусто"));
        $(".basket-empty__wrap").find(withText("Перейти на главную")).click();
        webdriver().shouldHave(url("https://www.wildberries.ru/"));
    }

    @Test
    @DisplayName("Корзина. Удаление товара")
    void basketDeleteGoods() {
        open("https://www.wildberries.ru/");
        $(".product-card__link.j-card-link.j-open-full-product-card").shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".product-page__order-buttons").find(withText("Добавить в корзину")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldHave(text("1 товар"));
        $(".btn__del.j-basket-item-del").click();
        $(".basket-empty__wrap").shouldHave(text("В корзине пока пусто"));
    }

    @Test
    @DisplayName("Корзина. Увеличение количества товара")
    void basketIncreasingQuantityGoods() {

        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $(".product-page__order-buttons").find(withText("Добавить в корзину")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 товар"));

        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
        try {
            Thread.sleep(4000); // Пауза на 3000 миллисекунд (3 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Селекторы с ценами стоимости товара в количестве 1 шт
        SelenideElement priceElement1 = $(".list-item__price-new.wallet");
        SelenideElement priceElement2 = $(".b-top__total.line span:nth-of-type(2)");

        // Извлекаем текст цен
        String priceText1 = priceElement1.getText();
        String priceText2 = priceElement2.getText();
        // Пробую через сравнение чисел
        double priceNum1 = parsePrice(priceText1);
        double priceNum2 = parsePrice(priceText2);
        assertEquals(priceNum1, priceNum2);
        // Сравниваем стоимость 1 шт в списке и в итого
        // assertEquals(priceText1, priceText2);
        $(".count__plus.plus").click();
        $(".accordion__goods-count").shouldHave(text("2 товара"));
        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
        try {
            Thread.sleep(4000); // Пауза на 3000 миллисекунд (3 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Селекторы с ценами стоимости товара в количестве 2 шт
        SelenideElement priceElement3 = $(".list-item__price-new.wallet");
        SelenideElement priceElement4 = $(".b-top__total.line span:nth-of-type(2)");
        // Извлекаем текст цен после изменения количества на 2 шт
        String priceText3 = priceElement3.getText();
        String priceText4 = priceElement4.getText();
        // Сравниваем стоимость 2 шт в списке и в итого в текстовом формате они должны быть равны
        assertEquals(priceText3, priceText4);
        // Преобразовываем текст в число для сравнения
        // double priceNum1 = parsePrice(priceText1);
        double priceNum3 = parsePrice(priceText3);
        double priceNum4 = parsePrice(priceText2);

        // Сравнение числовых значений
        assertEquals(priceNum1 * 2, priceNum3);
    }
    private double parsePrice(String priceText) {
        // Удаляем пробелы и символ "Р", а также преобразуем в число
        return Double.parseDouble(priceText.replaceAll("[^0-9]", ""));
    }

    @Test
    @DisplayName("Корзина. Уменьшение количества товара")
    void basketReducingQuantityGoods() {

        open("https://www.wildberries.ru/catalog/102721617/detail.aspx");
        $(".product-page__order-buttons").find(withText("Добавить в корзину")).shouldBe(visible, Duration.ofSeconds(15)).click();
        $(".navbar-pc__item.j-item-basket").click();
        $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 товар"));
        $(".count__plus.plus").click();
        $(".accordion__goods-count").shouldHave(text("2 товара"));
        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
        try {
            Thread.sleep(4000); // Пауза на 3000 миллисекунд (3 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Селекторы с ценами стоимости товара в количестве 2 шт
        SelenideElement priceElement1 = $(".list-item__price-new.wallet");
        SelenideElement priceElement2 = $(".b-top__total.line span:nth-of-type(2)");

        // Извлекаем текст цен
        String priceText1 = priceElement1.getText();
        String priceText2 = priceElement2.getText();
        // Парсим строку в числа
        double priceNum1 = parsePrice(priceText1);
        double priceNum2 = parsePrice(priceText2);
        assertEquals(priceNum1, priceNum2);        // Сравниваем стоимость 2 шт в списке и в итого

        $(".count__minus.minus").click();
        $(".accordion__goods-count").shouldHave(text("1 товар"));
        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
        try {
            Thread.sleep(4000); // Пауза на 3000 миллисекунд (3 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Селекторы с ценами стоимости товара в количестве 2 шт
        SelenideElement priceElement3 = $(".list-item__price-new.wallet");
        SelenideElement priceElement4 = $(".b-top__total.line span:nth-of-type(2)");
        // Извлекаем текст цен после изменения количества на 2 шт
        String priceText3 = priceElement3.getText();
        String priceText4 = priceElement4.getText();
        // Сравниваем стоимость 2 шт в списке и в итого в текстовом формате они должны быть равны
        assertEquals(priceText3, priceText4);
        // Преобразовываем текст в число для сравнения
        // double priceNum1 = parsePrice(priceText1);
        double priceNum3 = parsePrice(priceText3);

        // Сравнение числовых значений
        assertEquals(priceNum1 / 2, priceNum3);
    }

    @Test
    @DisplayName("Карточка товара. Переход в отзывы через оценку")
    void productCardSwitchingToReviewsViaRating() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $("#comments_reviews_link").shouldBe(visible, Duration.ofSeconds(15)).click();
        webdriver().shouldHave(url("https://www.wildberries.ru/catalog/218488991/feedbacks?imtId=298362116"));
        $(".product-feedbacks__title").shouldHave(text("Все отзывы"));
        $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("Написать отзыв"));
    }

    @Test
    @DisplayName("Карточка товара. Переход в отзывы через кнопку Смотреть все отзывы")
    void productCardSwitchingToReviewsViaButton() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $("#footerTabs").scrollTo();
        $("a[href='/catalog/218488991/feedbacks?imtId=298362116&size=347810859']").click();
        $(".product-feedbacks__title").shouldHave(text("Все отзывы"));
        $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("Написать отзыв"));
    }

    @Test
    @DisplayName("Карточка товара. Переход в характеристики")
    void productCardSwitchingToSpecifications() {
        open("https://www.wildberries.ru/catalog/218488991/detail.aspx");
        $(".product-page__btn-detail.hide-mobile.j-details-btn-desktop").shouldBe(Condition.appear).click();
        $(".popup.popup-product-details.shown").shouldHave(text("Характеристики и описание"));
        $(".popup.popup-product-details.shown").shouldHave(text("Основная информация"));
        $(".popup.popup-product-details.shown").shouldHave(text("Технические особенности"));
        $(".popup.popup-product-details.shown").shouldHave(text("Насадки"));
    }

    @Test
    @DisplayName("Вход. Попытка входа без номера")
    void incorrectEntry() {
        open("https://www.wildberries.ru");
        $(".navbar-pc__link.j-main-login.j-wba-header-item").shouldBe(Condition.appear).click();
        $(".popup.popup-auth-base.shown").shouldHave(text("Войти или создать профиль"));
        $("#requestCode").click();
        $(".form-block__message.form-block__message--error").shouldHave(text("Некорректный формат номера"));
    }

    @Test
    @DisplayName("Поиск. Корпус для ПК")
    void SearchText() {
        open("https://www.wildberries.ru");
        $("#searchInput").setValue("корпус для пк").pressEnter();
        $(".searching-results__title").shouldBe(Condition.appear).shouldHave(text("корпус для пк"));
        $$(".product-card__wrapper").get(0).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
        $$(".product-card__wrapper").get(1).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
        $$(".product-card__wrapper").get(2).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
        $$(".product-card__wrapper").get(3).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
        $$(".product-card__wrapper").get(4).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
    }

    @Test
    @DisplayName("Поиск. Поиск по фото")
    void searchPhoto() {
        open("https://www.wildberries.ru");
        $("#searchByImageFormAbNew").click();
        $("#popUpFileInput").uploadFromClasspath("sampleFile.jpeg");
        $("#searchGoodsButton").click();
        $$(".product-card__wrapper").get(0).shouldHave(text("сияние".toLowerCase())).shouldHave(text("сияние".toUpperCase()));

    }

    @Test
    @DisplayName("Поиск. Неккоректный поиск")
    void searchUncorrected() {
        open("https://www.wildberries.ru");
        $("#searchInput").setValue("**").pressEnter();
        $(".not-found-search__title").shouldHave(text("Ничего не нашлось по запросу"));
        $(".not-found-search__text").shouldHave(text("Попробуйте поискать по-другому или сократить запрос"));
        $(".catalog-page__section-header.section-header").shouldHave(text("Возможно, вам понравится:"));

    }
}