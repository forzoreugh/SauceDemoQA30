package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-message-container");
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login Page")
    public LoginPage open() {
        log.info("Open Login page");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//div[@class='login_wrapper-inner']")));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("[Login page] Page isn't opened");
        }
        return this;
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем: {password}")
    public ProductsPage login(String user, String password) {
        log.info("[Login page] Authorization with data - user: '{}', password: '{}'", user, password);
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void assertErrorMessageUsername() {
        log.info("[Login page] Comparing actual error text to expected for Username field");
        assertEquals(getErrorMessage(), "Epic sadface: Username is required",
                "Тестовый текст");
    }

    public void assertErrorMessagePassword() {
        log.info("[Login page] Comparing actual error text to expected for Password field");
        assertEquals(getErrorMessage(), "Epic sadface: Password is required",
                "Тестовый текст");
    }
}
