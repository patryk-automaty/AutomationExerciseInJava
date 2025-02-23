package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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
        ExtentTest test = extentReports.createTest("Verify All Products and product detail page");

        // Accept cookies, navigate to the product page
        homePage.consentCookies()
                .productNavBar();
        test.log(Status.PASS, "Accept cookies, navigate to the product page");

        // Verify that products list is visible
        Assert.assertTrue(productsPage.isProductListVisible());
        test.log(Status.PASS, "Verify that products list is visible");

        productsPage.clickItem(0);
        test.log(Status.PASS, "Click the first item on the list");

        // Verify that details is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(productsPage.getProductName().isDisplayed());
        Assert.assertTrue(productsPage.getProductHeader().isDisplayed());
        Assert.assertTrue(productsPage.getProductPrice().isDisplayed());
        Assert.assertTrue(productsPage.getProductAvailability().isDisplayed());
        Assert.assertTrue(productsPage.getProductCondition().isDisplayed());
        Assert.assertTrue(productsPage.getProductBrand().isDisplayed());
        test.log(Status.PASS, "Verify that details is visible: product name, category, price, availability, condition, brand");
    }

    // Test Case 9
    @Test
    public void SearchProductTest() {
        String searchProductName = "dress";
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ExtentTest test = extentReports.createTest("Search Product");

        homePage.consentCookies()
                .productNavBar();
        test.log(Status.PASS, "Accept cookies, navigate to the product page");

        // Enter product name in search input and click search button
        productsPage.searchProduct(searchProductName);
        test.log(Status.PASS, "Enter product name in search input and click search button");

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
        test.log(Status.PASS, "Iterate through all product titles and check if they contain the keyword");
        }

    // TC 18
    @Test
    public void viewCategoryProductsTest() {
        //Create instances
        HomePage homePage = new HomePage(driver);
        ExtentTest test = extentReports.createTest("View Category Products");

        // Accept cookies
        homePage.consentCookies();
        test.log(Status.PASS, "Accept cookies");

        // Verify that the category section is visible
        Assert.assertEquals(homePage.getSideCategoryHeader(), "Category".toUpperCase());

        // Click on 'Women' category and choose Dress
        homePage.chooseWomenCategory();
        homePage.clickOnWomenCategory("Dress");
        test.log(Status.PASS, "Click on 'Women' category and choose Dress");

        // Verify that the category section is visible
        Assert.assertEquals(homePage.getCategoryHeader(), "Women - Dress Products".toUpperCase());

        //  On left sidebar, click on any sub-category link of 'Men' category
        homePage.chooseMenCategory();
        homePage.clickOnMenCategory("Jeans");
        test.log(Status.PASS, "Click on any sub-category link of 'Men' category on left sidebar");
    }
}

