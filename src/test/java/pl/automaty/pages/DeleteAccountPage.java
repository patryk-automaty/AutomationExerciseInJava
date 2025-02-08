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
    private WebElement accountDeletedText;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public String getAccountDeletedText() {
        return accountDeletedText.getText();
    }

    public void clickContinue() {
        continueButton.click();
    }

}
