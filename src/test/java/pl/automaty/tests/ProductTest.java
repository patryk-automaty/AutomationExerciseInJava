package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.ProductsPage;

import java.util.List;

public class ProductTest extends BaseTest {

    // Test Case 8
    @Test
    public void ProductPageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .productNavBar();
        ProductsPage productsPage = new ProductsPage(driver);
        // Verify user is navigated to ALL PRODUCTS page successfully

        // Verify that products list is visible
        Assert.assertTrue(productsPage.isProductListVisible());
        productsPage.clickItem(0);
        // Verify that details is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(productsPage.getProductName().isDisplayed());
        Assert.assertTrue(productsPage.getProductHeader().isDisplayed());
        Assert.assertTrue(productsPage.getProductPrice().isDisplayed());
        Assert.assertTrue(productsPage.getProductAvailability().isDisplayed());
        Assert.assertTrue(productsPage.getProductCondition().isDisplayed());
        Assert.assertTrue(productsPage.getProductBrand().isDisplayed());

    }

    // Test Case 9
    @Test
    public void SearchProductTest() {
        String searchProductName = "dress";
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .productNavBar();
        ProductsPage productsPage = new ProductsPage(driver);
        // Verify user is navigated to ALL PRODUCTS page successfully

        // Enter product name in search input and click search button
        productsPage.searchProduct(searchProductName);
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.getSearchedProductsHeader().isDisplayed());

        List<String> searchedProductTitles = productsPage.getProductListTexts();
        // Number of all searched products
        int totalProducts = searchedProductTitles.size();
        // Number of matched products with searchProductName
        int matchingProducts = 0;
        SoftAssert softAssert = new SoftAssert();
        // Iterate through all product titles and check if they contain the keyword
        System.out.println("Search results:");
        for (String title : searchedProductTitles) {
                String productTitle = title.toLowerCase();
                boolean containsWord = productTitle.contains(searchProductName);

                if(containsWord) {
                    matchingProducts++;
                    System.out.println("OK: " + productTitle);
                }
                else {
                    System.out.println("ERROR: " + productTitle);
                }
                // Soft assertion - test execution will continue even if this assertion fails
                softAssert.assertTrue(containsWord, "Product not include '" + searchProductName + "': "+ productTitle);
            }
        // Final assertion - If assertion find error, assertion will fail
        softAssert.assertAll();
        }


    }

