package pl.automaty.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.DeleteAccountPage;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;


public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    // Test Case 2
    @Test
    public void CorrectLoginTest() {

        // Create needed instances
        HomePage homePage = new HomePage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        // Accept cookies, navigate to the sign-in page and log in with the provided credentials
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");

        // Verify that the logged-in user text contains "Logged in as"
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Delete account
        homePage.deleteAccount();

        // Verify that confirmation message contains "ACCOUNT DELETED!".
        Assert.assertTrue(deleteAccountPage.getAccountDeletedText().contains("ACCOUNT DELETED!"));

        // Click the continue button
        deleteAccountPage.clickContinue();

    }

    // Test Case 3
    @Test
    public void IncorrectLoginTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Accept cookies, navigate to the sign-in page and log in with the incorrect credentials
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");

        // Verify that the error message displayed
        Assert.assertEquals(loginPage.getIncorrectLoginText(), "Your email or password is incorrect!");

    }

    // Test Case 4
    @Test
    public void LogoutUserTest() {
        // Create instances
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Accept cookies, navigate to the sign-in page
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // Verify that "Login to your account" is visible
        Assert.assertEquals(loginPage.getLoginText(), "Login to your account");

        // Log in with the correct credentials
        loginPage.loginToAccount("existUser1312311@tests.com", "Test123");

        // Verify that the logged-in user text contains "Logged in as"
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Logout from account
        homePage.logoutAccount();

    }
}
