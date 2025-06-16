# AutomationExerciseInJava

This project is a collection of automated tests written in Java, designed to practice and demonstrate proficiency in automation testing using Selenium WebDriver and TestNG.

## Project Structure

The project follows a standard Maven structure:

- **`src/test/java/pl/automaty`**: Contains the test classes and packages.

  
## Prerequisites

To run this project, ensure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or higher)
- [Apache Maven](https://maven.apache.org/install.html)
- [Google Chrome Browser](https://www.google.com/chrome/) (for ChromeDriver)
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) (ensure the version matches your Chrome browser)

## Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/patryk-automaty/AutomationExerciseInJava.git
   cd AutomationExerciseInJava
   
2. **Install Dependencies:**:

   ```bash
   mvn clean install
   
3. **Configure WebDriver:**:

   Ensure that the ChromeDriver executable is in your system's PATH. Alternatively, specify the path to the ChromeDriver in your test setup.

## Running the Tests

Execute the tests using Maven:

   ```bash
   mvn test
```

## Reporting

Test results and reports are generated using TestNG's default reporting mechanism. You can find the reports in the index.html file.


  
