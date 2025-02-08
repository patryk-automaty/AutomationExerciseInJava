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

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement deleteProductButton;

    @FindBy(xpath = "//tr[contains(@id, 'product')]")
    private List<WebElement> numberOfProducts;

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

    public void deleteProduct() {
        deleteProductButton.click();
    }

    public int getNumberOfProducts() {
        return numberOfProducts.size();
    }



}
