package com.migration.validator;

import com.migration.models.Customer;
import com.migration.models.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class MigrationValidator {

    public List<ValidationResult> validate(

            List<Customer> source,

            List<Customer> target) {

        List<ValidationResult> results =
                new ArrayList<>();

        for(Customer sourceCustomer : source){

            Customer targetCustomer =

                    target.stream()

                            .filter(

                                    x ->
                                            x.getCustomerId()
                                                    ==
                                                    sourceCustomer.getCustomerId()
                            )

                            .findFirst()

                            .orElse(null);

            if(targetCustomer == null){

                results.add(

                        new ValidationResult(

                                String.valueOf(
                                        sourceCustomer.getCustomerId()
                                ),

                                "customer",

                                "Exists",

                                "Missing",

                                "FAIL"
                        )
                );

                continue;
            }

            if(sourceCustomer.getBalance()

                    !=

                    targetCustomer.getBalance()){

                results.add(

                        new ValidationResult(

                                String.valueOf(
                                        sourceCustomer.getCustomerId()
                                ),

                                "balance",

                                String.valueOf(
                                        sourceCustomer.getBalance()
                                ),

                                String.valueOf(
                                        targetCustomer.getBalance()
                                ),

                                "FAIL"
                        )
                );

            }
            else{

                results.add(

                        new ValidationResult(

                                String.valueOf(
                                        sourceCustomer.getCustomerId()
                                ),

                                "ALL",

                                "MATCHED",

                                "MATCHED",

                                "PASS"
                        )
                );
            }
        }

        return results;
    }
}