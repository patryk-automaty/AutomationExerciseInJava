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

    @FindBy(xpath = "//a[contains(text(), 'Test Cases')]")
    private WebElement TestCaseButton;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    private WebElement productButton;


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
        return this;
    }

    public HomePage logoutAccount() {
        LogoutButton.click();
        return new HomePage(driver);
    }

    public DeleteAccountPage deleteAccount() {
        DeleteAccountButton.click();
        return new DeleteAccountPage(driver);
    }
    public String loggedUserText() {
        return UserLoggedInfo.getText();
    }

    public ContactUsPage contactUs() {
        ContactUsButton.click();
        return new ContactUsPage(driver);
    }

    public TestCasePage testCase() {
        TestCaseButton.click();
        return new TestCasePage(driver);
    }

    public ProductsPage productNavBar() {
        productButton.click();
        return new ProductsPage(driver);
    }

}
