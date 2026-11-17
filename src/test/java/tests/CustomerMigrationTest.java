package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.migration.api.FlipApiClient;
import com.migration.db.SourceDataLoader;
import com.migration.models.Customer;
import com.migration.models.ValidationResult;

import com.migration.report.ExcelReportGenerator;
import com.migration.report.ExtentManager;

import com.migration.validator.MigrationValidator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMigrationTest {

    @Test
    public void validateMigration() throws Exception {

        // Source Data

        List<Customer> source =
                new SourceDataLoader()
                        .getCustomers();

        // Target Data (FLIP API)

        List<Customer> target =
                new FlipApiClient()
                        .getCustomers();

        // Validation

        List<ValidationResult> results =

                new MigrationValidator()
                        .validate(
                                source,
                                target
                        );

        // Generate Excel Report

        ExcelReportGenerator
                .generate(results);

        // Generate HTML Report

        ExtentReports extent =
                ExtentManager.getInstance();

        ExtentTest test =
                extent.createTest(
                        "Customer Migration Validation"
                );

        int passCount = 0;
        int failCount = 0;

        System.out.println();
        System.out.println(
                "==================================================");
        System.out.println(
                "       MIGRATION VALIDATION REPORT");
        System.out.println(
                "==================================================");

        for (ValidationResult result : results) {

            if ("PASS".equalsIgnoreCase(
                    result.getStatus())) {

                passCount++;

                System.out.println(
                        "\n[PASS]"
                );

                System.out.println(
                        "Customer ID : "
                                + result.getCustomerId()
                );

                System.out.println(
                        "\n--------------------------------------------------"
                );

                test.pass(

                        "Customer ID : "
                                + result.getCustomerId()

                                + " validated successfully."
                );

            } else {

                failCount++;

                System.out.println(
                        "\n[FAIL]"
                );

                System.out.println(
                        "Customer ID : "
                                + result.getCustomerId()
                );

                System.out.println(
                        "Field       : "
                                + result.getField()
                );

                System.out.println(
                        "Source      : "
                                + result.getSourceValue()
                );

                System.out.println(
                        "Target      : "
                                + result.getTargetValue()
                );

                System.out.println(
                        "\n--------------------------------------------------"
                );

                test.fail(

                        "Customer ID : "
                                + result.getCustomerId()

                                + "<br>Field : "
                                + result.getField()

                                + "<br>Source : "
                                + result.getSourceValue()

                                + "<br>Target : "
                                + result.getTargetValue()
                );
            }
        }

        int totalRecords = results.size();

        double successRate =
                ((double) passCount
                        / totalRecords) * 100;

        System.out.println();
        System.out.println(
                "Migration Summary");
        System.out.println(
                "--------------------------------------------------");

        System.out.println(
                "Total Records : "
                        + totalRecords);

        System.out.println(
                "Passed        : "
                        + passCount);

        System.out.println(
                "Failed        : "
                        + failCount);

        System.out.println(
                "Success Rate  : "
                        + String.format(
                        "%.2f",
                        successRate)
                        + " %");

        System.out.println(
                "\n=================================================="
        );

        // Summary In HTML Report

        test.info(
                "Total Records : "
                        + totalRecords
        );

        test.info(
                "Passed : "
                        + passCount
        );

        test.info(
                "Failed : "
                        + failCount
        );

        test.info(
                "Success Rate : "
                        + String.format(
                        "%.2f",
                        successRate)
                        + "%"
        );

        extent.flush();

        System.out.println();
        System.out.println(
                "HTML Report Generated : reports/MigrationReport.html"
        );

        System.out.println(
                "Excel Report Generated : reports/MigrationResults.xlsx"
        );

        // Build should fail if mismatches exist

        assertEquals(

                0,

                failCount,

                "Migration Validation Failed. \n\n"
                        +
                        "Please check reports:\n"
                        +
                        "1. reports/MigrationResults.xlsx\n"
                        +
                        "2. reports/MigrationReport.html"
        );
    }
}