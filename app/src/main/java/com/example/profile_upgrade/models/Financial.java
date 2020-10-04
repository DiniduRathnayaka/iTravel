package com.example.profile_upgrade.models;

public class Financial {
    private String nameOnCard;
    private String cardNumber;
    private String paymentMethod;
    private String paymentDate;
    private String expMonth;
    private String expYear;
    private String CVV;
    private String id;

    public Financial(){

    }

    public Financial(String id,String nameOnCard, String cardNumber, String paymentMethod, String paymentDate, String expMonth, String expYear, String CVV) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.CVV = CVV;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
