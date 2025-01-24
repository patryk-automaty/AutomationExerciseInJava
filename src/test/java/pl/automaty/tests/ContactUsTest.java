package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.ContactUsData;
import pl.automaty.pages.ContactUsPage;
import pl.automaty.pages.HomePage;

public class ContactUsTest extends BaseTest {

    // Test case 6
    @Test
    public void ContactUsFormTest() {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsData contactUsData = new ContactUsData();
        String path = "/home/patryk/IdeaProjects/AutomationExerciseInJava/src/test/java/pl/automaty/model/sample.txt";
        // open contact us form page
        homePage.consentCookies()
                .contactUs();
        // Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUsPage.getGetInTouchText().contains("GET IN TOUCH"));
        contactUsData.setName("Pat")
                     .setEmail("testemail123@test.com")
                     .setSubject("Complaint Regarding Defective Laptop ")
                     .setMessage("I am writing to formally submit a complaint regarding the [Laptop Model Name]," +
                                " which I purchased on 2025/1/1 from Store." +
                                " Unfortunately, I have encountered the following issue:\n" +
                                "The screen has stopped working");

        contactUsPage.setData(contactUsData)
                     .uploadFile(path)
                     .clickSubmit()
                     .acceptAlert();
        Assert.assertEquals(contactUsPage.successMessageText(), "Success! Your details have been submitted successfully.");
        contactUsPage.clickHome();
    }
}
