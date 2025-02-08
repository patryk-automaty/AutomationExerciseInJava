package pl.automaty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasePage {

    private WebDriver driver;

    public TestCasePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//b[text()='Test Cases']")
    private WebElement testCaseHeader;

    public String getTestCaseHeader() {
        return testCaseHeader.getText();
    }

}
