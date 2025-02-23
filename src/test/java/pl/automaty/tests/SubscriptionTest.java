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

        // Create instances and define subscription email
        String subscriptionEmail = "testEmail@email.com";
        HomePage homePage = new HomePage(driver);
        ExtentTest test = extentReports.createTest("Verify Subscription in home page");

        // Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.subscriptionHeaderText(), "Subscription");

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);
        test.log(Status.PASS, "Enter email address in input and click arrow button");

        // Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.successSubMessageText(), "You have been successfully subscribed!'");
        test.log(Status.PASS, "Verify success message 'You have been successfully subscribed!' is visible");

    }

    // TC 11
    @Test
    public void SendSubscriptionTestInCartPage() {

        // Create instances and define subscription email
        String subscriptionEmail = "testEmail@email.com";
        HomePage homePage = new HomePage(driver);
        ExtentTest test = extentReports.createTest("Verify Subscription in Cart page");
        // Navigate to cart page
        homePage.viewCart();
        test.log(Status.PASS, "Navigate to cart page");
        // Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.subscriptionHeaderText(), "Subscription");

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);
        test.log(Status.PASS, "Enter email address in input and click arrow button");

        // Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.successSubMessageText(), "You have been successfully subscribed!'");
    }



}
