package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutOverviewPage open() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Checkout: Overview']")));
        assertEquals(driver.findElement(By.xpath("//span[text()='Checkout: Overview']")).getText(),
                "Checkout: Overview", "Неверное наименование раздела");
        return this;
    }
}
