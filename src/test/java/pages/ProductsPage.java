package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.cssSelector("[data-test = title]");


    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void getShop() {
        driver.findElement(By.xpath("//*[text() = 'Sauce Labs Backpack']" +
                "/ancestor::div[@class = 'inventory_item']//button")).click();
    }

    public void click() {
        driver.findElement(By.cssSelector("[data-test=shopping-cart-link]")).click();
    }
}
