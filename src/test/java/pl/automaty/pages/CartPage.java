package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    public CartPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
