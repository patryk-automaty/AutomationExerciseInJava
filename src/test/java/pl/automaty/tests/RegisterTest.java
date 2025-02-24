package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;

public class RegisterTest extends BaseTest {

    // Test Case 1
    @Test
    public void RegisterUserTest() {

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

        // Open home page and direct to login page
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // sign up new user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.SignUpUser("Pat", "exis1tUser1312311@tests.com");

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

        // Click 'Delete Account' button
        homePage.deleteAccount();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }


    // Test Case 5
    @Test
    public void RegisterExistUserTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // Sign up new user with existing email
        loginPage.SignUpUser("Pat", "existUser13123@tests.com");
        Assert.assertEquals(signUpPage.getExistEmailText(), "Email Address already exist!");

    }

    @Test
    public void RegisterUserToTest() {

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

        // Open home page and direct to login page
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // sign up new user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.SignUpUser("Pat", "exis1tUser1312311@tests.com");

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

    }
}

