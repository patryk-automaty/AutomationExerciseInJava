package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;
import pl.automaty.utils.TestDataGenerator;

public class RegisterTest extends BaseTest {

    // Test Case 1
    @Test
    public void RegisterUserTest() {

        // Create instances for reporting and pages
        ExtentTest test = extentReports.createTest("Register User");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

        // Step 1: Generate and save test data
        SignUpData signUpData = TestDataGenerator.generateTestData();
        TestDataGenerator.saveTestData(signUpData);

        // Step 2: Load test data from JSON
        SignUpData loadedData = TestDataGenerator.loadTestData();

        // Step 3: Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Opened home page and navigated to login page");

        // Step 4: Sign up new user with generated test data
        loginPage.SignUpUser(loadedData.getName(), loadedData.getName() + "@testmail.com");
        test.log(Status.PASS, "Entered sign-up details");

        // Step 5: Verify 'ENTER ACCOUNT INFORMATION' text is displayed
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");
        test.log(Status.PASS, "Verified account information section is visible");

        // Step 6: Fill in account information using loaded test data
        signUpPage.EnterAccountInformation(loadedData);
        test.log(Status.PASS, "Entered account information");

        // Step 7: Fill in address information
        signUpPage.EnterAddressInformation(loadedData);
        test.log(Status.PASS, "Entered address information");

        // Step 8: Verify 'ACCOUNT CREATED!' text is displayed
        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");
        test.log(Status.PASS, "Verified account was created successfully");

        // Step 9: Click 'Continue' button
        accountCreatedPage.clickContinue();
        test.log(Status.PASS, "Clicked 'Continue' button");

        // Step 10: Verify that the user is logged in
        String loggedInMessage = homePage.loggedUserText();
        Assert.assertTrue(loggedInMessage.contains("Logged in as"),
                "Expected 'Logged in as' message to be visible. Actual message: " + loggedInMessage);
        test.log(Status.PASS, "Verified that the user is logged in: '" + loggedInMessage + "'");

        // Step 11: Click 'Delete Account' button
        homePage.deleteAccount();
        test.log(Status.PASS, "Clicked 'Delete Account' button");

        // Step 12: Verify 'ACCOUNT DELETED!' message is displayed
        String actualDeletedMessage = deleteAccountPage.getAccountDeletedText();
        String expectedDeletedMessage = "ACCOUNT DELETED!";
        Assert.assertEquals(actualDeletedMessage, expectedDeletedMessage,
                "Expected account deletion message: '" + expectedDeletedMessage + "'. Actual message:'" + actualDeletedMessage + "'");
        test.log(Status.PASS, "Verified account deletion message: '" + actualDeletedMessage + "'");
    }


    // Test Case 5
    @Test
    public void RegisterExistUserTest() {

        // Create test instance for reporting
        ExtentTest test = extentReports.createTest("Register Existing User Test");

        // Create page instances
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Step 1: Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Navigated to login page from home page");

        // Step 2: Attempt to sign up with an existing email
        String existingEmail = "existUser13123@tests.com";
        String username = "Pat";
        loginPage.SignUpUser(username, existingEmail);
        test.log(Status.INFO, "Attempted to sign up with existing email: " + existingEmail);

        // Step 3: Verify error message is displayed
        String expectedErrorMessage = "Email Address already exist!";
        String actualErrorMessage = signUpPage.getExistEmailText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        test.log(Status.PASS, "Verified error message for existing email: '" + actualErrorMessage + "'");

    }

    @Test(enabled = false)
    public void RegisterUserToTest2() {

        // Create instances for reporting and pages
        ExtentTest test = extentReports.createTest("Register User");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        // Step 1: Generate and save test data
        SignUpData signUpData = TestDataGenerator.generateTestData();
        TestDataGenerator.saveTestData(signUpData);

        // Step 2: Load test data from JSON
        SignUpData loadedData = TestDataGenerator.loadTestData();

        // Step 3: Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();
        test.log(Status.PASS, "Opened home page and navigated to login page");

        // Step 4: Sign up new user with generated test data
        loginPage.SignUpUser(loadedData.getName(), loadedData.getName() + "@testmail.com");
        test.log(Status.PASS, "Entered sign-up details");

        // Step 5: Verify 'ENTER ACCOUNT INFORMATION' text is displayed
        Assert.assertEquals(signUpPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION");
        test.log(Status.PASS, "Verified account information section is visible");

        // Step 6: Fill in account information using loaded test data
        signUpPage.EnterAccountInformation(loadedData);
        test.log(Status.PASS, "Entered account information");

        // Step 7: Fill in address information
        signUpPage.EnterAddressInformation(loadedData);
        test.log(Status.PASS, "Entered address information");

        // Step 8: Verify 'ACCOUNT CREATED!' text is displayed
        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");
        test.log(Status.PASS, "Verified account was created successfully");

        // Step 9: Click 'Continue' button
        accountCreatedPage.clickContinue();
        test.log(Status.PASS, "Clicked 'Continue' button");
    }
}

