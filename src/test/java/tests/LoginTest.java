package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "Валидная авторизация", retryAnalyzer = Retry.class,
            groups = {"smoke"}, priority = 1)
    @Epic("Страница Login Page")
    @Feature("Авторизация")
    @Story("Успешная авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Свидинский А.А.")
    @Description("Проверка успешной авторизации в приложение")
    public void checkSuccessTest() {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .assertOpenPage();
    }

    @Test(testName = "Авторизация с невалидным Username", groups = {"regression"}, priority = 3)
    @Epic("Страница Login Page")
    @Feature("Авторизация")
    @Story("Авторизация с невалидным значением в поле Username")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Свидинский А.А.")
    @Description("Проверка отсутствия успешной авторизации при заполнении некорректного значения в поле Username")
    public void checkLoginWithEmptyUsername() {
        loginPage.open()
                .isPageOpened()
                .login("", password);
        loginPage.assertErrorMessageUsername();
    }

    @Test(testName = "Авторизация с невалидным Password", groups = {"regression"}, priority = 4)
    @Epic("Страница Login Page")
    @Feature("Авторизация")
    @Story("Авторизация с невалидным значением в поле Password")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Свидинский А.А.")
    @Description("Проверка отсутствия успешной авторизации при заполнении некорректного значения в поле Password")
    public void checkLoginWithEmptyPassword() {
        loginPage.open()
                .isPageOpened()
                .login(user, "");
        loginPage.assertErrorMessagePassword();
    }

    @Test(testName = "Невалидная авторизация", groups = {"regression"}, priority = 2)
    @Epic("Страница Login Page")
    @Feature("Авторизация")
    @Story("Авторизация с отсутствием заполнения авторизационных данных")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Свидинский А.А.")
    @Description("Проверка отсутствия успешной авторизации при отсутствии заполнения полей Username/Password")
    public void checkInvalideLogin() {
        loginPage.open()
                .isPageOpened()
                .login("", "");
        loginPage.assertErrorMessageUsername();
    }
}
