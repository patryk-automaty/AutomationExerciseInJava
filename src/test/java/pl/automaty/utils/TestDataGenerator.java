package pl.automaty.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import pl.automaty.model.SignUpData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private static final String FILE_PATH = "src/test/java/pl/automaty/model/testData.json";

    // Method to generate random test data using Faker
    public static SignUpData generateTestData() {
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
}
