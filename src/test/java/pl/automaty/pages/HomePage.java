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

    @FindBy(xpath = "//a[text()=' Logged in as ']")
    private WebElement UserLoggedInfo;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement LogoutButton;

    @FindBy(xpath = "//a[contains(text(), 'Delete Account')]")
    private WebElement DeleteAccountButton;

    @FindBy(xpath = "//a[contains(text(), 'Contact us')]")
    private WebElement ContactUsButton;


    private WebDriver driver;

    public HomePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public LoginPage openSignInAndLoginPage() {
        SignUpAndLoginLink.click();
        return new LoginPage(driver);
    }

    public HomePage consentCookies() {
        CookiesConsentButton.click();
        return new HomePage(driver);
    }

    public void logoutAccount() {
        LogoutButton.click();
    }

    public void deleteAccount() {
        DeleteAccountButton.click();
    }
    public String loggedUserText() {
        return UserLoggedInfo.getText();
    }

    public void contactUs() {
        ContactUsButton.click();
        new ContactUsPage(driver);
    }

}
