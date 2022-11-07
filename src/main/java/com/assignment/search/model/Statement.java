package com.assignment.search.model;

public class Statement {

    private int accountId;
    private String dateField;
    private String amount;

    public Statement(int accountId, String dateField, String amount) {
        this.accountId = accountId;
        this.dateField = dateField;
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "accountId='" + accountId + '\'' +
                ", dateField='" + dateField + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
