package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;

public class RegisterTest extends BaseTest {

    // Test Case 1
    @Test
    public void RegisterUserTest() {
        // Create instances
        ExtentTest test = extentReports.createTest("Register User");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

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
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Open home page and direct to login page");

        // sign up new user
        loginPage.SignUpUser("Pat", "exis1tUser1312311@tests.com");
        test.log(Status.PASS, "Sign up new user");

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

        // Verify that 'Logged in as username' is visible
        Assert.assertTrue(homePage.loggedUserText().contains("Logged in as"));

        // Click 'Delete Account' button
        homePage.deleteAccount();
        test.log(Status.PASS, "Click 'Delete Account' button");

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertEquals(deleteAccountPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }


    // Test Case 5
    @Test
    public void RegisterExistUserTest() {

        // Create instances
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ExtentTest test = extentReports.createTest("Register User");

        // Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Open home page and navigate to login page");

        // Sign up new user with existing email
        loginPage.SignUpUser("Pat", "existUser13123@tests.com");
        Assert.assertEquals(signUpPage.getExistEmailText(), "Email Address already exist!");
        test.log(Status.PASS, "Sign up new user with existing email");

    }

    @Test
    public void RegisterUserToTest() {

        // Create instances
        ExtentTest test = extentReports.createTest("Register User");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

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
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Open home page and direct to login page");

        // sign up new user
        loginPage.SignUpUser("Pat", "exis1tUser1312311@tests.com");

        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");
        test.log(Status.PASS, "Sign up new user");

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

    }
}

