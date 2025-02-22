package pl.automaty.pages;

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
    private WebElement productHeader;

    @FindBy(xpath = "//div[@class = 'productinfo text-center']//p")
    private List<WebElement> productList;

    @FindBy(xpath = "//div[@class = 'productinfo text-center']//a[@data-product-id]")
    private List<WebElement> addToCartList;

    @FindBy(xpath = "//a[text()='View Product']")
    private List<WebElement> viewProductTabs;

    @FindBy(xpath = "//div[@class='product-information']/h2")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Category')]")
    private WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Availability')]")
    private WebElement productAvailability;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Condition')]")
    private WebElement productCondition;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Brand')]")
    private WebElement productBrand;

    @FindBy(xpath = "//div[@class='product-information']//span[contains(text(),'Rs')]")
    private WebElement productPrice;

    @FindBy(id = "search_product")
    private WebElement searchProductInput;

    @FindBy(id = "submit_search")
    private WebElement searchProductButton;

    @FindBy(xpath = "//h2[contains(text(),'Searched Products')]")
    private WebElement searchedProductHeader;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCardLink;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingButton;

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addToCardButton;

    // Method to check if product list is visible
    public boolean isProductListVisible() {
        return !productList.isEmpty();
    }

    // Method to get the list of product elements
    public List<String> getProductListString() {
        return productList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public ProductsPage clickAddToCart(int prodNumber) {
        addToCartList.get(prodNumber).click();
        return this;
    }

    public CartPage clickItem(int item) {
        viewProductTabs.get(item).click();
        return new CartPage(driver);
    }

    public WebElement getProductHeader() {
        return productHeader;
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public List<String> getProductListTexts() {
        return productList.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
    }

    public WebElement getProductCategory() { return productCategory;}

    public WebElement getProductAvailability() { return productAvailability; }

    public WebElement getProductCondition() { return productCondition; }

    public WebElement getProductBrand() { return productBrand; }

    public WebElement getProductName() { return productName; }

    public WebElement getProductPrice() { return productPrice; }

    public WebElement getSearchedProductsHeader() { return searchedProductHeader; }

    public void searchProduct(String productName) {
        searchProductInput.sendKeys(productName);
        searchProductButton.click();
    }

    public CartPage viewCart() {
        viewCardLink.click();
        return new CartPage(driver);
    }

    public ProductsPage continueShopping() {
        continueShoppingButton.click();
        return this;
    }

    public ProductsPage inputQuantityNumber(int quantityNumber) {
        String quantityNumberStr = Integer.toString(quantityNumber);
        quantityInput.sendKeys(quantityNumberStr);
        return this;
    }

    public ProductsPage clearQuantityNumber() {
        quantityInput.clear();
        return this;
    }

    public ProductsPage addToCard() {
        addToCardButton.click();
        return this;
    }
}
