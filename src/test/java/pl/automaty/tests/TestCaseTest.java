package pl.automaty.tests;

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

        // Accept cookies and navigate to test case page
        homePage.consentCookies()
                .testCase();

        // Verify that the test case header contains the text 'TEST CASES'
        Assert.assertEquals(testCasePage.getTestCaseHeader(), "TEST CASES");

    }
}
