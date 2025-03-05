package pl.automaty.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pl.automaty.model.PaymentData;
import pl.automaty.model.SignUpData;
import pl.automaty.pages.AccountCreatedPage;
import pl.automaty.pages.HomePage;
import pl.automaty.pages.LoginPage;
import pl.automaty.pages.SignUpPage;
import pl.automaty.tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private static final String FILE_PATH = "src/test/java/pl/automaty/model/testData.json";
    private WebDriver driver;

    // Method to generate random test data using Faker
    public static SignUpData generateSignUpTestData() {
        Faker faker = new Faker();
        List<String> countries = List.of("India", "Canada", "United States", "Australia", "Israel", "New Zealand", "Singapore");
        Random random = new Random();
        String randomCountry = countries.get(random.nextInt(countries.size()));
        SignUpData signUpData = new SignUpData();
        signUpData.setGender(faker.options().option("Mr", "Mrs"));
        signUpData.setName(faker.name().firstName());
        signUpData.setPassword(faker.internet().password(8, 12, true, true));
        signUpData.setBirthDay(String.valueOf(faker.number().numberBetween(1, 28)));
        signUpData.setBirthMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        signUpData.setBirthYear(String.valueOf(faker.number().numberBetween(1970, 2005)));
        signUpData.setNewsletter(faker.bool().bool());
        signUpData.setOffer(faker.bool().bool());

        signUpData.setFirstName(faker.name().firstName());
        signUpData.setLastName(faker.name().lastName());
        signUpData.setCompany(faker.company().name());
        signUpData.setAddress1(faker.address().streetAddress());
        signUpData.setAddress2(faker.address().secondaryAddress());

        signUpData.setCountry(randomCountry);
        signUpData.setState(faker.address().state());
        signUpData.setCity(faker.address().city());
        signUpData.setZipcode(faker.address().zipCode());
        signUpData.setMobileNumber(faker.phoneNumber().cellPhone());

        signUpData.setUsername(faker.name().username());
        signUpData.setEmail(faker.internet().emailAddress());

        return signUpData;
    }

    public static PaymentData generatePaymentTestData() {
        Faker faker = new Faker();
        PaymentData paymentData = new PaymentData();
        paymentData.setNameOnCard(faker.name().firstName() + faker.name().lastName());
        return paymentData;

    }

    // Method to save generated test data to JSON
    public static void saveTestData(SignUpData signUpData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), signUpData);
            System.out.println("Test data saved to: " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to load test data from JSON
    public static SignUpData loadTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(FILE_PATH), SignUpData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SignUpData registerNewUser(WebDriver driver) {
        // Create instances of page objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        // Generate new user test data
        SignUpData signUpData = generateSignUpTestData();
        saveTestData(signUpData);  // Save test data for later use

        // Open home page and navigate to login page
        homePage.consentCookies()
                .openSignInAndLoginPage();

        // Sign up with generated test data
        loginPage.SignUpUser(signUpData.getUsername(), signUpData.getEmail());

        // Fill in account information
        signUpPage.EnterAccountInformation(signUpData);

        // Fill in address information
        signUpPage.EnterAddressInformation(signUpData);

        // Click 'Continue' button after account creation
        accountCreatedPage.clickContinue();

        //Logout from account
        homePage.logoutAccount();

        // Back to home page
        homePage.backToHomePage();

        // Return the registered user data
        return signUpData;
    }

}
