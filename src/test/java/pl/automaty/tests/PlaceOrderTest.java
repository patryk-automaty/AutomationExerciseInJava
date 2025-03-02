package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.PaymentData;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;
import pl.automaty.utils.TestDataGenerator;

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
        ExtentTest test = extentReports.createTest("Place Order: Register while Checkout");
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

        try {
            // Add products to cart and navigate to cart page
            homePage.consentCookies()
                    .addProductToCart(0)
                    .continueShopping()
                    .addProductToCart(1)
                    .viewCart();
            test.log(Status.PASS, "Added products to cart and navigate to cart page");

            // Click 'Proceed To Checkout' button
            cartPage.proceedToCheckout()
                    .registerOrLogin();
            test.log(Status.PASS, "Clicked 'Proceed To Checkout' button");

            // Generate and save test data
            SignUpData signUpData = TestDataGenerator.generateTestData();
            TestDataGenerator.saveTestData(signUpData);

            // Sign up new user
            loginPage.SignUpUser(signUpData.getName(), signUpData.getEmail());
            test.log(Status.PASS, "Signed up new user");

            // Verify that 'ENTER ACCOUNT INFORMATION' is visible
            Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");
            test.log(Status.PASS, "Verified that 'ENTER ACCOUNT INFORMATION' is visible");
            // Load test data from JSON
            SignUpData loadedData = TestDataGenerator.loadTestData();

            // Fill in account information using loaded test data
            signUpPage.EnterAccountInformation(loadedData);
            test.log(Status.PASS, "Entered account information");

            // Fill in address information
            signUpPage.EnterAddressInformation(loadedData);
            test.log(Status.PASS, "Entered address information");

            // Fill account information
            signUpPage.EnterAccountInformation(signUpData);
            test.log(Status.PASS, "Filled account information");

            // Fill address information
            signUpPage.EnterAddressInformation(signUpData);
            test.log(Status.PASS, "Filled address information");

            // Verify that 'ACCOUNT CREATED!' is visible
            Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");
            test.log(Status.PASS, "Verified that 'ACCOUNT CREATED!' is visible");

            // Click 'Continue' button
            accountCreatedPage.clickContinue();
            test.log(Status.PASS, "Clicked 'Continue' button");

            // Verify that 'Logged in as username' is visible
            Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));
            test.log(Status.PASS, "Verified that 'Logged in as username' is visible");

            // Navigate to cart page
            homePage.viewCart();
            test.log(Status.PASS, "Navigated to cart page");

            // Click 'Proceed To Checkout' button
            cartPage.proceedToCheckout();
            test.log(Status.PASS, "Clicked 'Proceed To Checkout' button");

            // Check that the delivery address is visible
            Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());
            test.log(Status.PASS, "Checked that the delivery address is visible");

            //Click Place Order button
            checkoutPage.clickPlaceOrder();
            test.log(Status.PASS, "Clicked Place Order button");

            // Fill Payment data and confirm order
            paymentData.setNameOnCard("Pat Kat")
                    .setCardNumber("123123532")
                    .setCvcCode("123")
                    .setExpirationMonth("05")
                    .setExpirationYear("2028");
            paymentPage.enterPaymentInformation(paymentData);
            test.log(Status.PASS, "Filled Payment data and confirm order");

            // Verify success message
            Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");
            test.log(Status.PASS, "Verified success message");

            // Click 'Delete Account' button
            homePage.deleteAccount();
            test.log(Status.PASS, "Clicked 'Delete Account' button");

            // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
            Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage());
            throw e;
        }
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
        ExtentTest test = extentReports.createTest("Place Order: Register before Checkout");
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

        // Accept cookies and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Accept cookies and navigate to login page");
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
        test.log(Status.PASS, "Fill account information");

        // Fill address information
        signUpPage.EnterAddressInformation(signUpData);
        test.log(Status.PASS, "Fill address information");

        // Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        // Click 'Continue' button
        accountCreatedPage.clickContinue();
        test.log(Status.PASS, "Click 'Continue' button");

        // Add products to cart, navigate to cart page and click proceed to checkout button
        homePage.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .viewCart()
                .proceedToCheckout();
        test.log(Status.PASS, "Add products to cart, navigate to cart page and click proceed to checkout button");

        // Check that the delivery address is visible
        Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());

        // Add order message and click place order button
        String message = "Test message";
        checkoutPage.addOrderMessage(message)
                    .clickPlaceOrder();
        test.log(Status.PASS, "Add order message and click place order button");

        // Fill Payment data and confirm order
        paymentData.setNameOnCard("Pat Kat")
                    .setCardNumber("123123532")
                    .setCvcCode("123")
                    .setExpirationMonth("05")
                    .setExpirationYear("2028");
        paymentPage.enterPaymentInformation(paymentData);
        test.log(Status.PASS, "Fill Payment data and confirm order");

        // Verify success message
        Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");

        // Click 'Delete Account' button
        homePage.deleteAccount();
        test.log(Status.PASS, "Click 'Delete Account' button");

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");

    }

    // TC 17
    @Test
    public void loginBeforeCheckoutTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentData paymentData = new PaymentData();
        PaymentPage paymentPage = new PaymentPage(driver);
        ExtentTest test = extentReports.createTest("Remove Products From Cart");

        // Accept cookies and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("exis1tUser1312311@tests.com", "Test123");
        test.log(Status.PASS, "Accept cookies and navigate to login page");

        // Verify that the logged-in user text contains "Logged in as"
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Add products to cart, navigate to cart page and click proceedToCheckout page
        homePage.addProductToCart(0)
                .continueShopping()
                .addProductToCart(1)
                .continueShopping()
                .viewCart()
                .proceedToCheckout();
        test.log(Status.PASS, "Add products to cart, navigate to cart page and click proceedToCheckout page");

        // Check that the delivery address is visible
        Assert.assertTrue(checkoutPage.getDeliveryAddress().isDisplayed());

        // Add order message and click place order button
        String message = "Test message";
        checkoutPage.addOrderMessage(message)
                .clickPlaceOrder();
        test.log(Status.PASS, "Add order message and click place order button");

        // Fill Payment data and confirm order
        paymentData.setNameOnCard("Pat Kat")
                .setCardNumber("123123532")
                .setCvcCode("123")
                .setExpirationMonth("05")
                .setExpirationYear("2028");
        paymentPage.enterPaymentInformation(paymentData);
        test.log(Status.PASS, "Fill Payment data and confirm order");

        // Verify success message
        Assert.assertEquals(paymentPage.getSuccessOrderMessage().getText(), "Congratulations! Your order has been confirmed!");

        // Click 'Delete Account' button
        homePage.deleteAccount();
        test.log(Status.PASS, "Click 'Delete Account' button");

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");

    }
}
