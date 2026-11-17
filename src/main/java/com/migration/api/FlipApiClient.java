package com.migration.api;

import com.migration.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class FlipApiClient {

    public List<Customer> getCustomers() {

        List<Customer> customers =
                new ArrayList<>();

        customers.add(

                new Customer(

                        101,
                        "Rahul Sharma",
                        "rahul.sharma@example.com",
                        "ACTIVE",
                        5000.50
                )
        );

        customers.add(

                new Customer(

                        102,
                        "Priya Nair",
                        "priya.nair@example.com",
                        "ACTIVE",
                        1200.50
                )
        );

        return customers;
    }
}