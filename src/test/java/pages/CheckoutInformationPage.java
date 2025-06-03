package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutInformationPage extends BasePage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By ZIP_CODE = By.id("postal-code");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By CONTINUE_BUTTON = By.cssSelector(".submit-button.btn.btn_primary.cart_button.btn_action");
    private static final By CANCEL_BUTTON = By.cssSelector(".btn.btn_secondary.back.btn_medium.cart_cancel_link");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutInformationPage open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutInformationPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='checkout_info']")));
        return this;
    }

    @Step("Заполнение персональных данных клиента при оформлении заказа")
    public void checkoutFilling(String firstname, String lastname, String zipcode) {
        driver.findElement(FIRST_NAME).sendKeys(firstname);
        driver.findElement(LAST_NAME).sendKeys(lastname);
        driver.findElement(ZIP_CODE).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Отмена заполнения персональных данных при оформлении заказа")
    public ProductsPage cancelCheckout() {
        driver.findElement(CANCEL_BUTTON).click();
        return new ProductsPage(driver);
    }

    public void assertCancelCheckout() {
        assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
    }

    public void assertErrorMessagePostalCode() {
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: Postal Code is required",
                "Текст ошибки отличается от фактического");
    }

    public void assertErrorMessageLastName() {
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: Last Name is required",
                "Текст ошибки отличается от фактического");
    }

    public void assertErrorMessageFirstName() {
        assertEquals(driver.findElement(ERROR_MESSAGE).getText(), "Error: First Name is required",
                "Текст ошибки отличается от фактического");
    }
}
