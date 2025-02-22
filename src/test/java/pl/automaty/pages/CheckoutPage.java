package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[@id='address_delivery']")
    WebElement deliveryAddressObject;

    @FindBy(xpath = "//a[text()='Place Order']")
    WebElement placeOrderButton;

    @FindBy(name = "message")
    WebElement orderMessageInput;

    public WebElement getDeliveryAddress() {
        return deliveryAddressObject;
    }

    public CheckoutPage clickPlaceOrder() {
        placeOrderButton.click();
        return this;
    }

    public CheckoutPage addOrderMessage(String message) {
        orderMessageInput.sendKeys(message);
        return this;
    }
}
