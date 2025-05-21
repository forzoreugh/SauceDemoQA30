package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", groups = {"smoke"},
            description = "Добавление товара в корзину в количестве 1 единицы")
    @Epic("Корзина")
    @Feature("Добавление товара")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка добавления товара в корзину")
    @Flaky
    @Link(name = "Документация", url = "https://www.saucedemo.com/")
    // https://qase.com/cases/TMS_T10
    @TmsLink("TMS_T10")
    @Issue("TMS_T11")
    public void addItemInBasketTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        assertEquals(driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText(),
                "Sauce Labs Backpack");
    }
}
