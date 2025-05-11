package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.cssSelector("[data-test = title]");

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addItemToCart() {
        driver.findElement(By.xpath("//*[text() = 'Sauce Labs Backpack']" +
                "/ancestor::div[@class = 'inventory_item']//button")).click();
    }

    public void openCart() {
        driver.findElement(By.cssSelector("[data-test=shopping-cart-link]")).click();
    }

    public void continueCheckout(){
        driver.findElement(By.cssSelector(".btn.btn_action.btn_medium.checkout_button ")).click();
    }
}
