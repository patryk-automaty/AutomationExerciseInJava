package pl.automaty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    private WebDriver driver;

    public ProductsPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h2[text()='All Products']")
    WebElement productHeader;

    @FindBy(xpath = "//div[@class = 'single-products']//p")
    List<WebElement> productList;

    @FindBy(xpath = "//a[text()='View Product']")
    List<WebElement> viewProductTabs;

    @FindBy(xpath = "//div[@class='product-information']/h2")
    WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Category')]")
    WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Availability')]")
    WebElement productAvailability;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Condition')]")
    WebElement productCondition;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Brand')]")
    WebElement productBrand;

    @FindBy(xpath = "//div[@class='product-information']//span[contains(text(),'Rs')]")
    WebElement productPrice;



    // Method to check if product list is visible
    public boolean isProductListVisible() {
        return !productList.isEmpty();
    }

    // Method to get the list of product elements
    public List<String> getProductList() {
        return productList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickFirstItem() {
        viewProductTabs.get(0).click();
    }

    public String getProductHeaderText() {
        return productHeader.getText();
    }

    public Boolean checkProductList() {
        return productHeader.isDisplayed();
    }

    public Boolean checkProductCategory() { return productCategory.isDisplayed(); }

    public Boolean checkProductAvailability() { return productAvailability.isDisplayed(); }

    public Boolean checkProductCondition() { return productCondition.isDisplayed(); }

    public Boolean checkProductBrand() { return productBrand.isDisplayed(); }

    public Boolean checkProductName() { return productName.isDisplayed(); }

    public Boolean checkProductPrice() { return productPrice.isDisplayed(); }






}
