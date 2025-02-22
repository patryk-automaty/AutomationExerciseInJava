package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.PaymentData;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;

public class PlaceOrderTest extends BaseTest {

    // TC 14
    @Test
    public void registerWhileCheckoutTest() {
        // Create instances
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        PaymentData paymentData = new PaymentData();
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        // Add products to cart and navigate to cart page
        homePage.consentCookies()
                .addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCart();

        // Click 'Proceed To Checkout' button
        cartPage.proceedToCheckout()
                .registerOrLogin();

        // Sign up new user
        loginPage.SignUpUser("janusz", "ja2nus111z123@mail123123.com");

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
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");

        // Fill account information
        signUpPage.EnterAccountInformation(signUpData);

        // Fill address information
        signUpPage.EnterAddressInformation(signUpData);

        // Verify that 'ACCOUNT CREATED!' is visible
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

        //Click Place Order button
        checkoutPage.clickPlaceOrder();

        // Fill Payment data and confirm order
        paymentData.setNameOnCard("Pat Kat")
                    .setCardNumber("123123532")
                    .setCvcCode("123")
                    .setExpirationMonth("05")
                    .setExpirationYear("2028");
        paymentPage.enterPaymentInformation(paymentData);

        // Verify success message
        Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");

        // Click 'Delete Account' button
        homePage.deleteAccount();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    // TC 15
    @Test
    public void registerBeforeCheckoutTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        PaymentData paymentData = new PaymentData();
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        // Accept cookies and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // Sign up new user
        loginPage.SignUpUser("janusz", "ja112nusmai@l123123.com");

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
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");

        // Fill account information
        signUpPage.EnterAccountInformation(signUpData);

        // Fill address information
        signUpPage.EnterAddressInformation(signUpData);

        // Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        // Click 'Continue' button
        accountCreatedPage.clickContinue();

        // Add products to cart, navigate to cart page and click proceed to checkout button
        homePage.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCart()
                .proceedToCheckout();

        // Check that the delivery address is visible
        Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());

        // Add order message and click place order button
        String message = "Test message";
        checkoutPage.addOrderMessage(message)
                    .clickPlaceOrder();

        // Fill Payment data and confirm order
        paymentData.setNameOnCard("Pat Kat")
                    .setCardNumber("123123532")
                    .setCvcCode("123")
                    .setExpirationMonth("05")
                    .setExpirationYear("2028");
        paymentPage.enterPaymentInformation(paymentData);

        // Verify success message
        Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");

        // Click 'Delete Account' button
        homePage.deleteAccount();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");

    }

    // TC 17
    @Test
    public void loginBeforeCheckoutTest() {

        //Create instances
        HomePage homePage = new HomePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentData paymentData = new PaymentData();
        PaymentPage paymentPage = new PaymentPage(driver);

        // Accept cookies and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("exis1tUser1312311@tests.com", "Test123");

        // Verify that the logged-in user text contains "Logged in as"
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Add products to cart, navigate to cart page and click proceedToCheckout page
        homePage.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .continueShopping()
                .viewCart()
                .proceedToCheckout();

        // Check that the delivery address is visible
        Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());

        // Add order message and click place order button
        String message = "Test message";
        checkoutPage.addOrderMessage(message)
                .clickPlaceOrder();

        // Fill Payment data and confirm order
        paymentData.setNameOnCard("Pat Kat")
                .setCardNumber("123123532")
                .setCvcCode("123")
                .setExpirationMonth("05")
                .setExpirationYear("2028");
        paymentPage.enterPaymentInformation(paymentData);

        // Verify success message
        Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");

        // Click 'Delete Account' button
        homePage.deleteAccount();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");

    }
}
