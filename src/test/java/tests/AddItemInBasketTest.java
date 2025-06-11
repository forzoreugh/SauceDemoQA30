package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class AddItemInBasketTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", groups = {"smoke"})
    @Epic("Корзина")
    @Feature("Добавление товара в корзину")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка успешного добавления товара в корзину в количестве 1 единицы")
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
