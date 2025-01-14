package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage {

    private WebDriver driver;

    public AccountCreatedPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//b[text()='Account Created!']")
    WebElement AccountCreatedText;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement ContinueButton;

    public String getAccountCreatedText() {
        return AccountCreatedText.getText();
    }

    public void clickContinue() {
        ContinueButton.click();
    }
}