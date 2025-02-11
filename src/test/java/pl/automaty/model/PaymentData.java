package pl.automaty.model;

public class PaymentData {

    private String nameOnCard;
    private String cardNumber;
    private String cvcCode;
    private String expirationMonth;
    private String expirationYear;

    public String getNameOnCard() {
        return nameOnCard;
    }

    public PaymentData setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentData setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public PaymentData setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
        return this;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public PaymentData setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public PaymentData setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }




}
