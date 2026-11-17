package com.migration.models;

public class Customer {

    private int customerId;
    private String fullName;
    private String email;
    private String accountStatus;
    private double balance;

    public Customer() {}

    public Customer(
            int customerId,
            String fullName,
            String email,
            String accountStatus,
            double balance) {

        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.accountStatus = accountStatus;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public double getBalance() {
        return balance;
    }
}