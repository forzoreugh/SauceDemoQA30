package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By ZIP_CODE = By.id("postal-code");
    private static final By FIRST_NAME_ERROR_MESSAGE =
            By.xpath("//*[text()='Error: First Name is required']");
    private static final By LAST_NAME_ERROR_MESSAGE =
            By.xpath("//*[text()='Error: Last Name is required']");
    private static final By ZIP_CODE_ERROR_MESSAGE =
            By.xpath("//*[text()='Error: Postal Code is required']");
    private static final By CONTINUE_BUTTON = By.cssSelector(".submit-button.btn.btn_primary.cart_button.btn_action");
    private static final By CANCEL_BUTTON = By.cssSelector(".btn.btn_secondary.back.btn_medium.cart_cancel_link");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutFilling(String firstname, String lastname, String zipcode){
        driver.findElement(FIRST_NAME).sendKeys(firstname);
        driver.findElement(LAST_NAME).sendKeys(lastname);
        driver.findElement(ZIP_CODE).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void cancelCheckout(){
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void firstnameErrorMessage(){
        driver.findElement(FIRST_NAME_ERROR_MESSAGE).isDisplayed();
    }

    public void lastnameErrorMessage(){
        driver.findElement(LAST_NAME_ERROR_MESSAGE).isDisplayed();
    }

    public void zipcodeErrorMessage(){
        driver.findElement(ZIP_CODE_ERROR_MESSAGE).isDisplayed();
    }
}
