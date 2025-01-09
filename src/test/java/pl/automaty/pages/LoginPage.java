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
    WebElement LoginEmailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement LoginPasswordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement LoginButton;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement SignUpButton;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement SignUpNameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement SignUpEmailInput;

    public void LoginToAccount(String email, String password) {
        LoginEmailInput.sendKeys(email);
        LoginPasswordInput.sendKeys(password);
        LoginButton.click();
    }

    public void SignUpUser(String name, String email) {
        SignUpNameInput.sendKeys(name);
        SignUpEmailInput.sendKeys(email);
        SignUpButton.click();
    }

}
