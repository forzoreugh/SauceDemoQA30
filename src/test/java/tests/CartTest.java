package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;



public class CartTest extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

        @Test
        public void addItemInBasketTest() {
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");
            productsPage.addItemToCart("Sauce Labs Backpack");
            productsPage.openCart();
        }
    }
