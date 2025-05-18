package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", groups = {"smoke"},
            description = "Добавление товара в корзину в количестве 1 единицы")
    public void addItemInBasketTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        assertEquals(driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText(),
                "Sauce Labs Backpack");
    }
}
