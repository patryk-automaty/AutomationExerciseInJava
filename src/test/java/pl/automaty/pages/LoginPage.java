package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement loginEmailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signUpButton;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement signUpNameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement signUpEmailInput;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement incorrectLoginText;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement loginText;


    public HomePage loginToAccount(String email, String password) {
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public SignUpPage SignUpUser(String name, String email) {
        signUpNameInput.sendKeys(name);
        signUpEmailInput.sendKeys(email);
        signUpButton.click();
        return new SignUpPage(driver);
    }

    public String getIncorrectLoginText() {
        return incorrectLoginText.getText();
    }

    public String getLoginText() {
        return loginText.getText();
    }
}
