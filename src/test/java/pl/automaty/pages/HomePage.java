package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//li/a[text()=' Signup / Login']")
    private WebElement SignUpAndLoginLink;

    @FindBy(xpath = "//button/p[text()='Consent']")
    private WebElement CookiesConsentButton;

    private WebDriver driver;

    public HomePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SignInAndLoginPage openSignInAndLoginPage() {
        SignUpAndLoginLink.click();
        return new SignInAndLoginPage(driver);
    }

    public HomePage consentCookies() {
        CookiesConsentButton.click();
        return new HomePage(driver);
    }

}
