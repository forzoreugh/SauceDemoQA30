package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", groups = {"smoke"},
            description = "Добавление товара в корзину в количестве 1 единицы")
    @Epic("Корзина")
    @Feature("Добавление товара")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка успешного добавления товара в корзину в количестве 1 единицы")
    @Link(name = "Документация", url = "https://www.saucedemo.com/")
    // https://qase.com/cases/TMS_T10
    @TmsLink("TMS_TEST1")
    @Issue("TMS_TEST2")
    public void addItemInBasketTest() {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .assertOpenPage();
        productsPage.addItemToCart("Sauce Labs Backpack")
                .openCart()
                .isPageOpened()
                .assertAddNameProduct();
    }
}
