package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    WebDriver driver;

    public CartPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//td[@class='cart_description']//a")
    private List<WebElement> productsName;

    @FindBy(xpath = "//td[@class='cart_description']//p")
    private List<WebElement> productsCategory;

    @FindBy(xpath = "//td[@class='cart_price']//p")
    private List<WebElement> productsPrice;

    @FindBy(xpath = "//td[@class='cart_quantity']//button")
    private List<WebElement> productsQuantity;

    @FindBy(xpath = "//td[@class='cart_total']//p")
    private List<WebElement> productsTotalPrice;

    @FindBy(className = "cart_quantity_delete")
    private List<WebElement> deleteProductButton;

    @FindBy(xpath = "//tr[contains(@id, 'product')]")
    private List<WebElement> numberOfProducts;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//button[text()='Continue On Cart']")
    private WebElement continueOnCartButton;

    @FindBy(xpath = "//u[text()='Register / Login']")
    private WebElement registerOrLoginLink;


    public List<WebElement> getProductsName() {
        return productsName;
    }

    public List<WebElement> getProductsCategory() {
        return productsCategory;
    }

    public List<WebElement> getProductsPrice() {
        return productsPrice;
    }

    public List<WebElement> getProductsQuantity() {
        return productsQuantity;
    }

    public List<WebElement> getProductsTotalPrice() {
        return productsTotalPrice;
    }

    public void deleteProduct(int index) {
        deleteProductButton.get(index).click();
    }

    public int getNumberOfProducts() {
        return deleteProductButton.size();
    }

    public CartPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return this;
    }

    public CartPage continueOnCart() {
        continueOnCartButton.click();
        return this;
    }

    public LoginPage registerOrLogin() {
        registerOrLoginLink.click();
        return new LoginPage(driver);
    }



}
