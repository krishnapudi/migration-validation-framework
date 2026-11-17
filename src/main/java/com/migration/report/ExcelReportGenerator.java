package com.migration.report;

import com.migration.models.ValidationResult;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelReportGenerator {

    public static void generate(
            List<ValidationResult> results)
            throws Exception {

        // Create Reports Folder

        File reportDir =
                new File("reports");

        if (!reportDir.exists()) {

            reportDir.mkdirs();

            System.out.println(
                    "Reports directory created successfully."
            );
        }

        XSSFWorkbook workbook =
                new XSSFWorkbook();

        XSSFSheet sheet =
                workbook.createSheet(
                        "Migration Results"
                );

        XSSFRow header =
                sheet.createRow(0);

        header.createCell(0)
                .setCellValue("Customer ID");

        header.createCell(1)
                .setCellValue("Field");

        header.createCell(2)
                .setCellValue("Source Value");

        header.createCell(3)
                .setCellValue("Target Value");

        header.createCell(4)
                .setCellValue("Status");

        int rowNum = 1;

        for (ValidationResult result : results) {

            XSSFRow row =
                    sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(
                            result.getCustomerId()
                    );

            row.createCell(1)
                    .setCellValue(
                            result.getField()
                    );

            row.createCell(2)
                    .setCellValue(
                            result.getSourceValue()
                    );

            row.createCell(3)
                    .setCellValue(
                            result.getTargetValue()
                    );

            row.createCell(4)
                    .setCellValue(
                            result.getStatus()
                    );
        }

        for (int i = 0; i < 5; i++) {

            sheet.autoSizeColumn(i);
        }

        String reportPath =
                "reports/MigrationResults.xlsx";

        FileOutputStream out =
                new FileOutputStream(
                        reportPath
                );

        workbook.write(out);

        out.close();

        workbook.close();

        System.out.println(
                "\nExcel Report Generated Successfully"
        );

        System.out.println(
                "Report Location : "
                        + new File(reportPath)
                        .getAbsolutePath()
        );
    }
}