package ru.dodopizza.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница добавления пиццы в корзину
 */
public class AddPizzaToCartPage {

    /**
     * Кнопка 'Маленькая'
     */
    private final SelenideElement smallButton
        = $x("//label[text()='Маленькая']");

    /**
     * Кнопка 'Добавить в корзину'
     */
    private final SelenideElement addToCartButton
        = $x("//div[@class='gsrbdo-18 dlfBaI']/button");

    /**
     * Выбрать маленькую пиццу
     * @return Страницу добавления пиццы в корзину
     */
    @Step("Выбрать маленькую пиццу")
    public final AddPizzaToCartPage chooseSmallPizza() {
        smallButton.shouldBe(Condition.visible).click();
        return this;
    }

    /**
     * Добавить пиццу в корзину
     * @return home page
     */
    @Step("Добавить пиццу в корзину")
    public final HomePage addPizzaToCart() {
        addToCartButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }
}
