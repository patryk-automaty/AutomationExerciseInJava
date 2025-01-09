package pl.automaty.tests;

import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;
import pl.automaty.pages.SignUpPage;

public class RegisterTest extends BaseTest {
    @Test
    public void RegisterUserTest() {
        HomePage homePage = new HomePage(driver);
        homePage.consentCookies()
                .openSignInAndLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.SignUpUser("Pat", "dsfsfdsfdtestqw123@123333.com");

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.EnterAccountInformation("Mr", "Pat", "dupa123", "12", "5", "2000",
                                            Boolean.TRUE, Boolean.TRUE);
    }

}

