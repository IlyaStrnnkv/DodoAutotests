package ru.dodopizza.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница выбора города
 */
public class ChooseCityPage {

    /**
     * Кнопка 'Москва'
     */
    private final SelenideElement moscowButton
        = $x("//div[contains(@class, 'big-city-container')]/a[@href='/moscow']");

    /**
     * Клик по кнопке 'Москва'
     * @return Home page
     */
    @Step("Выбрать город Москва")
    public final HomePage clickToMoscowButton() {
        moscowButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }
}
