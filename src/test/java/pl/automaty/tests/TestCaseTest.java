package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.TestCasePage;

public class TestCaseTest extends BaseTest {

    // TC 7
    @Test
    public void TestCasePageTest() {

        // Define expected value
        final String expectedTestCaseHeader = "TEST CASES";

        // Create instances for reporting and pages
        HomePage homePage = new HomePage(driver);
        TestCasePage testCasePage = new TestCasePage(driver);
        ExtentTest test = extentReports.createTest("Verify Test Cases Page");
        try {
            // Accept cookies and navigate to test case page
            homePage.consentCookies()
                    .testCase();
            test.log(Status.PASS, "Accepted cookies and navigated to test case page");

            // Verify that the test case header contains the text 'TEST CASES'
            String actualTestCaseHeaderText = testCasePage.getTestCaseHeader();
            Assert.assertEquals(actualTestCaseHeaderText, expectedTestCaseHeader);
            test.log(Status.PASS, "Verified that the test case header contains the text 'TEST CASES'");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: Expected '" + expectedTestCaseHeader + "', but found '" + testCasePage.getTestCaseHeader() + "'");
            throw e; // Failed due assertion
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to unexpected error: " + e.getMessage());
            throw e; // Failed due to unexpected error
        }
    }
}
