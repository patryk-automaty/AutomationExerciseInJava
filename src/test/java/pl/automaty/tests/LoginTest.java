package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.DeleteAccountPage;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;
import pl.automaty.utils.SeleniumHelper;


public class LoginTest extends BaseTest {


    // TC 2
    @Test
    public void CorrectLoginTest() {

        // Create instances for pages and reporting
        HomePage homePage = new HomePage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        ExtentTest test = extentReports.createTest("Login User with correct email and password");
        try {
            // Accept cookies, navigate to the sign-in page and log in with the provided credentials
            homePage.consentCookies()
                    .openSignInAndLoginPage()
                    .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");
            test.log(Status.PASS, "Accept cookies, navigate to the sign-in page and log in with the provided credentials");

            // Verify that the logged-in user text contains "Logged in as"
            Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

            // Delete account
            homePage.deleteAccount();
            test.log(Status.PASS, "Delete account");
            // Verify that confirmation message contains "ACCOUNT DELETED!".
            Assert.assertTrue(deleteAccountPage.getAccountDeletedText().contains("ACCOUNT DELETED!"));

            // Click the continue button
            deleteAccountPage.clickContinue();
            test.log(Status.PASS, "Click the continue button");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage());
            throw e;
        }
    }

    // TC 3
    @Test
    public void IncorrectLoginTest() {

        // Create instances for pages and reporting
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ExtentTest test = extentReports.createTest("Login User with incorrect email and password");
        try {
            // Accept cookies, navigate to the sign-in page and log in with the incorrect credentials
            homePage.consentCookies()
                    .openSignInAndLoginPage()
                    .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");
            test.log(Status.PASS, "Accept cookies, navigate to the sign-in page and log in with the incorrect credentials");
            // Verify that the error message displayed
            Assert.assertEquals(loginPage.getIncorrectLoginText(), "Your email or password is incorrect!");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage());
            throw e;
        }
    }

    // Test Case 4
    @Test
    public void LogoutUserTest() {

        // Create instances for pages and reporting
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ExtentTest test = extentReports.createTest("Logout User");
        try {
            // Accept cookies, navigate to the sign-in page
            homePage.consentCookies()
                    .openSignInAndLoginPage();
            test.log(Status.PASS, "Accept cookies, navigate to the sign-in page", SeleniumHelper.getScreenshot(driver));

            // Verify that "Login to your account" is visible
            Assert.assertEquals(loginPage.getLoginText(), "Login to your account");

            // Log in with the correct credentials
            loginPage.loginToAccount("existUser1312311@tests.com", "Test123");
            test.log(Status.PASS, "Log in with the correct credentials", SeleniumHelper.getScreenshot(driver));

            // Verify that the logged-in user text contains "Logged in as"
            Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

            // Logout from account
            homePage.logoutAccount();
            test.log(Status.PASS, "Logout from account", SeleniumHelper.getScreenshot(driver));
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        }
    }
}
