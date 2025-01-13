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

    // Address information elements

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

    public void EnterAccountInformation(String gender, String name, String password, String day, String month,
                                        String year,Boolean newsletter, Boolean offer ) {
        // Check if the gender is "Mr"
        if (gender.equals("Mr")) {
            // Click the "Mr" radio button
            MrRadiobutton.click();
        }
        else {
            // Otherwise, click the "Mrs" radio button
            MrsRadiobutton.click();
        }
        // Input name and password fields
        NameInput.sendKeys(name);
        PasswordInput.sendKeys(password);
        // Select date of birth from dropdown
        Select selectDay = new Select(BirthDaySelect);
        selectDay.selectByValue(day);

        Select selectMonth = new Select(BirthMonthSelect);
        selectMonth.selectByValue(month);

        Select selectYear = new Select(BirthYearSelect);
        selectYear.selectByValue(year);

        if (newsletter.equals(Boolean.TRUE)) {
            NewsletterCheckbox.click();
        }
        if (offer.equals(Boolean.TRUE)) {
            OffersCheckbox.click();
        }
    }


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
}
