package pl.automaty.tests;

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

        // Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.subscriptionHeaderText(), "Subscription");

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);

        // Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.successSubMessageText(), "You have been successfully subscribed!'");
    }

    // TC 11
    @Test
    public void SendSubscriptionTestInCartPage() {

        // Create instances and define subscription email
        String subscriptionEmail = "testEmail@email.com";
        HomePage homePage = new HomePage(driver);

        // Navigate to cart page
        homePage.viewCart();

        // Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.subscriptionHeaderText(), "Subscription");

        // Enter email address in input and click arrow button
        homePage.sendSubscriptionEmail(subscriptionEmail);

        // Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.successSubMessageText(), "You have been successfully subscribed!'");
    }



}
