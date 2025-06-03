package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutInformationTest extends BaseTest {

    @Test(dataProvider = "Позитивные тесты для заполнения данных при оформлении заказа",
            testName = "Валидное заполнение данных оформления заказа", retryAnalyzer = Retry.class,
            priority = 1, groups = {"smoke"})
    @Epic("Оформление заказа")
    @Feature("Заполнение персональных данных")
    @Story("Корректное заполнение")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка валидного заполнения персональных данных при оформлени заказа")
    public void valideFilling(String firstname, String lastname, String zipcode) {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .addItemToCart("Sauce Labs Backpack")
                .openCart()
                .continueCheckout()
                .checkoutFilling(firstname, lastname, zipcode);
        checkoutOverviewPage.isPageOpened();
    }

    @Test(testName = "Отмена заполнения данных оформления заказа", priority = 5, groups = {"smoke"})
    @Epic("Оформление заказа")
    @Feature("Отсутствие заполнения персональных данных")
    @Story("Отмена заполнения персональных данных")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка отмены заполнения пресональных данных при оформлении заказа")
    public void cancelCheckout() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addItemToCart("Sauce Labs Backpack")
                .openCart()
                .continueCheckout();
        checkoutInformationPage.cancelCheckout();
        checkoutInformationPage.assertCancelCheckout();
    }

    @Test(testName = "Невалидное заполнение имени при оформлении заказа", priority = 2, groups = {"regression"})
    @Epic("Оформление заказа")
    @Feature("Заполнение персональных данных")
    @Story("Некорректное заполнение поля First Name")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка невалидного заполнения поля First Name при оформлении заказа")
    public void invalideFillingFirstName() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addItemToCart("Sauce Labs Backpack")
                .openCart()
                .continueCheckout();
        checkoutInformationPage.checkoutFilling("", "Svidzinski", "17");
        checkoutInformationPage.assertErrorMessageFirstName();
    }

    @Test(testName = "Невалидное заполнение фамилии при оформлении заказа", priority = 3, groups = {"regression"})
    @Epic("Оформление заказа")
    @Feature("Заполнение персональных данных")
    @Story("Некорректное заполнение поля Last Name")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка невалидного заполнения поля Last Name при оформлении заказа")
    public void invalideFillingLastName() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .addItemToCart("Sauce Labs Backpack")
                .openCart()
                .isPageOpened()
                .continueCheckout();
        checkoutInformationPage.isPageOpened()
                .checkoutFilling("Artem", "", "17");
        checkoutInformationPage.assertErrorMessageLastName();
    }

    @Test(testName = "Невалидное заполнение ZIP кода при оформлении заказа", priority = 4, groups = {"regression"})
    @Epic("Оформление заказа")
    @Feature("Заполнение персональных данных")
    @Story("Некорректное заполнение поля Zip/Postal Code")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Свидинский А.А.")
    @Description("Проверка невалидного заполнения поля Zip/Postal Code при оформлении заказа")
    public void invalideFillingZipcode() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .addItemToCart("Sauce Labs Backpack")
                .openCart()
                .isPageOpened()
                .continueCheckout();
        checkoutInformationPage.isPageOpened()
                .checkoutFilling("Artem", "Svidzinski", "");
        checkoutInformationPage.assertErrorMessagePostalCode();
    }

    @DataProvider(name = "Позитивные тесты для заполнения данных при оформлении заказа")
    public Object[][] valideCheckoutInformationData() {
        return new Object[][]{
                {"Artem", "Svidzinski", "17"},
                {"Марина", "Проверкина", "222"},
        };
    }
}
