package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.*;
import pl.automaty.utils.SeleniumHelper;
import pl.automaty.utils.TestDataGenerator;

import java.io.File;
import java.io.IOException;


public class RegisterTest extends BaseTest {

    // TC 1
    @Test
    public void RegisterUserTest() {

        // Create instance for reporting
        ExtentTest test = extentReports.createTest("Register User");

        // Create instances for pages
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

        // Generate and save test data
        SignUpData signUpData = TestDataGenerator.generateSignUpTestData();
        TestDataGenerator.saveTestData(signUpData);

        // Load test data from JSON
        SignUpData loadedData = TestDataGenerator.loadTestData();

        // Define expected results
        String expectedAccountInformationText = "ENTER ACCOUNT INFORMATION";
        String expectedAccountCreatedText = "ACCOUNT CREATED!";
        String expectedDeletedMessage = "ACCOUNT DELETED!";

        try {
            // Open home page and navigate to login page
            homePage.consentCookies()
                    .openSignInAndLoginPage();
            test.log(Status.PASS, "Opened home page and navigated to login page", SeleniumHelper.getScreenshot(driver));

            // Sign up new user with generated test data
            loginPage.SignUpUser(loadedData.getUsername(), loadedData.getEmail());
            test.log(Status.PASS, "Entered sign-up details", SeleniumHelper.getScreenshot(driver));

            // Verify 'ENTER ACCOUNT INFORMATION' text is displayed
            String actualAccountInformationText = signUpPage.getEnterAccountInformationText();
            Assert.assertEquals(actualAccountInformationText, expectedAccountInformationText);
            test.log(Status.PASS, "Verified account information section is visible", SeleniumHelper.getScreenshot(driver));

            // Fill in account information using loaded test data
            signUpPage.EnterAccountInformation(loadedData);
            test.log(Status.PASS, "Entered account information", SeleniumHelper.getScreenshot(driver));

            // Fill in address information
            signUpPage.EnterAddressInformation(loadedData);
            test.log(Status.PASS, "Entered address information", SeleniumHelper.getScreenshot(driver));

            // Verify 'ACCOUNT CREATED!' text is displayed
            String actualAccountCreatedText = accountCreatedPage.getAccountCreatedText();
            Assert.assertEquals(actualAccountCreatedText, expectedAccountCreatedText);
            test.log(Status.PASS, "Verified account was created successfully", SeleniumHelper.getScreenshot(driver));

            // Click 'Continue' button
            accountCreatedPage.clickContinue();
            test.log(Status.PASS, "Clicked 'Continue' button", SeleniumHelper.getScreenshot(driver));

            // Verify that the user is logged in
            String loggedInMessage = homePage.loggedUserText();
            Assert.assertTrue(loggedInMessage.contains("Logged in as"),
                    "Expected 'Logged in as' message to be visible. Actual message: " + loggedInMessage);
            test.log(Status.PASS, "Verified that the user is logged in: '" + loggedInMessage + "'", SeleniumHelper.getScreenshot(driver));

            // Click 'Delete Account' button
            homePage.deleteAccount();
            test.log(Status.PASS, "Clicked 'Delete Account' button", SeleniumHelper.getScreenshot(driver));

            // Verify 'ACCOUNT DELETED!' message is displayed
            String actualDeletedMessage = deleteAccountPage.getAccountDeletedText();
            Assert.assertEquals(actualDeletedMessage, expectedDeletedMessage,
                    "Expected account deletion message: '" + expectedDeletedMessage + "'. Actual message:'" + actualDeletedMessage + "'");
            test.log(Status.PASS, "Verified account deletion message: '" + actualDeletedMessage + "'", SeleniumHelper.getScreenshot(driver));
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
        }
    }


    // TC 5
    @Test
    public void RegisterExistUserTest() throws IOException {

        // Define the data JSON file path
        final String DATA_PATH = "src/test/java/pl/automaty/model/testData.json";

        // Create instances for reporting and pages
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        TestDataGenerator testDataGenerator = new TestDataGenerator();

        // Create instance for reporting
        ExtentTest test = extentReports.createTest("Register Existing User Test");

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Define expected results
        String expectedErrorMessage = "Email Address already exist!";

        try {
            // Create new user
            testDataGenerator.RegisterUser();

            // Read JSON file as a tree (JsonNode)
            JsonNode rootNode = objectMapper.readTree(new File(DATA_PATH));

            // Extract specific value (email)
            String existAccountEmail = rootNode.get("email").asText();
            String existUserName = rootNode.get("username").asText();


            // Open home page and navigate to login page
            homePage.consentCookies()
                    .openSignInAndLoginPage();
            test.log(Status.PASS, "Navigated to login page from home page", SeleniumHelper.getScreenshot(driver));

            // Attempt to sign up with an existing email
            loginPage.SignUpUser(existUserName, existAccountEmail);
            test.log(Status.PASS, "Attempted to sign up with existing email: " + existAccountEmail, SeleniumHelper.getScreenshot(driver));

            // Verify error message is displayed
            String actualErrorMessage = signUpPage.getExistEmailText();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
            test.log(Status.PASS, "Verified error message for existing email: '" + actualErrorMessage + "'", SeleniumHelper.getScreenshot(driver));
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        }
    }
}

