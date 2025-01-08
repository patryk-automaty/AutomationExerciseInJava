package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInAndLoginPage {

    public SignInAndLoginPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        //this.driver = driver;
    }

    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement LoginEmailButton;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement LoginPasswordButton;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement LoginButton;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement SignUpButton;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement SignUpNameButton;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement SignUpEmailButton;


}
