package pl.automaty.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.automaty.model.PaymentData;
import pl.automaty.pages.CheckoutPage;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.PaymentPage;
import pl.automaty.pages.ProductsPage;

import java.time.Duration;
import java.util.List;

public class ProductTest extends BaseTest {

    // Test Case 8
    @Test
    public void ProductPageTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Accept cookies, navigate to the product page
        homePage.consentCookies()
                .productNavBar();

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
        ProductsPage productsPage = new ProductsPage(driver);
        homePage.consentCookies()
                .productNavBar();

        // Enter product name in search input and click search button
        productsPage.searchProduct(searchProductName);

        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.getSearchedProductsHeader().isDisplayed());

        // Get all product names to the list
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

    // TC 18
    @Test
    public void viewCategoryProductsTest() {
        //Create instances
        HomePage homePage = new HomePage(driver);


        // Accept cookies and navigate to login page
        homePage.consentCookies();

        // Verify that the category section is visible
        Assert.assertEquals(homePage.getSideCategoryHeader(), "Category".toUpperCase());

        // Click on 'Women' category and choose Dress
        homePage.chooseWomenCategory();
        homePage.clickOnWomenCategory("Dress");
        // Verify that the category section is visible
        Assert.assertEquals(homePage.getCategoryHeader(), "Women - Dress Products".toUpperCase());

        //  On left sidebar, click on any sub-category link of 'Men' category
        homePage.chooseMenCategory();
        homePage.clickOnMenCategory("Jeans");
    }

    @Test
    public void viewAndCartBrandProductsTest() {
        HomePage homePage = new HomePage(driver);
    }



}

