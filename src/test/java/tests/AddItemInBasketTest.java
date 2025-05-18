package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AddItemInBasketTest extends BaseTest {

    @Test
    public void addItemInBasketTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String title = driver.findElement(By.cssSelector(".title ")).getText();
        softAssert.assertEquals(title, "Products", "Логин не выполнен");
        String addText = driver.findElement(By.xpath
                ("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).getText();
        softAssert.assertEquals(addText, "Add to cart", "Неверное наименование " +
                "кнопки добавления товара.");
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ")).click();
        String removeText = driver.findElement(By.xpath
                ("//*[@id=\"remove-sauce-labs-backpack\"]")).getText();
        softAssert.assertEquals(removeText, "Remove", "Неверное наименование кнопки удаления товара");
        String nameProduct = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText();
        String countProduct = driver.findElement(By.xpath
                ("//*[@id=\"inventory_container\"]/div/div[3]/div[2]/div[2]/div")).getText();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        String payNameProduct = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        String payCountProduct = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        softAssert.assertEquals(nameProduct, payNameProduct);
        softAssert.assertEquals(countProduct, payCountProduct);
    }
}
