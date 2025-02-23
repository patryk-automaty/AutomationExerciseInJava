package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.TestCasePage;

public class TestCaseTest extends BaseTest {

    @Test
    public void TestCasePageTest() {
        // Create instances
        HomePage homePage = new HomePage(driver);
        TestCasePage testCasePage = new TestCasePage(driver);
        ExtentTest test = extentReports.createTest("Verify Subscription in home page");

        // Accept cookies and navigate to test case page
        homePage.consentCookies()
                .testCase();
        test.log(Status.PASS, "Accept cookies and navigate to test case page");

        // Verify that the test case header contains the text 'TEST CASES'
        Assert.assertEquals(testCasePage.getTestCaseHeader(), "TEST CASES");
        test.log(Status.PASS, "Verify that the test case header contains the text 'TEST CASES'");
    }
}
