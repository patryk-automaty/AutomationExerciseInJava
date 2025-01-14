package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.automaty.model.SignUpData;

public class SignUpPage {

    private WebDriver driver;

    public SignUpPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Account information elements

    @FindBy(id = "id_gender1")
    WebElement MrRadiobutton;

    @FindBy(id = "id_gender2")
    WebElement MrsRadiobutton;

    @FindBy(id = "name")
    WebElement NameInput;

    @FindBy(id = "email")
    WebElement EmailInput;

    @FindBy(id = "password")
    WebElement PasswordInput;

    @FindBy(id = "days")
    WebElement BirthDaySelect;


    @FindBy(id = "months")
    WebElement BirthMonthSelect;

    @FindBy(id = "years")
    WebElement BirthYearSelect;

    @FindBy(id = "newsletter")
    WebElement NewsletterCheckbox;

    @FindBy(id = "optin")
    WebElement OffersCheckbox;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement EnterAccountInformationText;

    // Address information elements

    @FindBy(xpath = "//b[text()='Address Information']")
    WebElement AddressInformationText;

    @FindBy(id = "first_name")
    WebElement FirstNameInput;

    @FindBy(id = "last_name")
    WebElement LastNameInput;

    @FindBy(id = "company")
    WebElement CompanyInput;

    @FindBy(id = "address1")
    WebElement Address1Input;

    @FindBy(id = "address2")
    WebElement Address2Input;

    @FindBy(id = "country")
    WebElement CountrySelect;

    @FindBy(id = "state")
    WebElement StateInput;

    @FindBy(id = "city")
    WebElement CityInput;

    @FindBy(id = "zipcode")
    WebElement ZipcodeInput;

    @FindBy(id = "mobile_number")
    WebElement MobileNumberInput;

    @FindBy(xpath = "//button[text()='Create Account']")
    WebElement CreateAccountButton;


    public void EnterAccountInformation(SignUpData signUpData) {
        // Check if the gender is "Mr"
        if (signUpData.getGender().equals("Mr")) {
            // Click the "Mr" radio button
            MrRadiobutton.click();
        }
        else {
            // Otherwise, click the "Mrs" radio button
            MrsRadiobutton.click();
        }

        // Input name and password fields
        NameInput.sendKeys(signUpData.getName());
        PasswordInput.sendKeys(signUpData.getPassword());

        // Select date of birth from dropdown
        Select selectDay = new Select(BirthDaySelect);
        selectDay.selectByValue(signUpData.getBirthDay());

        Select selectMonth = new Select(BirthMonthSelect);
        selectMonth.selectByValue(signUpData.getBirthMonth());

        Select selectYear = new Select(BirthYearSelect);
        selectYear.selectByValue(signUpData.getBirthYear());

        // Newsletter checkbox
        if (signUpData.getNewsletter().equals(Boolean.TRUE)) {
            NewsletterCheckbox.click();
        }

        // Get offer checkbox
        if (signUpData.getNewsletter().equals(Boolean.TRUE)) {
            OffersCheckbox.click();
        }
    }

    public void EnterAddressInformation(SignUpData signUpData) {

        FirstNameInput.sendKeys(signUpData.getFirstName());
        LastNameInput.sendKeys(signUpData.getLastName());
        CompanyInput.sendKeys(signUpData.getCompany());
        Address1Input.sendKeys(signUpData.getAddress1());
        Address2Input.sendKeys(signUpData.getAddress2());
        Select selectCountry = new Select(CountrySelect);
        selectCountry.selectByValue(signUpData.getCountry());
        StateInput.sendKeys(signUpData.getState());
        CityInput.sendKeys(signUpData.getCity());
        ZipcodeInput.sendKeys(signUpData.getZipcode());
        MobileNumberInput.sendKeys(signUpData.getMobileNumber());
        CreateAccountButton.click();

    }

    public String getEnterAccountInformationText() {
        return EnterAccountInformationText.getText();
    }

}
