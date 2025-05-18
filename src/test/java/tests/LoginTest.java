package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", testName = "Валидная авторизация", retryAnalyzer = Retry.class,
            groups = {"smoke"}, priority = 1)
    public void checkSuccessTest(String username, String password) {
        loginPage.open();
        loginPage.login(username, password);
        assertEquals(productsPage.getTitle(), "Products",
                "Тестовый текст");
    }

    @Test(testName = "Авторизация с невалидным Username", groups = {"regression"}, priority = 3)
    public void checkLoginWithEmptyUsername() {
        loginPage.open();
        loginPage.login("24", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Тестовый текст");
    }

    @Test(testName = "Авторизация с невалидным Password", groups = {"regression"}, priority = 4)
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standart-user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
                "Тестовый текст");
    }

    @Test(testName = "Невалидная авторизация", groups = {"regression"}, priority = 2)
    public void checkInvalideLogin() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Тестовый текст");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
        };
    }
}
