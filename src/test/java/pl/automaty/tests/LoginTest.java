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

        HomePage homePage = new HomePage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));
        homePage.deleteAccount();
        Assert.assertTrue(deleteAccountPage.getAccountDeletedText().contains("ACCOUNT DELETED!"));
        deleteAccountPage.clickContinue();

    }

    // Test Case 3
    @Test
    public void IncorrectLoginTest() {

        HomePage homePage = new HomePage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage()
                .loginToAccount("testCorrectUser1993@testCorrectUser1993.com", "Test123!");
        Assert.assertEquals(loginPage.getIncorrectLoginText(), "Your email or password is incorrect!");

    }

    // Test Case 4
    @Test
    public void LogoutUserTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage();
        Assert.assertEquals(loginPage.getLoginText(), "Login to your account");
        loginPage.loginToAccount("existUser1312311@tests.com", "Test123");
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));
        homePage.logoutAccount();

    }
}
