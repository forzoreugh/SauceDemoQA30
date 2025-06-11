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
import static org.testng.Assert.assertTrue;

@Log4j2
public class CheckoutInformationPage extends BasePage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By ZIP_CODE = By.id("postal-code");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By CONTINUE_BUTTON = By.cssSelector(".submit-button.btn.btn_primary.cart_button.btn_action");
    private static final By CANCEL_BUTTON = By.cssSelector(".btn.btn_secondary.back.btn_medium.cart_cancel_link");
    private static final Logger log = LogManager.getLogger(CheckoutInformationPage.class);

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutInformationPage open() {
        log.info("Open Checkout Information page");
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutInformationPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//div[@class='checkout_info']")));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }

    @Step("Заполнение персональных данных клиента при оформлении заказа")
    public CheckoutOverviewPage checkoutFilling(String firstname, String lastname, String zipcode) {
        log.info("[Checkout Overview] Filling data in Checkout Information page - firstname: '{}', " +
                "lastname: '{}', zipcode: '{}'", firstname, lastname, zipcode);
        driver.findElement(FIRST_NAME).sendKeys(firstname);
        driver.findElement(LAST_NAME).sendKeys(lastname);
        driver.findElement(ZIP_CODE).sendKeys(zipcode);
        log.info("Confirmation of data filling in Checkout Information page");
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Отмена заполнения персональных данных при оформлении заказа")
    public ProductsPage cancelCheckout() {
        log.info("[Checkout Overview] Cancel filling in Checkout Information page");
        driver.findElement(CANCEL_BUTTON).click();
        return new ProductsPage(driver);
    }

    public void assertCancelCheckout() {
        log.info("[Checkout Overview] Checking for Displayed Cancel button");
        assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
    }

    public void assertErrorMessagePostalCode() {
        log.info("[Checkout Overview] Comparing actual error text to expected for Postal Code field");
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: Postal Code is required",
                "Текст ошибки отличается от фактического");
    }

    public void assertErrorMessageLastName() {
        log.info("[Checkout Overview] Comparing actual error text to expected for Last Name field");
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: Last Name is required",
                "Текст ошибки отличается от фактического");
    }

    public void assertErrorMessageFirstName() {
        log.info("[Checkout Overview] Comparing actual error text to expected for First Name field");
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: First Name is required",
                "Текст ошибки отличается от фактического");
    }
}
