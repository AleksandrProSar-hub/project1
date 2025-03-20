package web.pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class WebPages {

    public void openMainPage (){
        open("https://www.wildberries.ru/");
    }

    public void switchToWindow (){
        switchTo().window(1);
    }

    public void forcedPause4sec (){
        try {
            Thread.sleep(4000); // Пауза на 3000 миллисекунд (3 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void goToBasket (){
        $(".navbar-pc__item.j-item-basket").click();
    }

    public void openTestProductCard1 (){
        open("https://www.wildberries.ru/catalog/189328347/detail.aspx");
    }

    public void productCardAddingToBasket (){
        $(".product-page__order-buttons").find(withText("Добавить в корзину")).shouldBe(visible, Duration.ofSeconds(15)).click();
    }


}
