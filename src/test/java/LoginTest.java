import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void checkSuccessTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("error_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title ")).getText();
        Assert.assertEquals(title, "Products", "Логин не выполнен");
    }

    @Test
        public void checkLoginWithEmptyUsername(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorText = driver.findElement(By.cssSelector(".error-message-container")).getText();
        Assert.assertEquals(errorText, "Epic sadface: Username is required",
                "Текст ошибки отсутствует");
        }

    @Test
    public void checkLoginWithEmptyPassword(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("error_user");
        driver.findElement(By.id("login-button")).click();
        String errorText = driver.findElement(By.cssSelector(".error-message-container")).getText();
        Assert.assertEquals(errorText, "Epic sadface: Password is required",
                "Текст ошибки отсутствует");
    }

    @Test
    public void checkInvalideLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button")).click();
        String errorText = driver.findElement(By.cssSelector(".error-message-container")).getText();
        Assert.assertEquals(errorText, "Epic sadface: Username is required",
                "Текст ошибки отсутствует");
    }
}
