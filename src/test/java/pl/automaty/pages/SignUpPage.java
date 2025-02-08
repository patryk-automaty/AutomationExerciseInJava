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
    private WebElement mrRadiobutton;

    @FindBy(id = "id_gender2")
    private WebElement mrsRadiobutton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement birthDaySelect;


    @FindBy(id = "months")
    private WebElement birthMonthSelect;

    @FindBy(id = "years")
    private WebElement birthYearSelect;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement offersCheckbox;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    private WebElement enterAccountInformationText;

    // Address information elements

    @FindBy(xpath = "//b[text()='Address Information']")
    private WebElement AddressInformationText;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    private WebElement existEmailText;

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
