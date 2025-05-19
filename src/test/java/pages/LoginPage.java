package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-message-container");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login Page")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем: {password}")
    public void login(String user, String password) {
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
