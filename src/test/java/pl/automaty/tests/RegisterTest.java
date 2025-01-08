package pl.automaty.tests;

import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.SignInAndLoginPage;

public class RegisterTest extends BaseTest {
    @Test
    public void RegisterUserTest() {
        new HomePage(driver).consentCookies()
                            .openSignInAndLoginPage();
    }
}

