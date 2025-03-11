package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.ContactUsData;
import pl.automaty.pages.ContactUsPage;
import pl.automaty.pages.HomePage;
import pl.automaty.utils.SeleniumHelper;

public class ContactUsTest extends BaseTest {

    // Test case 6
    @Test
    public void ContactUsFormTest() {
        // Define path to sample file
        final String PATH = "/home/patryk/IdeaProjects/AutomationExerciseInJava/src/test/java/pl/automaty/model/sample.txt";

        // Define test data
        String name = "Pat";
        String email = "testemail123@test.com";
        String subject = "Complaint Regarding Defective Laptop";
        String message = "I am writing to formally submit a complaint regarding the [Laptop Model Name]," +
                            " which I purchased on 2025/1/1 from Store." +
                            " Unfortunately, I have encountered the following issue:" +
                            "The screen has stopped working";

        String expectedSuccessMessage = "Success! Your details have been submitted successfully.";

        // Create instances for pages and reporting
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsData contactUsData = new ContactUsData();
        ExtentTest test = extentReports.createTest("Contact Us Form");
        try {
            // Open 'contact us' form page
            homePage.consentCookies()
                    .contactUs();
            test.log(Status.PASS, "Opened 'contact us' form page", SeleniumHelper.getScreenshot(driver));
            // Verify 'GET IN TOUCH' is visible
            Assert.assertTrue(contactUsPage.getGetInTouchText().contains("GET IN TOUCH"));
            test.log(Status.PASS, "Verified 'GET IN TOUCH' is visible", SeleniumHelper.getScreenshot(driver));
            contactUsData.setName(name)
                    .setEmail(email)
                    .setSubject(subject)
                    .setMessage(message);

            // Submit the contact form
            contactUsPage.setData(contactUsData)
                    .uploadFile(PATH)
                    .clickSubmit()
                    .acceptAlert();
            test.log(Status.PASS, "Submitted the contact form", SeleniumHelper.getScreenshot(driver));
            // Verify that the success message after form submission is as expected
            String actualSuccessMessage = contactUsPage.successMessageText();
            Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
            test.log(Status.PASS, "Verified success message after form submission is as expected", SeleniumHelper.getScreenshot(driver));
            // Navigate back to the Home page.
            contactUsPage.clickHome();
            test.log(Status.PASS, "Navigated back to the Home page.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
        }
    }
}
