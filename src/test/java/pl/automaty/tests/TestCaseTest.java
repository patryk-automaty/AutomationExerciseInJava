package pl.automaty.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.TestCasePage;

public class TestCaseTest extends BaseTest {

    @Test
    public void TestCasePageTest() {
        HomePage homePage = new HomePage(driver);
        TestCasePage testCasePage = new TestCasePage(driver);
        homePage.consentCookies()
                .testCase();
        Assert.assertEquals(testCasePage.getTestCaseHeader(), "TEST CASES");

    }
}
