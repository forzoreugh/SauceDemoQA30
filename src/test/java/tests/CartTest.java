package tests;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {

        @Test
        public void addItemInBasketTest() {
            loginPage.open();
            loginPage.login("error_user", "secret_sauce");
            productsPage.getShop();
            productsPage.click();






        }
    }
