package tests;

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
            login();
            driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt"));
            driver.findElement(By.partialLinkText("Sauce Labs"));
            driver.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
            driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
            driver.findElement(By.xpath("//div[contains(@data-test,'inventory')]"));
            driver.findElement(By.xpath("//div[contains(@data-test,'inventory')]" +
                    "//ancestor::a[@id='item_1_title_link']"));
            driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                    "//descendant::div[@data-test='inventory-item-name']"));
            driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                    "//following::div[@class='inventory_item_desc']"));
            driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                    "/parent::div"));
            driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                    "/preceding::div"));
            driver.findElement(By.xpath("//a[@id='item_1_title_link' " +
                    "and @data-test='item-1-title-link']"));
            driver.findElement(By.cssSelector(".inventory_item_label"));
            driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory "));
            driver.findElement(By.cssSelector(".inventory_item_label .inventory_item_desc"));
            driver.findElement(By.cssSelector("#item_1_img_link"));
            driver.findElement(By.cssSelector("footer"));
            driver.findElement(By.cssSelector("footer.footer"));
            driver.findElement(By.cssSelector("[data-test=inventory-item]"));
            driver.findElement(By.cssSelector("[data-test~=inventory-item]"));
            driver.findElement(By.cssSelector("[data-test|=inventory-item]"));
            driver.findElement(By.cssSelector("[data-test^=inventory]"));
            driver.findElement(By.cssSelector("[data-test*=ite]"));

        }
}
