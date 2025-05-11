package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class CheckoutInformationTest extends BaseTest {

    @Test
    public void valideFilling(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart();
        cartPage.openCart();
        cartPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "Svidzinski", "17");
        assertTrue(driver.findElement(By.xpath("//*[text()='Checkout: Overview']")).isDisplayed());
    }

    @Test
    public void cancelCheckout(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart();
        cartPage.openCart();
        cartPage.continueCheckout();
        checkoutInformationPage.cancelCheckout();
        assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
    }

    @Test
    public void invalideFillingFirstName(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart();
        cartPage.openCart();
        cartPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("", "Svidzinski", "17");
        checkoutInformationPage.firstnameErrorMessage();
    }

    @Test
    public void invalideFillingLastName(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart();
        cartPage.openCart();
        cartPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "", "17");
        checkoutInformationPage.lastnameErrorMessage();
    }

    @Test
    public void invalideFillingZipcode(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart();
        cartPage.openCart();
        cartPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "Svidzinski", "");
        checkoutInformationPage.zipcodeErrorMessage();
    }
}
