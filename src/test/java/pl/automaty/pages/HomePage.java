package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.cert.X509Certificate;

public class HomePage {

    @FindBy(xpath = "//li/a[text()=' Signup / Login']")
    private WebElement signUpAndLoginLink;

    @FindBy(xpath = "//button/p[text()='Consent']")
    private WebElement cookiesConsentButton;

    @FindBy(xpath = "//a[text()=' Logged in as ']")
    private WebElement userLoggedInfo;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(text(), 'Delete Account')]")
    private WebElement deleteAccountButton;

    @FindBy(xpath = "//a[contains(text(), 'Contact us')]")
    private WebElement contactUsButton;

    @FindBy(xpath = "//a[contains(text(), 'Test Cases')]")
    private WebElement testCaseButton;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    private WebElement productButton;

    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionHeader;

    @FindBy(id = "susbscribe_email")
    private WebElement subscriptionEmailInput;

    @FindBy(id = "subscribe")
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[text()='You have been successfully subscribed!']")
    private WebElement successSubMessage;

    @FindBy(xpath = "//a[contains(text(), 'Cart')]")
    private WebElement cartButton;


    private WebDriver driver;

    public HomePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public LoginPage openSignInAndLoginPage() {
        signUpAndLoginLink.click();
        return new LoginPage(driver);
    }

    public HomePage consentCookies() {
        cookiesConsentButton.click();
        return this;
    }

    public HomePage logoutAccount() {
        logoutButton.click();
        return new HomePage(driver);
    }

    public DeleteAccountPage deleteAccount() {
        deleteAccountButton.click();
        return new DeleteAccountPage(driver);
    }
    public String loggedUserText() {
        return userLoggedInfo.getText();
    }

    public ContactUsPage contactUs() {
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public TestCasePage testCase() {
        testCaseButton.click();
        return new TestCasePage(driver);
    }

    public ProductsPage productNavBar() {
        productButton.click();
        return new ProductsPage(driver);
    }

    public String subscriptionHeaderText() {
        return subscriptionHeader.getText();
    }

    public HomePage sendSubscriptionEmail(String email) {
        subscriptionEmailInput.sendKeys(email);
        subscriptionButton.click();
        return this;
    }

    public String successSubMessageText() {
        return successSubMessage.getText();
    }

    public CartPage viewCart() {
        cartButton.click();
        return new CartPage(driver);
    }


}
