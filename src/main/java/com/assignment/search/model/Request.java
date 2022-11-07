package com.assignment.search.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Request {

    @NotNull
    @Size(min=1)
    private String accountId;
    @Pattern(regexp = "^([0-2]?[0-9]|(3)[0-1])(\\.)(((0)?[0-9])|((1)[0-2]))(\\.)\\d{4}|^$")
    private String fromDate;
    @Pattern(regexp = "^([0-2]?[0-9]|(3)[0-1])(\\.)(((0)?[0-9])|((1)[0-2]))(\\.)\\d{4}$|^")
    private String toDate;
    @Pattern(regexp = "-?\\d+(\\.\\d+)?|^")
    private String fromAmount;
    @Pattern(regexp = "-?\\d+(\\.\\d+)?|^")
    private String toAmount;

    public Request(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        this.accountId = accountId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }
}
