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
        // Create needed instances and path to the sample file
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsData contactUsData = new ContactUsData();
        String path = "/home/patryk/IdeaProjects/AutomationExerciseInJava/src/test/java/pl/automaty/model/sample.txt";
        ExtentTest test = extentReports.createTest("Contact Us Form");
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
                    .uploadFile(path)
                    .clickSubmit()
                    .acceptAlert();
        test.log(Status.PASS, "Submit the contact form");
        // Verify that the success message after form submission is as expected
        Assert.assertEquals(contactUsPage.successMessageText(), "Success! Your details have been submitted successfully.");

        // Navigate back to the Home page.
        contactUsPage.clickHome();
        test.log(Status.PASS, "Navigate back to the Home page.");
    }
}
