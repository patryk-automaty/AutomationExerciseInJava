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
    WebElement mrRadiobutton;

    @FindBy(id = "id_gender2")
    WebElement mrsRadiobutton;

    @FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "days")
    WebElement birthDaySelect;


    @FindBy(id = "months")
    WebElement birthMonthSelect;

    @FindBy(id = "years")
    WebElement birthYearSelect;

    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    WebElement offersCheckbox;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement enterAccountInformationText;

    // Address information elements

    @FindBy(xpath = "//b[text()='Address Information']")
    WebElement AddressInformationText;

    @FindBy(id = "first_name")
    WebElement firstNameInput;

    @FindBy(id = "last_name")
    WebElement lastNameInput;

    @FindBy(id = "company")
    WebElement companyInput;

    @FindBy(id = "address1")
    WebElement address1Input;

    @FindBy(id = "address2")
    WebElement address2Input;

    @FindBy(id = "country")
    WebElement countrySelect;

    @FindBy(id = "state")
    WebElement stateInput;

    @FindBy(id = "city")
    WebElement cityInput;

    @FindBy(id = "zipcode")
    WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    WebElement mobileNumberInput;

    @FindBy(xpath = "//button[text()='Create Account']")
    WebElement createAccountButton;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement existEmailText;

    public void EnterAccountInformation(SignUpData signUpData) {
        // Check if the gender is "Mr"
        if (signUpData.getGender().equals("Mr")) {
            // Click the "Mr" radio button
            mrRadiobutton.click();
        }
        else {
            // Otherwise, click the "Mrs" radio button
            mrsRadiobutton.click();
        }

        // Input name and password fields
        nameInput.sendKeys(signUpData.getName());
        passwordInput.sendKeys(signUpData.getPassword());

        // Select date of birth from dropdown
        Select selectDay = new Select(birthDaySelect);
        selectDay.selectByValue(signUpData.getBirthDay());

        Select selectMonth = new Select(birthMonthSelect);
        selectMonth.selectByValue(signUpData.getBirthMonth());

        Select selectYear = new Select(birthYearSelect);
        selectYear.selectByValue(signUpData.getBirthYear());

        // Newsletter checkbox
        if (signUpData.getNewsletter().equals(Boolean.TRUE)) {
            newsletterCheckbox.click();
        }

        // Get offer checkbox
        if (signUpData.getNewsletter().equals(Boolean.TRUE)) {
            offersCheckbox.click();
        }
    }

    public void EnterAddressInformation(SignUpData signUpData) {

        firstNameInput.sendKeys(signUpData.getFirstName());
        lastNameInput.sendKeys(signUpData.getLastName());
        companyInput.sendKeys(signUpData.getCompany());
        address1Input.sendKeys(signUpData.getAddress1());
        address2Input.sendKeys(signUpData.getAddress2());
        Select selectCountry = new Select(countrySelect);
        selectCountry.selectByValue(signUpData.getCountry());
        stateInput.sendKeys(signUpData.getState());
        cityInput.sendKeys(signUpData.getCity());
        zipcodeInput.sendKeys(signUpData.getZipcode());
        mobileNumberInput.sendKeys(signUpData.getMobileNumber());
        createAccountButton.click();

    }

    public String getEnterAccountInformationText() {
        return enterAccountInformationText.getText();
    }

    public String getExistEmailText() {
        return existEmailText.getText();
    }

}
