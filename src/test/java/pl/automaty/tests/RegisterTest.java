package pl.automaty.tests;

import org.testng.annotations.Test;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;
import pl.automaty.pages.SignUpPage;

public class RegisterTest extends BaseTest {
    @Test
    public void RegisterUserTest() {

        // User data
        SignUpData signUpData = new SignUpData();
        signUpData.setGender("Mr");
        signUpData.setName("Pat");
        signUpData.setPassword("Dupa123");
        signUpData.setBirthDay("11");
        signUpData.setBirthMonth("3");
        signUpData.setBirthYear("2000");
        signUpData.setNewsletter(Boolean.TRUE);
        signUpData.setOffer(Boolean.TRUE);


        // Open home page and direct to login page
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // sign up new user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.SignUpUser("Pat", "dsfsfds132w123@123333.com");

        // fill account information
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.EnterAccountInformation(signUpData);
    }

}

