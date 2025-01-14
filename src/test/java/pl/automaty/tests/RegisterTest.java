package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;
import pl.automaty.pages.SignUpPage;

public class RegisterTest extends BaseTest {

    // Test Case 1
    @Test
    public void RegisterUserTest() {

        // Account Information data
        SignUpData signUpData = new SignUpData();
        signUpData.setGender("Mr");
        signUpData.setName("Pat");
        signUpData.setPassword("Dupa123");
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
        loginPage.SignUpUser("Pat", "d11sfsfds12322w123@123333.com");

        // fill account information
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.EnterAccountInformation(signUpData);

        // fill address information
        signUpPage.EnterAddressInformation(signUpData);
    }

}

