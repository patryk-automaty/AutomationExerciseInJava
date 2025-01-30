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
    WebElement getInTouchText;

    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "subject")
    WebElement subjectInput;

    @FindBy(id = "message")
    WebElement messageInput;

    @FindBy(name = "upload_file")
    WebElement uploadFile;

    @FindBy(name = "submit")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    WebElement successMessage;

    @FindBy(xpath ="//span[contains(text(), 'Home')]")
    WebElement homeButton;




    public ContactUsPage setData(ContactUsData contactUsData) {
        nameInput.sendKeys(contactUsData.getName());
        emailInput.sendKeys(contactUsData.getEmail());
        subjectInput.sendKeys(contactUsData.getSubject());
        messageInput.sendKeys(contactUsData.getMessage());
        return new ContactUsPage(driver);
    }
    public String getGetInTouchText() {
        return getInTouchText.getText();
    }

    public ContactUsPage uploadFile(String path) {
        uploadFile.sendKeys(path);
        return this;
    }

    public ContactUsPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public ContactUsPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public String successMessageText() {
        return successMessage.getText();
    }

    public HomePage clickHome() {
        homeButton.click();
        return new HomePage(driver);
    }

}
