package ru.dodopizza.autotests.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Домашняя страница
 */
public class HomePage {

    /**
     * Кнопка закрыть уведомления о куках
     */
    private final SelenideElement closeCookieNotificationButton
        = $x("//button[@class='cookie-policy__button']");

    /**
     * Кнопка 'Корзина'
     */
    private final SelenideElement cartButton
        = $x("//div[@data-testid='navigation__cart']/button");

    /**
     * Счетчик добавленных товаров
     */
    private final SelenideElement addProductCounter
        = $x("//div[@data-testid='cart-button__quantity']");

    /**
     * Уведомление об успешном добавлении товара в корзину
     */
    private final SelenideElement addProductToCartSuccessfulNotification
        = $x("//div[contains(@class, 'notification')]");

    /**
     * Список кнопок 'Выбрать' которая находится возле пиццы
     */
    private final ElementsCollection listChooseButtons
        = $$x("//section[@id='pizzas']/article//button[text()='Выбрать']");

    /**
     * Список пицц
     */
    private final ElementsCollection listOfPizzas
        = $$x("//section[@id='pizzas']/article");

    /**
     * Заголовок 'Регион'
     */
    private final SelenideElement regionHeader
        = $x("//a[contains(@class, 'locality header')]");

    /**
     * Проверка количества пицц в разделе 'Пицца'
     * @param numberOfPizzas - Ожидаемое количество пицц
     * @return Home page
     */
    @Step("Проверить количество пицц")
    public final HomePage checkNumberOfPizzas(int numberOfPizzas) {
        listOfPizzas.get(0).shouldBe(Condition.visible);
        Assert.assertEquals(listOfPizzas.size(), numberOfPizzas);
        return this;
    }

    /**
     * Проверка наименования и отображения региона
     * @param value - ожидаемое значение региона
     * @return Home page
     */
    @Step("Проверить отображение и наименование региона")
    public final HomePage checkRegionValue(String value) {
        regionHeader.shouldBe(Condition.visible);
        Assert.assertEquals(regionHeader.getText(), value);
        return this;
    }

    /**
     * Выбрать для добавления в корзину случайную пиццу
     * @return Страница добавления пиццы в корзину(AddPizzaToCartPage)
     */
    @Step("Выбрать случайную пиццу")
    public final AddPizzaToCartPage chooseRandomPizza() {
        listChooseButtons.get(0).shouldBe(Condition.visible);
        Random random = new Random();
        int buttonIndex = random.nextInt(listChooseButtons.size());
        listChooseButtons.get(buttonIndex).click();
        return new AddPizzaToCartPage();
    }

    @Step("Закрыть уведомление о куках")
    public final HomePage closeCookiesNotification() {
        cartButton.shouldBe(Condition.visible);
        if (closeCookieNotificationButton.isDisplayed()) {
            closeCookieNotificationButton.shouldBe(Condition.visible).click();
        }
        return this;
    }

    @Step("Выбрать пиццу по индексу {index}")
    public final AddPizzaToCartPage choosePizzaByIndex(int index) {
        listChooseButtons.shouldHave(CollectionCondition.size(31));
        listChooseButtons.get(index).shouldBe(Condition.visible).click();
        return new AddPizzaToCartPage();
    }

    @Step("Нажать на кнопку 'Корзина'")
    public final CartPage clickToCartButton() {
        cartButton.click();
        return new CartPage();
    }

    /**
     * Проверить появление уведомления о добавлении товаров
     * @return home page
     */
    @Step("Проверить появление уведомления о добавлении товаров")
    public final HomePage checkNotificationPresent() {
        addProductToCartSuccessfulNotification.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Проверить значение счетчика добавленных товаров
     * @param expectedValue - Ожидаемое значение
     * @return home page
     */
    @Step("Проверить значение счетчика добавленных товаров")
    public final HomePage checkCounter(String expectedValue) {
        addProductCounter.shouldBe(Condition.visible);
        addProductCounter.shouldHave(Condition.text(expectedValue));
        Assert.assertEquals(addProductCounter.getText(), expectedValue);
        return this;
    }

    @Step("Получить название пиццы по индексу")
    public final String getPizzaNameByIndex(int index) {
        return listChooseButtons.get(index).parent().parent().$x("main").$x("div").getText();
    }

    @Step("Получить цену пиццы по индексу")
    public final String getPizzaPriceByIndex(int index) {
        return listChooseButtons.get(index).parent().$x("div").getText();
    }

    @Step("Проверить текущий url страницы")
    public final HomePage checkUrl(String expectedUrl) {
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), expectedUrl);
        return this;
    }
}
