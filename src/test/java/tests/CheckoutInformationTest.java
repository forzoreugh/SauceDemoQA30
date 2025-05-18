package tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutInformationTest extends BaseTest {

    @Test(dataProvider = "Позитивные тесты для заполнения данных при оформлении заказа",
            testName = "Валидное заполнение данных оформления заказа", retryAnalyzer = Retry.class,
            priority = 1, groups = {"smoke"})
    public void valideFilling(String firstname, String lastname, String zipcode) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling(firstname, lastname, zipcode);
        assertTrue(driver.findElement(By.xpath("//*[text()='Checkout: Overview']")).isDisplayed());
    }

    @Test(testName = "Отмена заполнения данных оформления заказа", priority = 5, groups = {"smoke"})
    public void cancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.cancelCheckout();
        assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
    }

    @Test(testName = "Невалидное заполнение имени при оформлении заказа", priority = 2, groups = {"regression"})
    public void invalideFillingFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.continueCheckout();
        checkoutInformationPage.checkoutFilling("", "Svidzinski", "17");
        String firstnameErrorMessage = checkoutInformationPage.errorMessage();
        assertEquals(firstnameErrorMessage, "Error: First Name is required");
    }

    @Test(testName = "Невалидное заполнение фамилии при оформлении заказа", priority = 3, groups = {"regression"})
    public void invalideFillingLastName() {
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

    @Test(testName = "Невалидное заполнение ZIP кода при оформлении заказа", priority = 4, groups = {"regression"})
    public void invalideFillingZipcode() {
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

    @DataProvider(name = "Позитивные тесты для заполнения данных при оформлении заказа")
    public Object[][] valideCheckoutInformationData() {
        return new Object[][]{
                {"Artem", "Svidzinski", "17"},
                {"Артем", "Свидинский", "17"},
                {"Artem-Maxim", "Svidzinski", "1"},
                {"Артем-Максим", "Свидинский", "2"},
                {"Ирина", "Проверкина-Матеркина", "222"},
                {"Irina", "Proverkina-Materkina", "222"}
        };
    }
}
