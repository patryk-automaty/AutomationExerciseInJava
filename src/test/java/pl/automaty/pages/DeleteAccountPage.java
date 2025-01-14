package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage {

    private WebDriver driver;

    public DeleteAccountPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//b[text()='Account Deleted!']")
    WebElement AccountDeletedText;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement ContinueButton;

    public String getAccountDeletedText() {
        return AccountDeletedText.getText();
    }

    public void clickContinue() {
        ContinueButton.click();
    }

}
