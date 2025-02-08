package pl.automaty.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.automaty.pages.CartPage;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.ProductsPage;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void viewCardTest() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        // Accept cookies and navigate to the product section
        homePage.consentCookies()
                .productNavBar();
        // Add the first and the second products to the cart.
        // View the cart.
        productsPage.clickAddToCart(0)
                    .continueShopping()
                    .clickAddToCart(1)
                    .viewCart();
        // Retrieve lists of product details
        List<WebElement> productsName = cartPage.getProductsName();
        List<WebElement> productsCategory = cartPage.getProductsCategory();
        List<WebElement> productsQuantity = cartPage.getProductsQuantity();
        List<WebElement> productsPrice = cartPage.getProductsPrice();
        List<WebElement> productsTotalPrice = cartPage.getProductsTotalPrice();
        SoftAssert softAssert = new SoftAssert();
        // Iterate over the lists to check the visibility of each product details
        for (int i = 0; i < productsName.size(); i++) {
            WebElement name = productsName.get(i);
            WebElement category = productsCategory.get(i);
            WebElement quantity = productsQuantity.get(i);
            WebElement price = productsPrice.get(i);
            WebElement totalPrice = productsTotalPrice.get(i);
            softAssert.assertTrue(name.isDisplayed(), "Product element is not visible: " + name.getText());
            softAssert.assertTrue(category.isDisplayed(), "Product element is not visible: " + category.getText());
            softAssert.assertTrue(quantity.isDisplayed(), "Product element is not visible: " + quantity.getText());
            softAssert.assertTrue(price.isDisplayed(), "Product element is not visible: " + price.getText());
            softAssert.assertTrue(totalPrice.isDisplayed(), "Product element is not visible: " + totalPrice.getText());
            System.out.println("product " + i + 1);
            System.out.println("Product Name: " + name.getText());
            System.out.println("Product Category: " + category.getText());
            System.out.println("Product quantity: " + quantity.getText());
            System.out.println("Product price: " + price.getText());
            System.out.println("Product total price: " + totalPrice.getText());

        }
        // Soft assertion - test execution will continue even if this assertion fails
        softAssert.assertAll();
    }

}
