package web;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.*;
import com.codeborne.xlstest.XLS;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import web.pages.WebPages;
import web.pages.WebPagesRegistrationForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebTests {
    private String browser;

    // блок ниже для кроссбраузерного тестирования
    @ParameterizedTest(name = "{index}: Running test on {0}")
    @MethodSource("browsers")
    @DisplayName("Browser Parametrization")
    void setBrowser(String browser) {
        this.browser = browser;
    }

    static Stream<String> browsers() {
        return Stream.of("chrome", "firefox"); // можно добавлять или убирать браузеры
    }

    @BeforeEach
    public  void main() throws MalformedURLException {
        //Url удалённого веб драйвера
        Configuration.remote = "http://192.168.0.103:4444/wd/hub";
        //Определяем какой браузер будем использовать
        Configuration.browser = browser;
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

    @Tag("Web")
    @Test
    @DisplayName("Header. Изменение валюты на KZT")
    void headerChangingCurrency() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Выбираем казахстанский тенге", () -> {
            $(".simple-menu__item.header__currency.j-b-header-country").hover();
            $(".country").find(byText("Казахстанский тенге")).click();
        });
        step("Проверяем хедер на наличие значка KZT", () -> {
            $(".simple-menu__item.header__currency.j-b-header-country").shouldHave(text("KZT"));
        });
        step("Проверяем что в цене карточки товара тг", () -> {
            $(".product-card__price.price").shouldHave(text("тг"));
        });
    }

    @Test
    @DisplayName("Header. Переход в доставку")
    void headerSwitchingToDelivery() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Кликаем по кнопке Адреса", () -> {
            $(".navbar-pc__item.j-item-addresses").find(byText("Адреса")).click();
        });
        step("Проверемя меню, заголовок, баннер, информацию о доставке", () -> {
            $(".service-menu__item.selected").shouldHave(text("Доставка"));
            $(".c-h1").shouldHave(text("Доставка"));
            $(".delivery-banner").shouldHave(text("Быстро доставим любой Ваш заказ по всей России"));
            $("#terms-delivery").shouldHave(text("Информация о доставке и пунктах выдачи"));
        });
    }

    @Test
    @DisplayName("Header. Переход в пустую корзину")
    void headerSwitchingToBasket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Нужимаем по кнопке Корзина", () -> {
            new WebPages().goToBasket();
        });
        step("Проверяем что корзина пустая", () -> {
            $(".basket-page__basket-empty.basket-empty").shouldHave(text("В корзине пока пусто"));
        });
    }

    @Test
    @DisplayName("Header. Переход в Продавайте на Wildberries")
    void headerSwitchingToSell() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Выбираем пункт Продавайте на Wildberries", () -> {
            $(".header__simple-menu.simple-menu").find(withText("Продавайте")).click();
        });
        step("Переходим в новое окно", () -> {
            switchTo().window(1);
        });
        step("Проверяем URL", () -> {
            webdriver().shouldHave(url("https://seller.wildberries.ru/about-portal/ru?redirect_url=https%3A%2F%2Fseller.wildberries.ru%2F"));
        });
        step("Проверяем Title", () -> {
        $(".preview_Preview__title__3xDP1").shouldHave(text("За каждой продажей стоят предприниматели"));
        });
    }

    @Test
    @DisplayName("Header. Работа в Wildberries")
    void headerSwitchingToWork() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Выбираем пункт Работа на Wildberries", () -> {
            $(".header__simple-menu.simple-menu").find(withText("Работа")).click();
        });
        step("Переходим в новое окно", () -> {
            new WebPages().switchToWindow();
        });
        step("Проверяем URL", () -> {
            webdriver().shouldHave(url("https://career.wb.ru/"));
        });
        step("Проверяем welcomeBlockWrapper", () -> {
            $(".Home_welcomeBlockWrapper__h_RWh").shouldHave(text("Вместе мы сможем больше"));
        });
    }

    @Test
    @DisplayName("Header. Переход в авиабилеты")
    void headerSwitchingToAirlineTicket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Выбираем пункт Авиабилеты", () -> {
            $(".header__simple-menu.simple-menu").find(withText("Авиабилеты")).click();
        });
        step("Переходим в новое окно", () -> {
            new WebPages().switchToWindow();
        });
        step("Проверяем URL", () -> {
            webdriver().shouldHave(url("https://www.wildberries.ru/travel?entry_point=tab_header"));
        });
        step("Проверяем common_page", () -> {
            $(".common_page__spm6Q").shouldHave(text("Популярные направления"));
            $(".common_page__spm6Q").shouldHave(text("Дешевые авиабилеты"));
        });
    }

    @Test
    @DisplayName("Корзина. Переход из пустой корзины на главную")
    void basketGoMainPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем пустую корзину", () -> {
            open("https://www.wildberries.ru/lk/basket");
        });
        step("Проверяем что корзина пустая", () -> {
            $(".basket-empty__wrap").shouldHave(text("В корзине пока пусто"));
        });
        step("Нажимаем Перейти на главную", () -> {
            $(".basket-empty__wrap").find(withText("Перейти на главную")).click();
        });
        step("Проверяем URL главной страницы", () -> {
            webdriver().shouldHave(url("https://www.wildberries.ru/"));
        });
    }

    @Test
    @DisplayName("Корзина. Удаление товара")
    void basketDeleteGoods() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Добавляем товар в корзину", () -> {
            $(".product-card__link.j-card-link.j-open-full-product-card").shouldBe(visible, Duration.ofSeconds(15)).click();
            $(".product-page__order-buttons").find(withText("Добавить в корзину")).shouldBe(visible, Duration.ofSeconds(15)).click();
        });
        step("Переходим в корзину", () -> {
            new WebPages().goToBasket();
        });
        step("Проверяем что в корзине 1 товар", () -> {
            $(".accordion__goods-count").shouldHave(text("1 товар"));
        });
        step("Удаляем товар из корзины", () -> {
            $(".btn__del.j-basket-item-del").click();
        });
        step("Проверяем что корзина пустая", () -> {
            $(".basket-empty__wrap").shouldHave(text("В корзине пока пусто"));
        });
    }

    @Test
    @DisplayName("Корзина. Увеличение количества товара")
    void basketIncreasingQuantityGoods() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем карточку тестового товара №1", () -> {
            new WebPages().openTestProductCard1();
        });
        step("Добавляем товар в корзину", () -> {
            new WebPages().productCardAddingToBasket();
        });
        step("Переходим в корзину", () -> {
            new WebPages().goToBasket();
        });
        step("Проверяем что в корзине 1 товар", () -> {
            $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 товар"));
        });
        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
        step("Делаем паузу 4 сек", () -> {
            new WebPages().forcedPause4sec();
        });
        step("Проверяем изменение стоимости после увеличения количества товара", () -> {
        // Селекторы с ценами стоимости товара в количестве 1 шт
        SelenideElement priceElement1 = $(".list-item__price-new.wallet");
        SelenideElement priceElement2 = $(".b-top__total.line span:nth-of-type(2)");
        // Извлекаем текст цен
        String priceText1 = priceElement1.getText();
        String priceText2 = priceElement2.getText();
        // Пробую через сравнение чисел
        double priceNum1 = parsePrice(priceText1);
        double priceNum2 = parsePrice(priceText2);
        // Сравниваем стоимость 1 шт в списке и в итого
        assertEquals(priceNum1, priceNum2);
        $(".count__plus.plus").click();
        $(".accordion__goods-count").shouldHave(text("2 товара"));
        // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
            new WebPages().forcedPause4sec();
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
        });
    }
    private double parsePrice(String priceText) {
        // Удаляем пробелы и символ "Р", а также преобразуем в число
        return Double.parseDouble(priceText.replaceAll("[^0-9]", ""));
    }

    @Test
    @DisplayName("Корзина. Уменьшение количества товара")
    void basketReducingQuantityGoods() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем карточку тестового товара №1", () -> {
            new WebPages().openTestProductCard1();
        });
        step("Добавляем товар в корзину", () -> {
            new WebPages().productCardAddingToBasket();
        });
        step("Переходим в корзину", () -> {
            new WebPages().goToBasket();
            new WebPages().forcedPause4sec(); // пауза нужна чтобы цены прогрузились
        });
        step("Проверяем изменение стоимости после уменьшения количества товара", () -> {
            // Селекторы с ценами стоимости товара в количестве 1 шт
            SelenideElement listItemPriceElementQuantity1 = $(".list-item__price-new.wallet");
            SelenideElement totalPriceElementQuantity1 = $(".b-top__total.line span:nth-of-type(2)");
            // Извлекаем текст цен
            String listItemPriceQuantity1Text = listItemPriceElementQuantity1.getText();
            String totalPriceQuantity1Text = totalPriceElementQuantity1.getText();
            // Пробую через сравнение чисел
            double listItemPriceQuantity1Num = parsePrice(listItemPriceQuantity1Text);
            double totalPriceQuantity1Num = parsePrice(totalPriceQuantity1Text);
            // Сравниваем стоимость 1 шт в списке и в итого
            assertEquals(listItemPriceQuantity1Num, totalPriceQuantity1Num);
            $(".count__plus.plus").click();
            $(".accordion__goods-count").shouldHave(text("2 товара"));
            // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
            new WebPages().forcedPause4sec();
            // Селекторы с ценами стоимости товара в количестве 2 шт
            SelenideElement listItemPriceElementQuantity2 = $(".list-item__price-new.wallet");
            SelenideElement totalPriceElementQuantity2 = $(".b-top__total.line span:nth-of-type(2)");
            // Извлекаем текст цен после изменения количества на 2 шт
            String listItemPriceQuantity2Text = listItemPriceElementQuantity2.getText();
            String totalPriceQuantity2Text = totalPriceElementQuantity2.getText();
            // Сравниваем стоимость 2 шт в списке и в итого в текстовом формате они должны быть равны
            assertEquals(listItemPriceQuantity2Text, totalPriceQuantity2Text);
            // Преобразовываем текст в число для сравнения
            // double priceNum1 = parsePrice(priceText1);
            double listItemPriceQuantity2Num = parsePrice(listItemPriceQuantity2Text);
            double totalPriceQuantity2Num = parsePrice(totalPriceQuantity2Text);

            // Проверяем что цена увеличилась в 2 раза
            assertEquals(listItemPriceQuantity1Num * 2, listItemPriceQuantity2Num);
            /*
            $(".accordion__goods-count").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("1 товар"));
            $(".count__plus.plus").click();
            $(".accordion__goods-count").shouldHave(text("2 товара"));
            // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
            new WebPages().forcedPause4sec();
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
         */
            $(".count__minus.minus").click();
            $(".accordion__goods-count").shouldHave(text("1 товар"));
            // Пауза на 4 секунды, так как окончательная цена рассчитывается в процессе прогрузки страницы
            new WebPages().forcedPause4sec();
            // Селекторы с ценами стоимости после изменения количества на 1 шт
            SelenideElement listItemPriceElementQuantity1New = $(".list-item__price-new.wallet");
            SelenideElement totalPriceElementQuantity1New = $(".b-top__total.line span:nth-of-type(2)");
            // Извлекаем текст цен после изменения количества на 1 шт
            String listItemPriceQuantity1TextNew = listItemPriceElementQuantity1New.getText();
            String totalPriceQuantity1TextNew = totalPriceElementQuantity1New.getText();
            // Сравниваем стоимость 2 шт в списке и в итого в текстовом формате они должны быть равны
            assertEquals(listItemPriceQuantity1TextNew, totalPriceQuantity1TextNew);
            // Преобразовываем текст в число для сравнения
            // double priceNum1 = parsePrice(priceText1);
            double listItemPriceQuantity1NumNew = parsePrice(listItemPriceQuantity1TextNew);

            // Сравнение числовых значений
            assertEquals(listItemPriceQuantity2Num / 2, listItemPriceQuantity1NumNew);
    });
    }

    @Test
    @DisplayName("Карточка товара. Переход в отзывы через оценку")
    void productCardSwitchingToReviewsViaRating() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем карточку тестового товара №1", () -> {
            new WebPages().openTestProductCard1();
        });
        step("Нажимаем на оценку", () -> {
            $("#comments_reviews_link").shouldBe(visible, Duration.ofSeconds(15)).click();
        });
        step("Проверяем URL", () -> {
            webdriver().shouldHave(url("https://www.wildberries.ru/catalog/218488991/feedbacks?imtId=298362116"));
        });
        step("Проверяем title", () -> {
            $(".product-feedbacks__title").shouldHave(text("Все отзывы"));
        });
        step("Проверяем наличие кнопки Написать отзыв", () -> {
            $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("Написать отзыв"));
        });
    }

    @Test
    @DisplayName("Карточка товара. Переход в отзывы через кнопку Смотреть все отзывы")
    void productCardSwitchingToReviewsViaButton() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем карточку тестового товара №1", () -> {
            new WebPages().openTestProductCard1();
        });
        step("Принудительная пауза 5 сек", () -> {
            new WebPages().forcedPause4sec();
        });
        step("Нажимаем на кнопку Все отзывы", () -> {
            $("#footerTabs").scrollTo();
            $("a[href='/catalog/218488991/feedbacks?imtId=298362116&size=347810859']").click();
        });
        step("Проверяем title", () -> {
            $(".product-feedbacks__title").shouldHave(text("Все отзывы"));
        });
        step("Проверяем наличие кнопки Написать отзыв", () -> {
            $(".btn-base.btn-base--lg.rating-product__btn").shouldHave(text("Написать отзыв"));
        });
    }

    @Test
    @DisplayName("Карточка товара. Переход в характеристики")
    void productCardSwitchingToSpecifications() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем карточку тестового товара №1", () -> {
            new WebPages().openTestProductCard1();
        });
        step("Переходим в характеристики", () -> {
            $(".product-page__btn-detail.hide-mobile.j-details-btn-desktop").shouldBe(Condition.appear).click();
        });
        step("Проверяем необходимые данные в характеристиках", () -> {
            $(".popup.popup-product-details.shown").shouldHave(text("Характеристики и описание"));
            $(".popup.popup-product-details.shown").shouldHave(text("Основная информация"));
            $(".popup.popup-product-details.shown").shouldHave(text("Технические особенности"));
            $(".popup.popup-product-details.shown").shouldHave(text("Насадки"));
        });
    }

    @Test
    @DisplayName("Вход. Попытка входа без номера")
    void incorrectEntry() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Нажимаем на кнопку Войти", () -> {
            $(".navbar-pc__link.j-main-login.j-wba-header-item").shouldBe(Condition.appear).click();
        });
        step("Проверяем что появилось окно для входа", () -> {
            $(".popup.popup-auth-base.shown").shouldHave(text("Войти или создать профиль"));
        });
        step("Нажимаем кнопку Получить код без указания номера телефона", () -> {
            $("#requestCode").click();
        });
        step("Проверяем наличие записи Некорректный формат номера", () -> {
            $(".form-block__message.form-block__message--error").shouldHave(text("Некорректный формат номера"));
        });
    }

    @Test
    @DisplayName("Поиск. Поиск по тексту")
    void SearchText() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Вводим поисковый запрос", () -> {
            $("#searchInput").setValue("корпус для пк").pressEnter();
        });
        step("Проверяем title поиска", () -> {
            $(".searching-results__title").shouldBe(Condition.appear).shouldHave(text("корпус для пк"));
        });
        step("Проверяем названия карточек на соответствие запросу", () -> {
            $$(".product-card__wrapper").get(0).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
            $$(".product-card__wrapper").get(1).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
            $$(".product-card__wrapper").get(2).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
            $$(".product-card__wrapper").get(3).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
            $$(".product-card__wrapper").get(4).shouldHave(text("корпус".toLowerCase())).shouldHave(text("корпус".toUpperCase()));
        });
    }

    @Test
    @DisplayName("Поиск. Поиск по фото")
    void searchPhoto() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("Выбираем поиск по фото", () -> {
            $("#searchByImageFormAbNew").click();
        });
        step("Загружаем картинку", () -> {
            $("#popUpFileInput").uploadFromClasspath("sampleFile.jpeg");
        });
        step("Нажимаем кнопку поиска", () -> {
            $("#searchGoodsButton").click();
        });
        step("Проверяем карточки товара на соответствие нашему запросу", () -> {
            $$(".product-card__wrapper").get(0).shouldHave(text("сияние".toLowerCase())).shouldHave(text("сияние".toUpperCase()));
        });
    }

    @Test
    @DisplayName("Поиск. Неккоректный поиск")
    void searchUncorrected() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            new WebPages().openMainPage();
        });
        step("В неккоректные поисковой запрос", () -> {
            $("#searchInput").setValue("**").pressEnter();
        });
        step("Проверяем информационные сообщения при неудачном поиске", () -> {
            $(".not-found-search__title").shouldHave(text("Ничего не нашлось по запросу"));
            $(".not-found-search__text").shouldHave(text("Попробуйте поискать по-другому или сократить запрос"));
            $(".catalog-page__section-header.section-header").shouldHave(text("Возможно, вам понравится:"));
        });
    }

    @Test
    @DisplayName("Регистрация. Заполнение формы регистрации сгенерированными тестовыми данными")
    void registrationForm() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebPagesRegistrationForm form = new WebPagesRegistrationForm();
            form.openPage();
            form.setFirstName();
            form.setLastName();
            form.setEmail();
            form.clickGenderRadio();
            form.setNumber();
            form.setBirthDay();
            form.setSubject();
            form.setHobbi();
            form.uploadPicture();
            form.setAddress();
            form.setStateCity();
            form.clickSubmit();
            form.shouldTable();
    }

    @Test
    @DisplayName("Загрузка картинки из ресурсов")
    void selenideUploadTest() {
        step("Открываем страницу загрузки картинки", () -> {
            open("https://demoqa.com/upload-download");
        });
        step("Загружаем картинку из ресурсов", () -> {
            $("input[type='file']").uploadFromClasspath("sampleFile.jpg");
        });
        step("Проверяем что картинка отображается в загруженных", () -> {
            $("#uploadedFilePath").shouldHave(text("sampleFile.jpg"));
        });
    }

    @Test
    @DisplayName("Скачивание и проверка xls документа")
    void selenideDownloadXlsTest() throws Exception {
        step("Открываем страницу с xls документом", () -> {
            open("https://samplelib.com/ru/sample-xls.html");
        });
        step("Скачиваем xls документ и проверяем его содержимое", () -> {
            File downloadedXls = $("a[href='https://download.samplelib.com/xls/sample-simple-1.xls']").download();
            XLS content = new XLS(downloadedXls);
            assertThat(content.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue().contains("test1"));
        });
        // getSheetAt(0) - это первая таблица в файле
        // getRow(0) - это первая строка
        // getCell(0) - это первая колонка
    }

}