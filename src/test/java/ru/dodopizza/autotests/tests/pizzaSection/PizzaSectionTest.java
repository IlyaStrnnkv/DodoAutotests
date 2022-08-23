package ru.dodopizza.autotests.tests.pizzaSection;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.dodopizza.autotests.pages.ChooseCityPage;
import ru.dodopizza.autotests.pages.HomePage;
import ru.dodopizza.autotests.testsSetUp.BaseSetUpOfTest;

import static com.codeborne.selenide.Selenide.open;
import static ru.dodopizza.autotests.data.home.HomePageData.*;

@Epic("Домашняя страница")
public class PizzaSectionTest extends BaseSetUpOfTest {

    HomePage homePage;

    @BeforeMethod
    public final void setUp() {
        open("/");
        homePage = new ChooseCityPage()
            .clickToMoscowButton()
            .closeCookiesNotification();
    }

    @Feature("Отображение информации на странице")
    @Story("Проверка отображения региона, проверка количества пицц")
    @Test(groups = {smoke}, description = "Проверка отображения региона, проверка количества пицц")
    @Severity(SeverityLevel.CRITICAL)
    public final void checkNumberOfPizzasAndRegion() {
        homePage
            .checkNumberOfPizzas(NUMBER_OF_PIZZAS)
            .checkRegionValue(MOSCOW_REGION)
            .checkUrl(CHECKED_URL);
    }
}
