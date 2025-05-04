import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

        @Test
        public void checkLocator(){
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name"));
            driver.findElement(By.name("user-name"));
            driver.findElement(By.className("form_group"));
            driver.findElement(By.tagName("div"));
            driver.get("https://www.saucedemo.com/inventory.html");
            driver.findElement(By.linkText(""));
            driver.findElement(By.partialLinkText(""));
            driver.findElement(By.xpath(""));
            driver.findElement(By.cssSelector(""));
        }
}
