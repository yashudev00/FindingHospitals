package org.example.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ExcelUtils {

    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet = workbook.createSheet("Test Results");
    private static int rowIndex = 0;

    static {
        Row header = sheet.createRow(rowIndex++);
        header.createCell(0).setCellValue("Test Case");
        header.createCell(1).setCellValue("Status");
        header.createCell(2).setCellValue("Message");
    }

    public static void writeResult(String testName, String status, String message) {

        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(message);
    }

    public static void saveExcel() {
        try {
            File file = new File("results/TestResults.xlsx");
            file.getParentFile().mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}