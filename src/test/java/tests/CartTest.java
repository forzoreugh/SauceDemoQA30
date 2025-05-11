package tests;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {

        @Test
        public void addItemInBasketTest() {
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");
            cartPage.addItemToCart();
            cartPage.openCart();
        }
    }
