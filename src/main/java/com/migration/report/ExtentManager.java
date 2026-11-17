package com.migration.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(
                            "reports/MigrationReport.html"
                    );

            reporter.config()
                    .setReportName(
                            "Migration Validation Report"
                    );

            reporter.config()
                    .setDocumentTitle(
                            "Migration Reconciliation Report"
                    );

            extent = new ExtentReports();

            extent.attachReporter(
                    reporter
            );
        }

        return extent;
    }
}