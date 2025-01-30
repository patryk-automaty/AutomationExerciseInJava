package pl.automaty.tests;

import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.ProductsPage;

public class ProductTest extends BaseTest {

    @Test
    public void Product1Test() {
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .productNavBar();
        ProductsPage productsPage = new ProductsPage(driver);
        System.out.println(productsPage.getProductList());
    }

    @Test
    public void ClickTest() {
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .productNavBar();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickFirstItem();
    }

    // productpage ogarniety, jutro do zrobienia klaas testowa czyli scenairusze testower
}
