package com.migration.db;

import com.migration.models.Customer;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SourceDataLoader {

    public List<Customer> getCustomers()
            throws Exception {

        List<Customer> customers =
                new ArrayList<>();

        CSVReader reader =
                new CSVReader(
                        new FileReader(
                                "src/main/resources/source_customers.csv"
                        )
                );

        String[] line;

        reader.readNext();

        while((line = reader.readNext()) != null){

            customers.add(

                    new Customer(

                            Integer.parseInt(line[0]),
                            line[1],
                            line[2],
                            line[3],
                            Double.parseDouble(line[4])
                    )
            );
        }

        return customers;
    }
}