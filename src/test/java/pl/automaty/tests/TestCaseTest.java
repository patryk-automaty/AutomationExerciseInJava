package pl.automaty.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.TestCasePage;
import pl.automaty.utils.SeleniumHelper;

import java.io.IOException;

public class TestCaseTest extends BaseTest {

    // TC 7
    @Test
    public void TestCasePageTest() throws IOException {

        // Define expected value
        final String EXPECTED_TEST_CASE_HEADER = "TEST CASES";


        // Create instances for reporting and pages
        HomePage homePage = new HomePage(driver);
        TestCasePage testCasePage = new TestCasePage(driver);
        ExtentTest test = extentReports.createTest("Verify Test Cases Page");
        try {
            // Accept cookies and navigate to test case page
            homePage.consentCookies()
                    .testCase();
            test.log(Status.PASS, "Accepted cookies and navigated to test case page", SeleniumHelper.getScreenshot(driver));
            // Verify that the test case header contains the text 'TEST CASES'
            String actualTestCaseHeaderText = testCasePage.getTestCaseHeader();
            Assert.assertEquals(actualTestCaseHeaderText, EXPECTED_TEST_CASE_HEADER);
            test.log(Status.PASS, "Verified that the test case header contains the text 'TEST CASES'", SeleniumHelper.getScreenshot(driver));
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion failed: Expected '" + EXPECTED_TEST_CASE_HEADER + "', but found '" + testCasePage.getTestCaseHeader() + "'", SeleniumHelper.getScreenshot(driver));
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to unexpected error: " + e.getMessage(), SeleniumHelper.getScreenshot(driver));
            throw e;
        }
    }
}
