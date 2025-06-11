package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final By CART_BUTTON = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN =
            "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage assertOpenPage() {
        assertEquals(getTitle(), "Products", "Отличается наименование раздела");
        return this;
    }

    public ProductsPage assertAddNameProduct() {
        assertEquals(driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText(),
                "Sauce Labs Backpack", "Отличается наименование товара");
        return this;
    }

    @Override
    public ProductsPage open() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkout']")));
        return this;
    }

    @Step("Добавление товара в корзину с именем: {product}")
    public ProductsPage addItemToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку перехода в корзину")
    public ProductsPage openCart() {
        driver.findElement(By.cssSelector("[data-test=shopping-cart-link]")).click();
        return this;
    }

    @Step("Нажатие на кнопку проверки оформления заказа на странице Product Page")
    public CheckoutInformationPage continueCheckout() {
        driver.findElement(By.cssSelector(".btn.btn_action.btn_medium.checkout_button ")).click();
        return new CheckoutInformationPage(driver);
    }
}
