package pl.automaty.pages;

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
    private WebElement getInTouchText;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "subject")
    private WebElement subjectInput;

    @FindBy(id = "message")
    private WebElement messageInput;

    @FindBy(name = "upload_file")
    private WebElement uploadFile;

    @FindBy(name = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    private WebElement successMessage;

    @FindBy(xpath ="//span[contains(text(), 'Home')]")
    private WebElement homeButton;




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
