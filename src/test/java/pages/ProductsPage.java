package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final By CART_BUTTON = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN =
            "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private static final Logger log = LogManager.getLogger(ProductsPage.class);

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage assertOpenPage() {
        log.info("[Product page] Checking for an open page");
        assertEquals(getTitle(), "Products", "Отличается наименование раздела");
        return this;
    }

    public ProductsPage assertAddNameProduct() {
        log.info("[Product page] Checking the actual name of the expected product");
        assertEquals(driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText(),
                "Sauce Labs Backpack", "Отличается наименование товара");
        return this;
    }

    @Override
    public ProductsPage open() {
        log.info("Open Product page");
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//button[@id='checkout']")));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("[Product page] Page isn't opened");
        }
        return this;
    }

    @Step("Добавление товара в корзину с именем: {product}")
    public ProductsPage addItemToCart(String product) {
        log.info("[Product page] Adding a product to the cart with the name: {}", product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку перехода в корзину")
    public ProductsPage openCart() {
        log.info("[Product page] Clicking on the go to cart button");
        driver.findElement(By.cssSelector("[data-test=shopping-cart-link]")).click();
        return this;
    }

    @Step("Нажатие на кнопку проверки оформления заказа на странице Product Page")
    public CheckoutInformationPage continueCheckout() {
        log.info("[Product page] Clicking on the checkout button on the page");
        driver.findElement(By.cssSelector(".btn.btn_action.btn_medium.checkout_button ")).click();
        return new CheckoutInformationPage(driver);
    }
}
