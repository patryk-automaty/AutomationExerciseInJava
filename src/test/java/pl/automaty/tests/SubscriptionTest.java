package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;

public class SubscriptionTest extends BaseTest {

    // TC 10
    @Test
    public void SendSubscriptionTestInHomePage() {

        // Define expected values
        final String subscriptionEmail = "testEmail@email.com";
        final String expectedSubscriptionHeader = "SUBSCRIPTION";
        final String expectedSuccessMessage = "You have been successfully subscribed!";

        // Create instances for reporting and pages
        HomePage homePage = new HomePage(driver);
        ExtentTest test = extentReports.createTest("Verify Subscription in Home page");

        // Accept cookies
        homePage.consentCookies();

        // Verify text 'SUBSCRIPTION'
        String actualHeaderText = homePage.subscriptionHeaderText().getText();
        Assert.assertEquals(actualHeaderText, expectedSubscriptionHeader);
        test.log(Status.PASS, "Verified subscription header. Expected: '" + expectedSubscriptionHeader + ", Found: " + actualHeaderText);

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);
        test.log(Status.PASS, "Entered email address in input and clicked arrow button");

        // Verify success message 'You have been successfully subscribed!' is visible
        String actualSuccessMessage = homePage.successSubMessageText();
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
        test.log(Status.PASS, "Verified success message. Expected: '" + expectedSuccessMessage + ", Found: '" + actualSuccessMessage);

    }

    // TC 11
    @Test
    public void SendSubscriptionTestInCartPage() {

        // Define subscription email
        final String subscriptionEmail = "testEmail@email.com";
        final String expectedSubscriptionHeader = "SUBSCRIPTION";
        final String expectedSuccessMessage = "YOU HAVE BEEN SUCCESSFULLY SUBSCRIBED!";

        // Create instances for reporting and pages
        HomePage homePage = new HomePage(driver);
        ExtentTest test = extentReports.createTest("Verify Subscription in Cart page");

        // Accept Cookies and navigate to cart page
        homePage.consentCookies()
                .viewCart();
        test.log(Status.PASS, "Navigated to cart page");

        // Verify text 'SUBSCRIPTION'
        String actualHeaderText = homePage.subscriptionHeaderText().getText();
        Assert.assertEquals(actualHeaderText, expectedSubscriptionHeader);
        test.log(Status.PASS, "Verified subscription header. Expected: '" + expectedSubscriptionHeader + ", Found: " + actualHeaderText);

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);
        test.log(Status.PASS, "Entered email address in input and clicked arrow button");

        // Verify success message 'You have been successfully subscribed!' is visible
        String actualSuccessMessage = homePage.successSubMessageText().toUpperCase();
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
        test.log(Status.PASS, "Verified success message. Expected: '" + expectedSuccessMessage + ", Found: '" + actualSuccessMessage);
    }
}
