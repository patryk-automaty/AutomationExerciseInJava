package pl.automaty.listeners;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import pl.automaty.utils.SeleniumHelper;

public class AdCleaningWebDriverListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        SeleniumHelper.waitForAndRemoveAds(SeleniumHelper.getDriver());
    }

    @Override
    public void afterClick(WebElement element) {
        SeleniumHelper.removeAdIframe(SeleniumHelper.getDriver());
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        SeleniumHelper.removeAdIframe(SeleniumHelper.getDriver());
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        SeleniumHelper.removeAdIframe(SeleniumHelper.getDriver());
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        SeleniumHelper.removeAdIframe(SeleniumHelper.getDriver());
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        SeleniumHelper.removeAdIframe(SeleniumHelper.getDriver());
    }

}
