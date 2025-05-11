package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutInformationTest extends BaseTest {

    @Test
    public void valideFilling(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "Svidzinski", "17");
        assertTrue(driver.findElement(By.xpath("//*[text()='Checkout: Overview']")).isDisplayed());
    }

    @Test
    public void cancelCheckout(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.cancelCheckout();
        assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
    }

    @Test
    public void invalideFillingFirstName(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("", "Svidzinski", "17");
        String firstnameErrorMessage = checkoutInformationPage.errorMessage();
        assertEquals(firstnameErrorMessage, "Error: First Name is required");
    }

    @Test
    public void invalideFillingLastName(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "", "17");
        checkoutInformationPage.errorMessage();
        String lastnameErrorMessage = checkoutInformationPage.errorMessage();
        assertEquals(lastnameErrorMessage, "Error: Last Name is required");
    }

    @Test
    public void invalideFillingZipcode(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("Artem", "Svidzinski", "");
        checkoutInformationPage.errorMessage();
        String zipcodeErrorMessage = checkoutInformationPage.errorMessage();
        assertEquals(zipcodeErrorMessage, "Error: Postal Code is required");
    }
}
