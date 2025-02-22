package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.automaty.model.PaymentData;

public class PaymentPage {

    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name = "name_on_card")
    WebElement nameOnCardInput;

    @FindBy(name = "card_number")
    WebElement cardNumberInput;

    @FindBy(name = "cvc")
    WebElement cvcCodeInput;

    @FindBy(name = "expiry_month")
    WebElement expirationMonthInput;

    @FindBy(name = "expiry_year")
    WebElement expirationYearInput;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(xpath = "//p[contains(text(), 'Congratulations')]")
    WebElement successOrderMessage;

    public void enterPaymentInformation (PaymentData paymentData) {
        nameOnCardInput.sendKeys(paymentData.getNameOnCard());
        cardNumberInput.sendKeys(paymentData.getCardNumber());
        cvcCodeInput.sendKeys(paymentData.getCvcCode());
        expirationMonthInput.sendKeys(paymentData.getExpirationMonth());
        expirationYearInput.sendKeys(paymentData.getExpirationYear());
        submitButton.click();
    }

    public WebElement getSuccessOrderMessage() {
        return successOrderMessage;
    }

}
