package pl.automaty.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;

import java.util.List;
import java.util.Random;

public class CartTest extends BaseTest {

    // Test Case 12
    @Test
    public void viewCardTest() {
        // Create needed instances
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Accept cookies and navigate to the product section
        homePage.consentCookies()
                .productNavBar();

        // Add the first and the second products to the cart and redirect to cart page
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

            // Check that each element is displayed using SoftAssert
            softAssert.assertTrue(name.isDisplayed(), "Product element is not visible: " + name.getText());
            softAssert.assertTrue(category.isDisplayed(), "Product element is not visible: " + category.getText());
            softAssert.assertTrue(quantity.isDisplayed(), "Product element is not visible: " + quantity.getText());
            softAssert.assertTrue(price.isDisplayed(), "Product element is not visible: " + price.getText());
            softAssert.assertTrue(totalPrice.isDisplayed(), "Product element is not visible: " + totalPrice.getText());

            // Log details for the product
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

    // Test Case 13
    @Test
    public void verifyQuantityTest() {
        // Create needed instances
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Accept cookies
        homePage.consentCookies();

        // Choose random product
        List<WebElement> productsList = homePage.getProductList();
        int sizeProductList = productsList.size();
        Random randomNumber = new Random();
        int randomProduct = randomNumber.nextInt(sizeProductList);
        homePage.viewProductList().get(randomProduct).click();

        // Increase quantity to 4
        int quantity = 4;
        productsPage.clearQuantityNumber()
                .inputQuantityNumber(quantity)
                .addToCard()
                .viewCart();

        // Verify that product is displayed in cart page with exact quantity
        String quantityOnCartPageString = cartPage.getProductsQuantity().get(0).getText();
        int quantityOnCartPageInt = Integer.parseInt(quantityOnCartPageString);
        Assert.assertEquals(quantityOnCartPageInt, quantity);
    }

    // TC 14
    @Test
    public void registerWhileCheckoutTest() {
        // Create instances
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Add products to cart and navigate to cart page
        homePage.consentCookies()
                .addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCart();

        // Click 'Proceed To Checkout' button
        cartPage.proceedToCheckout()
                .registerOrLogin();

        loginPage.SignUpUser("janusz", "janus11z123@mail123123.com");


        // Account information data
        SignUpData signUpData = new SignUpData();
        signUpData.setGender("Mr");
        signUpData.setName("Pat");
        signUpData.setPassword("Test123");
        signUpData.setBirthDay("11");
        signUpData.setBirthMonth("3");
        signUpData.setBirthYear("2000");
        signUpData.setNewsletter(Boolean.TRUE);
        signUpData.setOffer(Boolean.TRUE);

        // Address information data
        signUpData.setFirstName("Pat");
        signUpData.setLastName("Kat");
        signUpData.setCompany("Januszex");
        signUpData.setAddress1("Random Address");
        signUpData.setAddress2("Continue random address 3/15");
        signUpData.setCountry("Canada");
        signUpData.setState("Mazovia");
        signUpData.setCity("Warsaw");
        signUpData.setZipcode("00-000 Warsaw");
        signUpData.setMobileNumber("123123123");

        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        SignUpPage signUpPage = new SignUpPage(driver);
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");

        // Fill account information
        signUpPage.EnterAccountInformation(signUpData);

        // Fill address information
        signUpPage.EnterAddressInformation(signUpData);

        // Verify that 'ACCOUNT CREATED!' is visible
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        // Click 'Continue' button
        accountCreatedPage.clickContinue();

        // Verify that 'Logged in as username' is visible
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Navigate to cart page
        homePage.viewCart();

        // Click 'Proceed To Checkout' button
        cartPage.proceedToCheckout();

        // Check that the delivery address is visible
        Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());


        // Click 'Delete Account' button
        homePage.deleteAccount();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

}