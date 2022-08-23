package ru.dodopizza.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Страница корзины
 */
public class CartPage {

    /**
     * Список пицц в корзине
     */
    private final ElementsCollection listOfPizzas
        = $$x("//section[@data-testid]/article");

    @Step("Проверить название пиццы в корзине")
    public final CartPage checkPizzaName(String expectedName) {
        listOfPizzas.get(0).shouldBe(Condition.visible);
        List<String> names = listOfPizzas
            .stream()
            .map(element -> element.$x("div[2]/div/h3").getText())
            .collect(Collectors.toList());
        Assert.assertTrue(names.stream().anyMatch(name->expectedName.contains(name)));
        return this;
    }

    @Step("Проверить цену пиццы в корзине")
    public final CartPage checkPizzaPrice(String expectedPrice) {
        listOfPizzas.get(0).shouldBe(Condition.visible);
        List<String> prices = listOfPizzas
            .stream()
            .map(element -> element.$x("div[3]/div/div[@class='current']").getText())
            .collect(Collectors.toList());
        Assert.assertTrue(prices.stream().anyMatch(price->expectedPrice.contains(price)));
        return this;
    }
}
