package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.ContactUsData;
import pl.automaty.pages.ContactUsPage;
import pl.automaty.pages.HomePage;

public class ContactUsTest extends BaseTest {

    // Test case 6
    @Test
    public void ContactUsFormTest() {
        // Define path to sample file
        final String PATH = "/home/patryk/IdeaProjects/AutomationExerciseInJava/src/test/java/pl/automaty/model/sample.txt";

        // Create instances for pages and reporting
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsData contactUsData = new ContactUsData();
        ExtentTest test = extentReports.createTest("Contact Us Form");
        try {
            // Open 'contact us' form page
            homePage.consentCookies()
                    .contactUs();
            test.log(Status.PASS, "Open 'contact us' form page");
            // Verify 'GET IN TOUCH' is visible
            Assert.assertTrue(contactUsPage.getGetInTouchText().contains("GET IN TOUCH"));
            contactUsData.setName("Pat")
                    .setEmail("testemail123@test.com")
                    .setSubject("Complaint Regarding Defective Laptop ")
                    .setMessage("I am writing to formally submit a complaint regarding the [Laptop Model Name]," +
                            " which I purchased on 2025/1/1 from Store." +
                            " Unfortunately, I have encountered the following issue:\n" +
                            "The screen has stopped working");

            // Submit the contact form
            contactUsPage.setData(contactUsData)
                    .uploadFile(PATH)
                    .clickSubmit()
                    .acceptAlert();
            test.log(Status.PASS, "Submit the contact form");
            // Verify that the success message after form submission is as expected
            Assert.assertEquals(contactUsPage.successMessageText(), "Success! Your details have been submitted successfully.");

            // Navigate back to the Home page.
            contactUsPage.clickHome();
            test.log(Status.PASS, "Navigate back to the Home page.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed: " + e.getMessage());
        }
    }
}
