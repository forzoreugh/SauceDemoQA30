package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkSuccessTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(cartPage.getTitle(), "Products",
                "Тестовый текст");
    }

    @Test
        public void checkLoginWithEmptyUsername(){
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Тестовый текст");
        }

    @Test
    public void checkLoginWithEmptyPassword(){
        loginPage.open();
        loginPage.login("standart-user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
                "Тестовый текст");
    }

    @Test
    public void checkInvalideLogin(){
        loginPage.open();
        loginPage.login("", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Тестовый текст");
    }
}
