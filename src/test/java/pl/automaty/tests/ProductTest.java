package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.ProductsPage;

public class ProductTest extends BaseTest {

    // Test Case 8
    @Test
    public void ProductPageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .productNavBar();
        ProductsPage productsPage = new ProductsPage(driver);
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.checkProductList());
        // Verify that products list is visible
        Assert.assertTrue(productsPage.isProductListVisible());
        productsPage.clickFirstItem();
        // Verify that details is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(productsPage.checkProductName());
        Assert.assertTrue(productsPage.checkProductCategory());
        Assert.assertTrue(productsPage.checkProductPrice());
        Assert.assertTrue(productsPage.checkProductAvailability());
        Assert.assertTrue(productsPage.checkProductCondition());
        Assert.assertTrue(productsPage.checkProductBrand());

    }
}
