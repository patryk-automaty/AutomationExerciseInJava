package pl.automaty.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.automaty.model.ContactUsData;

public class ContactUsPage {

    private WebDriver driver;

    public ContactUsPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h2[text()='Get In Touch']")
    WebElement GetInTouchText;

    @FindBy(name = "name")
    WebElement NameInput;

    @FindBy(name = "email")
    WebElement EmailInput;

    @FindBy(name = "subject")
    WebElement SubjectInput;

    @FindBy(id = "message")
    WebElement MessageInput;

    @FindBy(name = "upload_file")
    WebElement UploadFile;

    @FindBy(name = "submit")
    WebElement SubmitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    WebElement SuccessMessage;

    @FindBy(xpath ="//span[contains(text(), 'Home')]")
    WebElement HomeButton;




    public ContactUsPage setData(ContactUsData contactUsData) {
        NameInput.sendKeys(contactUsData.getName());
        EmailInput.sendKeys(contactUsData.getEmail());
        SubjectInput.sendKeys(contactUsData.getSubject());
        MessageInput.sendKeys(contactUsData.getMessage());
        return new ContactUsPage(driver);
    }
    public String getGetInTouchText() {
        return GetInTouchText.getText();
    }

    public ContactUsPage uploadFile(String path) {
        UploadFile.sendKeys(path);
        return this;
    }

    public ContactUsPage clickSubmit() {
        SubmitButton.click();
        return this;
    }

    public ContactUsPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public String successMessageText() {
        return SuccessMessage.getText();
    }

    public HomePage clickHome() {
        HomeButton.click();
        return new HomePage(driver);
    }

}
