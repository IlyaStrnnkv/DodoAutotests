package ru.dodopizza.autotests.tests.cart;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.dodopizza.autotests.pages.ChooseCityPage;
import ru.dodopizza.autotests.pages.HomePage;
import ru.dodopizza.autotests.testsSetUp.BaseSetUpOfTest;

import static com.codeborne.selenide.Selenide.open;

@Epic("Корзина товаров")
public class CartTest extends BaseSetUpOfTest {

    HomePage homePage;

    @BeforeMethod
    public final void setUp() {
        open("/");
        homePage = new ChooseCityPage()
            .clickToMoscowButton()
            .closeCookiesNotification();
    }

    @Feature("Добавление товара в корзину")
    @Story("Добавление товара в корзину и проверка счетчика добавленных товаров")
    @Test(groups = {smoke}, description = "Добавление товара в корзину и проверка счетчика добавленных товаров")
    @Severity(SeverityLevel.BLOCKER)
    public final void addSmallPizzaInCart() {
        homePage
            .chooseRandomPizza()
            .chooseSmallPizza()
            .addPizzaToCart()
            .checkNotificationPresent()
            .checkCounter("1");
    }

    @Feature("Добавление товара в корзину")
    @Story("Добавление нескольких товаров в корзину, проверка названий товаров и их цены")
    @Test(groups = {smoke}, description = "Добавление нескольких товаров в корзину, проверка названий товаров и их цены")
    @Severity(SeverityLevel.BLOCKER)
    public final void test() {
        homePage
            .choosePizzaByIndex(0)
            .chooseSmallPizza()
            .addPizzaToCart()
            .choosePizzaByIndex(1)
            .chooseSmallPizza()
            .addPizzaToCart()
            .choosePizzaByIndex(2)
            .chooseSmallPizza()
            .addPizzaToCart()
            .choosePizzaByIndex(3)
            .chooseSmallPizza()
            .addPizzaToCart()
            .choosePizzaByIndex(4)
            .chooseSmallPizza()
            .addPizzaToCart()
            .checkCounter("5")
            .clickToCartButton()
            .checkPizzaName(homePage.getPizzaNameByIndex(0))
            .checkPizzaName(homePage.getPizzaNameByIndex(1))
            .checkPizzaName(homePage.getPizzaNameByIndex(2))
            .checkPizzaName(homePage.getPizzaNameByIndex(3))
            .checkPizzaName(homePage.getPizzaNameByIndex(4))
            .checkPizzaPrice(homePage.getPizzaPriceByIndex(0))
            .checkPizzaPrice(homePage.getPizzaPriceByIndex(1))
            .checkPizzaPrice(homePage.getPizzaPriceByIndex(2))
            .checkPizzaPrice(homePage.getPizzaPriceByIndex(3))
            .checkPizzaPrice(homePage.getPizzaPriceByIndex(4));
    }
}
