package pages;

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
public class CheckoutOverviewPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CheckoutOverviewPage.class);

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutOverviewPage open() {
        log.info("Open Checkout Overview page");
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Checkout: Overview']")));
            log.info("[Checkout Overview] Comparisons of section name correctness Checkout Overview ");
            assertEquals(driver.findElement(By.xpath("//span[text()='Checkout: Overview']")).getText(),
                    "Checkout: Overview", "Неверное наименование раздела");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }
}
