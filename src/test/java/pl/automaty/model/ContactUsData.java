package pl.automaty.model;

import org.openqa.selenium.WebDriver;

public class ContactUsData {

    // "Get in touch" elements
    private String name;
    private String email;
    private String subject;
    private String message;



    public String getName() {
        return name;
    }

    public ContactUsData setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactUsData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactUsData setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ContactUsData setMessage(String message) {
        this.message = message;
        return this;
    }
}
